package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.FormaPago;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Venta;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;


@ApplicationScoped
public class FormaPagoRepository implements PanacheRepositoryBase<FormaPago, Long> {

    public Paginator<FormaPago> listar(Integer pagina, Integer items) throws PersistanceLayerException {
        Paginator<FormaPago> paginator = null;
        try {
            PanacheQuery<FormaPago> query = this.findAll();
            query.page(Page.of(pagina, items));
            List<FormaPago> formaPago = query.list();
            paginator = formaPago != null && !formaPago.isEmpty() ? new Paginator<FormaPago>(formaPago, this.count()) : null;
        } catch(Exception e) {
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_FORMA_PAGO_EXCEPTION);
        }
        return paginator;
    }
}
