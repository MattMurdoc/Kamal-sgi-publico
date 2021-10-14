package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.DetalleVenta;
import com.ar.sgi.model.Paginator;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class DetalleVentaRepository implements PanacheRepositoryBase<DetalleVenta, Long> {
}
