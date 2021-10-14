package com.ar.sgi.services;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.DetalleVenta;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Venta;
import com.ar.sgi.repository.DetalleVentaRepository;
import com.ar.sgi.repository.VentaRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;

public class DetalleVentaService {

    @Inject
    DetalleVentaRepository detalleVentaRepository;

    public Paginator<DetalleVenta> listarTodo(int pagina, int items) throws Exception {
        try {
            return detalleVentaRepository.listar(pagina, items);
        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            throw new ServiceLayerException(ServiceLayerException.LISTAR_DETALLE_VENTA_EXCEPTION);
        }
    }

    @Transactional
    public void agregar(DetalleVenta detalleVenta) throws ServiceLayerException {
        try {
            detalleVentaRepository.persist(detalleVenta);
        } catch (Exception e) {
            throw new ServiceLayerException(ServiceLayerException.CREAR_DETALLE_VENTA_EXCEPTION);
        }
    }
}
