package com.ar.sgi.services;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.FormaPago;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Venta;
import com.ar.sgi.repository.FormaPagoRepository;
import com.ar.sgi.repository.VentaRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;

public class FormaPagoService {

    @Inject
    FormaPagoRepository formaPagoRepository;

    public Paginator<FormaPago> listarTodo(int pagina, int items) throws Exception {
        try {
            return formaPagoRepository.listar(pagina, items);
        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            throw new ServiceLayerException(ServiceLayerException.LISTAR_FORMA_PAGO_EXCEPTION);
        }
    }

    @Transactional
    public void agregar(FormaPago formaPago) throws ServiceLayerException {
        try {
            formaPagoRepository.persist(formaPago);
        } catch (Exception e) {
            throw new ServiceLayerException(ServiceLayerException.CREAR_FORMA_PAGO_EXCEPTION);
        }
    }
}
