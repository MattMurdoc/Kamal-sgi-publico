package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.*;
import com.ar.sgi.model.dto.MovimientoStockFiltroDTO;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


@ApplicationScoped
public class MovimientoStockRepository implements PanacheRepositoryBase<MovimientoStock, Long> {

    private static final Logger LOGGER = Logger.getLogger(MovimientoStockRepository.class.getName());

    public Paginator<MovimientoStock> listar(int pagina, int items, MovimientoStockFiltroDTO filtro) throws PersistanceLayerException {
        Paginator<MovimientoStock> paginator;
        try {
            PanacheQuery<MovimientoStock> query;
            if (filtro != null) {
                Map<String, Object> params = new HashMap<>();
                String consulta = "";
                if (filtro.getFechaDesde() != null && filtro.getFechaHasta() != null) {
                    params.put("fechaHoraDesde", filtro.getFechaDesde());
                    params.put("fechaHoraHasta", filtro.getFechaHasta());
                    consulta += "fechaHora between :fechaHoraDesde and :fechaHoraHasta ";
                } else if(filtro.getFechaDesde() != null) {
                    params.put("fechaHoraDesde", filtro.getFechaDesde());
                    consulta += "fechaHora >= :fechaHoraDesde";
                } else if(filtro.getFechaHasta() != null) {
                    params.put("fechaHoraHasta", filtro.getFechaHasta());
                    consulta += "fechaHora <= :fechaHoraHasta";
                }
                if (filtro.getTipoMovimiento() != null && filtro.getTipoMovimiento() > 0) {
                    params.put("tipoMovimiento", filtro.getTipoMovimiento());
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "tipoMovimiento = :tipoMovimiento ";
                }
                if (filtro.getMotivo() != null && filtro.getMotivo() > 0) {
                    params.put("motivo", filtro.getMotivo());
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "motivo = :motivo ";
                }
                query = this.find(consulta, params);

            } else {
                query = this.findAll();
            }
            query.page(Page.of(pagina, items));
            List<MovimientoStock> movimientos = query.list();

            paginator = movimientos != null && !movimientos.isEmpty() ? new Paginator<>(movimientos, query.count()) : null;


        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_MOVIMIENTO_STOCK_EXCEPTION);
        }
        return paginator;
    }
}
