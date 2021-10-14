package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Tarjeta;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class TarjetaRepository implements PanacheRepositoryBase<Tarjeta, Long> {

    public Paginator<Tarjeta> listar(Integer pagina, Integer items) throws PersistanceLayerException {
        Paginator<Tarjeta> paginator = null;
        try {
            PanacheQuery<Tarjeta> query = this.findAll();
            query.page(Page.of(pagina, items));
            List<Tarjeta> tarjeta = query.list();
            paginator = tarjeta != null && !tarjeta.isEmpty() ? new Paginator<Tarjeta>(tarjeta, this.count()) : null;
        } catch(Exception e) {
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_TARJETA_EXCEPTION);
        }
        return paginator;
    }
}
