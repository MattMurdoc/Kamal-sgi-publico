package com.ar.sgi.services;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Departamento;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Seccion;
import com.ar.sgi.repository.SeccionRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class SeccionService {

    private static final Logger LOGGER = Logger.getLogger(SeccionService.class.getName());

    @Inject
    SeccionRepository seccionRepository;

    public Paginator<Seccion> listarTodo(int pagina, int items, Departamento departamento, boolean estado) throws Exception {

        try {
            return seccionRepository.listarPaginado(pagina, items, departamento, estado);
        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_SECCION_EXCEPTION);
        }
    }

    public Paginator<Seccion> listarTodoByDepartamento(int pagina, int item, Long departamentoId) throws Exception {
        try {
            List<Seccion> seccion = seccionRepository.listar(pagina, item, departamentoId);
            return new Paginator<>(seccion, seccionRepository.count("seccion", departamentoId));
        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_SECCION_EXCEPTION);
        }

    }

    public List<Seccion> listarByDepartamento(Departamento departamento) throws Exception {
        try {
            return seccionRepository.find("departamento", departamento).list();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_SECCION_EXCEPTION);
        }
    }

    public Seccion buscarSeccion(Long seccionId) throws Exception {
        try {
            return seccionRepository.findById(seccionId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.BUSCAR_SECCION_EXCEPTION);
        }
    }

    @Transactional
    public void agregar(Seccion seccion) throws ServiceLayerException {
        try {
            seccion.setId(null);
            seccion.setEstado(true);
            seccionRepository.persist(seccion);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.CREAR_SECCION_EXCEPTION);
        }
    }

    @Transactional
    public void actualizar(Seccion seccion) throws ServiceLayerException {
        try {
            seccionRepository.getEntityManager().merge(seccion);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_SECCION_EXCEPTION);
        }
    }

    /**
     * Metodo que cambia el estado de una SECCION
     *
     * @param id: identificador de la seccion
     * @throws PersistanceLayerException: error en la capa de persistencia
     * @throws ServiceLayerException:     error en la capa de servicio
     * @since Octubre 2020
     */
    @Transactional
    public void cambiarEstado(Long id) throws PersistanceLayerException, ServiceLayerException {
        try {
            seccionRepository.cambiarEstado(id);
        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_SECCION_EXCEPTION);
        }
    }
}