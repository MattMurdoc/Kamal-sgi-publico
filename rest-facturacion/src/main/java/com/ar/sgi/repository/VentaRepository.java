package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.FormaPago;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Venta;
import com.ar.sgi.model.dto.VentaFiltroDTO;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

@ApplicationScoped
public class VentaRepository implements PanacheRepositoryBase<Venta, Long> {

    @Inject
    FormaPagoRepository formaPagoRepository;

    public Paginator<Venta> listar(Integer pagina, Integer items) throws PersistanceLayerException {
        Paginator<Venta> paginator = null;

        try {
            PanacheQuery<Venta> query = this.findAll();
            query.page(Page.of(pagina, items));
            List<Venta> venta = query.list();
            paginator = venta != null && !venta.isEmpty() ? new Paginator<Venta>(venta, this.count()) : null;
        } catch (Exception e) {
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_VENTA_EXCEPTION);
        }
        return paginator;
    }

    public Paginator<Venta> listarFiltro(Integer pagina, Integer items, VentaFiltroDTO ventaFiltroDTO) throws PersistanceLayerException {
        Paginator<Venta> paginator = null;
        try {
            PanacheQuery<Venta> query = null;

            if (ventaFiltroDTO != null) {

                Map<String, Object> params = new HashMap<>();
                String consulta = "";
                query = this.find(consulta, params);

                if (ventaFiltroDTO.getFechaDesde() != null && ventaFiltroDTO.getFechaHasta() != null) {
                    params.put("fechaHoraDesde", ventaFiltroDTO.getFechaDesde());
                    params.put("fechaHoraHasta", ventaFiltroDTO.getFechaHasta());
                    consulta += "fechaHora BETWEEN :fechaHoraDesde and :fechaHoraHasta";
                }

                if (ventaFiltroDTO.getFormaPago() != null) {
                    FormaPago formaPago = formaPagoRepository.findById(ventaFiltroDTO.getFormaPago());
                    params.put("formaPago", formaPago);
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "(formaPago) = :formaPago ";
                }
                query = this.find(consulta, params);
            } else {
                query = this.findAll();
            }
            query.page(Page.of(pagina, items));
            List<Venta> venta = query.list();
            paginator = venta != null && !venta.isEmpty() ? new Paginator<Venta>(venta, query.count()) : null;
        } catch (Exception e) {
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_VENTA_EXCEPTION);
        }
        return paginator;
    }
}
