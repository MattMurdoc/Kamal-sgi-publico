package com.ar.sgi.services;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Rol;
import com.ar.sgi.repository.RolRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class RolService {

    private static Logger LOGGER = Logger.getLogger(RolService.class.getName());

    @Inject
    RolRepository rolRepository;

    public List<Rol> listarTodo() throws Exception {
        try {
            return rolRepository.listAll();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new ServiceLayerException(ServiceLayerException.LISTAR_ROL_EXCEPTION);
        }
    }

    public Rol buscarRol(Long rolId) throws Exception {
        try {
            return rolRepository.findById(rolId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new ServiceLayerException(ServiceLayerException.BUSCAR_ROL_EXCEPTION);
        }
    }

    public Paginator<Rol>listarTodoPag(int pagina, int items)throws Exception {
        try {
            return new Paginator<>(rolRepository.listar(pagina, items), rolRepository.count());
        }catch (PersistanceLayerException pe){
            throw pe;
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_ROL_EXCEPTION);
        }
    }
}
