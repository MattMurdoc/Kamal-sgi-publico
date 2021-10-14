package com.ar.sgi.services;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Familia;
import com.ar.sgi.model.Seccion;
import com.ar.sgi.repository.FamiliaRepository;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class FamiliaService {

    private static Logger LOGGER = Logger.getLogger(FamiliaService.class.getName());

    @Inject
    FamiliaRepository familiaRepository;

    public Paginator<Familia> listarTodoBySeccion(int pagina, int item, Seccion seccion, Boolean estado) throws Exception {
        try {
            return familiaRepository.listar(pagina, item, seccion, estado);
        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_FAMILIA_EXCEPTION);
        }

    }

    public List<Familia> listarBySeccion(Seccion seccion) throws Exception {
        try {
            return familiaRepository.find("seccion", seccion).list();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_FAMILIA_EXCEPTION);
        }
    }

    public Familia buscarFamilia(Long familiaId) throws Exception {
        try {
            return familiaRepository.findById(familiaId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.BUSCAR_FAMILIA_EXCEPTION);
        }
    }

    @Transactional
    public void agregar(Familia familia) throws ServiceLayerException {
        try {
            familia.setId(null);
            familia.setEstado(true);
            familiaRepository.persist(familia);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.CREAR_FAMILIA_EXCEPTION);
        }
    }

    @Transactional
    public void actualizar(Familia familia) throws ServiceLayerException {
        try {
            familiaRepository.getEntityManager().merge(familia);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_FAMILIA_EXCEPTION);
        }
    }

    /**
     * Metodo que cambia el estado de una FAMILIA
     *
     * @param id
     * @throws PersistanceLayerException
     * @throws ServiceLayerException
     * @version 1.0
     * @since Octubre 2020
     */
    @Transactional
    public void cambiarEstado(Long id) throws PersistanceLayerException, ServiceLayerException {
        try {
            familiaRepository.cambiarEstado(id);
            ;
        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_FAMILIA_EXCEPTION);
        }
    }

}


