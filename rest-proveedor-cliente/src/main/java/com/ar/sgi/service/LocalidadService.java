package com.ar.sgi.service;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Localidad;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Provincia;
import com.ar.sgi.repository.LocalidadRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class LocalidadService {

    private static Logger LOGGER = Logger.getLogger(LocalidadService.class.getName());

    @Inject
    LocalidadRepository localidadRepository;

    public Paginator<Localidad>listarTodo(int pagina, int items)throws Exception {
        try {
            return new Paginator<>(localidadRepository.listar(pagina, items), localidadRepository.count());
        }catch (PersistanceLayerException pe){
            throw pe;
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_LOCALIDAD_EXCEPTION);
        }
    }

    public List<Localidad> listarTodoByProvincia(Provincia provincia) throws Exception {
        try{
            return localidadRepository.listarByProvincia(provincia);

        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_LOCALIDAD_EXCEPTION);
        }

    }

    public Localidad getById(Long id) throws ServiceLayerException {
        try {
            return localidadRepository.findById(id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.BUSCAR_LOCALIDAD_EXCEPTION);
        }
    }

    @Transactional
    public void agregar(Localidad localidad) throws ServiceLayerException {
        try {
            localidad.setId(null);
            localidadRepository.persist(localidad);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.CREAR_LOCALIDAD_EXCEPTION);
        }
    }

    @Transactional
    public void actualizar(Localidad localidad) throws ServiceLayerException {
        try {
            localidadRepository.getEntityManager().merge(localidad);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_LOCALIDAD_EXCEPTION);
        }
    }
}
