package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.MovimientoStock;
import com.ar.sgi.model.MovimientoStockDetalle;
import com.ar.sgi.model.Paginator;
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
public class MovimientoStockDetalleRepository implements PanacheRepositoryBase<MovimientoStockDetalle, Long> {

}

