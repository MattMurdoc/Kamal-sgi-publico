package com.ar.sgi.services;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.MovimientoStock;
import com.ar.sgi.model.MovimientoStockDetalle;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Producto;
import com.ar.sgi.model.dto.MovimientoStockConDetalleDTO;
import com.ar.sgi.model.dto.MovimientoStockDTO;
import com.ar.sgi.model.dto.MovimientoStockFiltroDTO;
import com.ar.sgi.repository.*;
import com.ar.sgi.util.DateFormatter;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class MovimientoStockService {

    private static final Logger LOGGER = Logger.getLogger(MovimientoStockService.class.getName());

    @Inject
    MovimientoStockRepository movimientoStockRepository;
    @Inject
    MovimientoStockDetalleRepository movimientoStockDetalleRepository;
    @Inject
    ProductoStockRepository productoStockRepository;
    @Inject
    ProductoRepository productoRepository;
    @Inject
    MaestroRepository maestroRepository;

    public Paginator<MovimientoStockDTO> listarTodo(int pagina, int items, MovimientoStockFiltroDTO filtro) throws Exception {

        try {
            if(filtro != null) {
                if(StringUtils.isNotBlank(filtro.getFechaDesdeStr())) {
                    filtro.setFechaDesde(DateFormatter.formatStringToSqlTimestamp(filtro.getFechaDesdeStr()));
                }
                if(StringUtils.isNotBlank(filtro.getFechaHastaStr())) {
                    filtro.setFechaHasta(DateFormatter.formatStringToSqlTimestamp(filtro.getFechaHastaStr()));
                }
            }
            Paginator<MovimientoStock> movimientoStockPaginator = movimientoStockRepository.listar(pagina, items, filtro);
            Paginator<MovimientoStockDTO> movimientoStockDTOPaginator = null;
            if(movimientoStockPaginator != null && movimientoStockPaginator.validar()) {
                List<MovimientoStockDTO> listMovimientoStockDTO = new ArrayList<>();
                for (MovimientoStock movimientoStock : movimientoStockPaginator.getEntity()) {
                    MovimientoStockDTO dto = new MovimientoStockDTO();
                    dto.setFechaHora(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(movimientoStock.getFechaHora()));
                    dto.setId(movimientoStock.getId());
                    dto.setObservacion(movimientoStock.getObservacion());
                    dto.setTipoMovimiento(maestroRepository.getDescripcionByValorYTipoDato(movimientoStock.getTipoMovimiento(), MaestroRepository.TIPO_DATO_TIPO_MOVIMIENTO));
                    dto.setMotivo(maestroRepository.getDescripcionByValorYTipoDato(movimientoStock.getMotivo(), MaestroRepository.TIPO_DATO_MOTIVO));
                    listMovimientoStockDTO.add(dto);
                }
                movimientoStockDTOPaginator = new Paginator<>(listMovimientoStockDTO, movimientoStockPaginator.getCantidadRegistros());
            }
            return movimientoStockDTOPaginator;
        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_DEPARTAMENTO_EXCEPTION);
        }
    }

    public MovimientoStock buscarMovimientoStock(Long movimientoStockId) throws Exception {
        try {
            return movimientoStockRepository.findById(movimientoStockId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new ServiceLayerException(ServiceLayerException.BUSCAR_MOVIMIENTO_STOCK_EXCEPTION);
        }
    }

    @Transactional
    public void agregar(MovimientoStockConDetalleDTO movimientoStockConDetalleDTO) throws ServiceLayerException {
        try {
            // Armamos el movimiento stock
            MovimientoStock movimientoStock = new MovimientoStock();
            movimientoStock.setFechaHora(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("GMT-3"))));
            movimientoStock.setTipoMovimiento(movimientoStockConDetalleDTO.getTipoMovimiento());
            movimientoStock.setMotivo(movimientoStockConDetalleDTO.getMotivo());
            movimientoStock.setObservacion(movimientoStockConDetalleDTO.getObservacion());
            movimientoStockRepository.persist(movimientoStock);
            // Armamos el detalle
            movimientoStockConDetalleDTO.getDetalle()
                    .forEach(it -> {
                        var producto = productoRepository.findById(it.getIdProducto());
                        if(producto.getProductoStock().getControlaStock()) {
                            var movimientoStockDetalle = new MovimientoStockDetalle();
                            movimientoStockDetalle.setMovimientoStock(movimientoStock);
                            movimientoStockDetalle.setProducto(producto);
                            movimientoStockDetalle.setCantidad(it.getCantidad());
                            movimientoStockDetalleRepository.persist(movimientoStockDetalle);

                            if (movimientoStock.getTipoMovimiento().equals(MaestroRepository.TIPO_MOVIMIENTO_ENTRADA_LONG)) {
                                productoStockRepository.aumentarStock(producto.getProductoStock(), Math.abs(movimientoStockDetalle.getCantidad()));
                            } else if (movimientoStock.getTipoMovimiento().equals(MaestroRepository.TIPO_MOVIMIENTO_SALIDA_LONG)) {
                                productoStockRepository.descontarStock(producto.getProductoStock(), Math.abs(movimientoStockDetalle.getCantidad()));
                            } else if (movimientoStock.getTipoMovimiento().equals(MaestroRepository.TIPO_MOVIMIENTO_AJUSTE_STOCK_LONG)) {
                                if (movimientoStockDetalle.getCantidad() >= 0) {
                                    productoStockRepository.aumentarStock(producto.getProductoStock(), Math.abs(movimientoStockDetalle.getCantidad()));
                                } else {
                                    productoStockRepository.descontarStock(producto.getProductoStock(), Math.abs(movimientoStockDetalle.getCantidad()));
                                }
                            }
                        }
                    });
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.CREAR_MOVIMIENTO_STOCK_EXCEPTION);
        }
    }
}
