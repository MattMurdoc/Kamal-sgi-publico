package com.ar.sgi.services;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Departamento;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.repository.DepartamentoRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class DepartamentoService {

    private static Logger LOGGER = Logger.getLogger(DepartamentoService.class.getName());

    @Inject
    DepartamentoRepository departamentoRepository;

    public Paginator<Departamento> listarPaginado(int pagina, int items) throws Exception {

        try {
            return new Paginator<>(departamentoRepository.listar(pagina, items), departamentoRepository.count());
        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_DEPARTAMENTO_EXCEPTION);
        }
    }

    public List<Departamento> listarTodo() throws Exception {

        try {
            return departamentoRepository.listAll();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_DEPARTAMENTO_EXCEPTION);
        }
    }

    public Departamento buscarDepartamento (Long departamentoId) throws Exception {
        try{
            return  departamentoRepository.findById(departamentoId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.BUSCAR_DEPARTAMENTO_EXCEPTION);
        }
    }

    @Transactional
    public void agregar(Departamento departamento) throws ServiceLayerException{
        try {
            departamento.setId(null);
            departamento.setEstado(true);
            departamentoRepository.persist(departamento);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.CREAR_DEPARTAMENTO_EXCEPTION);
        }
    }

    @Transactional
    public void actualizar(Departamento departamento) throws ServiceLayerException{
        try {
            departamentoRepository.getEntityManager().merge(departamento);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_DEPARTAMENTO_EXCEPTION);
        }
    }

    /**
     * Metodo que cambia el estado de un DEPARTAMENTO
     * @param id
     * @version 1.0
     * @throws PersistanceLayerException
     * @throws ServiceLayerException
     * @since 	Octubre 2020
     */
    @Transactional
    public void cambiarEstado(Long id) throws PersistanceLayerException, ServiceLayerException {
        try {
            departamentoRepository.cambiarEstado(id);;
        } catch(PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_DEPARTAMENTO_EXCEPTION);
        }
    }
}


