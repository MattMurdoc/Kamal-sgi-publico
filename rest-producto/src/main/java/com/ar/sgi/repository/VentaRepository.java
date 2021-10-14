package com.ar.sgi.repository;

import com.ar.sgi.model.Venta;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VentaRepository implements PanacheRepositoryBase<Venta, Long> {

}
