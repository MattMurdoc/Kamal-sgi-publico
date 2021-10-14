package com.ar.sgi.services;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Venta;
import com.ar.sgi.model.dto.VentaFiltroDTO;
import com.ar.sgi.repository.VentaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.logging.Level;

@ApplicationScoped
public class VentaService {

    @Inject
    VentaRepository ventaRepository;

    public Paginator<Venta> listarTodo(int pagina, int items) throws Exception {
        try {
            return ventaRepository.listar(pagina, items);
        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            throw new ServiceLayerException(ServiceLayerException.LISTAR_VENTA_EXCEPTION);
        }
    }

    public Paginator<Venta> listar(Integer pagina, Integer items, VentaFiltroDTO ventaFiltroDTO) throws ServiceLayerException {
        Paginator<Venta> paginator = null;
        try {
            paginator = ventaRepository.listarFiltro(pagina, items, ventaFiltroDTO);
        } catch(Exception e) {
            throw new ServiceLayerException(ServiceLayerException.LISTAR_VENTA_EXCEPTION);
        }
        return paginator;
    }

    @Transactional
    public void agregar(Venta venta) throws ServiceLayerException {
        try {
            ventaRepository.persist(venta);
        } catch (Exception e) {
            throw new ServiceLayerException(ServiceLayerException.CREAR_VENTA_EXCEPTION);
        }
    }
}
