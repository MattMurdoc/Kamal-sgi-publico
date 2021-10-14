package com.ar.sgi.services;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Permiso;
import com.ar.sgi.model.Rol;
import com.ar.sgi.repository.PermisoRepository;
import com.ar.sgi.repository.RolRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@ApplicationScoped
public class PermisoService {

    private static Logger LOGGER = Logger.getLogger(PermisoService.class.getName());

    @Inject
    PermisoRepository permisoRepository;

    public Paginator<Permiso> listarTodo(int pagina, int items) throws Exception {
        try {
            return permisoRepository.listar(pagina, items);
        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new ServiceLayerException(ServiceLayerException.LISTAR_PERMISO_EXCEPTION);
        }
    }

    public Permiso buscarPermiso(Long permisoId) throws Exception {
        try {
            return permisoRepository.findById(permisoId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new ServiceLayerException(ServiceLayerException.BUSCAR_PERMISO_EXCEPTION);
        }
    }

    @Transactional
    public void agregar(Permiso permiso) throws ServiceLayerException {
        try {
            permisoRepository.persist(permiso);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new ServiceLayerException(ServiceLayerException.CREAR_PERMISO_EXCEPTION);
        }
    }

    @Transactional
    public void actualizar(Permiso permiso) throws ServiceLayerException {
        try {
            permisoRepository.getEntityManager().merge(permiso);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_PERMISO_EXCEPTION);
        }
    }

    public List<Permiso> listarByRolId(Rol rol) throws Exception {
        try {
            return permisoRepository.find("ROL", rol).list();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new ServiceLayerException(ServiceLayerException.LISTAR_ROL_EXCEPTION);
        }

    }

    @Transactional
    public void eliminarPermiso(Long id) throws PersistanceLayerException, ServiceLayerException {
        try {
            permisoRepository.eliminarPermiso(id);
        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_PERMISO_EXCEPTION);
        }
    }
}
