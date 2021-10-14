package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.DetalleVenta;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Venta;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class DetalleVentaRepository implements PanacheRepositoryBase<DetalleVenta, Long> {

    public Paginator<DetalleVenta> listar(Integer pagina, Integer items) throws PersistanceLayerException {
        Paginator<DetalleVenta> paginator = null;
        try {
            PanacheQuery<DetalleVenta> query = this.findAll();
            query.page(Page.of(pagina, items));
            List<DetalleVenta> detalleVenta = query.list();
            paginator = detalleVenta != null && !detalleVenta.isEmpty() ? new Paginator<DetalleVenta>(detalleVenta, this.count()) : null;
        } catch(Exception e) {
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_DETALLE_VENTA_EXCEPTION);
        }
        return paginator;
    }
}
