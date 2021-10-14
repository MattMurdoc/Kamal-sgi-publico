package com.ar.sgi.services;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Tarjeta;
import com.ar.sgi.repository.TarjetaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class TarjetaService {

    @Inject
    TarjetaRepository tarjetaRepository;

    public Paginator<Tarjeta> listarTodo(int pagina, int items) throws Exception {
        try {
            return tarjetaRepository.listar(pagina, items);
        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            throw new ServiceLayerException(ServiceLayerException.LISTAR_TARJETA_EXCEPTION);
        }
    }

    @Transactional
    public void agregar(Tarjeta tarjeta) throws ServiceLayerException {
        try {
            tarjetaRepository.persist(tarjeta);
        } catch (Exception e) {
            throw new ServiceLayerException(ServiceLayerException.CREAR_TARJETA_EXCEPTION);
        }
    }
}
