package com.ar.sgi.services;

import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.DetalleVenta;
import com.ar.sgi.model.Venta;
import com.ar.sgi.model.dto.VentaDTO;
import com.ar.sgi.repository.DetalleVentaRepository;
import com.ar.sgi.repository.ProductoRepository;
import com.ar.sgi.repository.ProductoStockRepository;
import com.ar.sgi.repository.VentaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class VentaService {
    private static final Logger LOGGER = Logger.getLogger(VentaService.class.getName());

    @Inject
    VentaRepository ventaRepository;
    @Inject
    ProductoRepository productoRepository;
    @Inject
    DetalleVentaRepository detalleVentaRepository;
    @Inject
    ProductoStockRepository productoStockRepository;

    @Transactional
    public void agregar(VentaDTO ventaDTO) throws ServiceLayerException {
        try {
            Venta venta = new Venta();
            venta.setNroTicket(ventaDTO.getNroTicket());
            venta.setTotal(ventaDTO.getTotal());
            venta.setClienteId(ventaDTO.getClienteId() == null || ventaDTO.getClienteId() == 0 ? null : ventaDTO.getClienteId());
            venta.setFechaHora(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("GMT-3"))));
            venta.setTipoFac(ventaDTO.getTipoFac());
            venta.setPuntoVenta(ventaDTO.getPuntoVenta());
            ventaRepository.persist(venta);
            ventaDTO.getDetalle()
                    .forEach(it -> {
                        var producto = productoRepository.findById(it.getProductoId());
                        if(producto != null && producto.getProductoStock().getControlaStock()) {
                            var detalleVenta = new DetalleVenta();
                            detalleVenta.setVenta(venta);
                            detalleVenta.setProductoId(producto.getId());
                            detalleVenta.setCantidad(it.getCantidad());
                            detalleVenta.setPrecioHistorico(it.getPrecioHistorico());
                            detalleVentaRepository.persist(detalleVenta);
                            productoStockRepository.descontarStock(producto.getProductoStock(), Math.abs(detalleVenta.getCantidad()));
                        }
                    });
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.CREAR_VENTA_EXCEPTION);
        }
    }
}
