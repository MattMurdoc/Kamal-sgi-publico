package com.ar.sgi.service;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Localidad;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Provincia;
import com.ar.sgi.model.Rubro;
import com.ar.sgi.repository.LocalidadRepository;
import com.ar.sgi.repository.RubroRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class RubroService {

    private static Logger LOGGER = Logger.getLogger(ProveedorService.class.getName());

    @Inject
    RubroRepository rubroRepository;

    public Paginator<Rubro>listarTodo(int pagina, int items)throws Exception {
        try {
            return new Paginator<>(rubroRepository.listar(pagina, items), rubroRepository.count());
        }catch (PersistanceLayerException pe){
            throw pe;
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_RUBRO_EXCEPTION);
        }
    }

    public Rubro getById(Long id) throws ServiceLayerException {
        try {
            return rubroRepository.findById(id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.BUSCAR_RUBRO_EXCEPTION);
        }
    }

    @Transactional
    public void agregar(Rubro rubro) throws ServiceLayerException {
        try {
            rubro.setId(null);
            rubro.setEstado(true);
            rubroRepository.persist(rubro);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.CREAR_RUBRO_EXCEPTION);
        }
    }

    @Transactional
    public void actualizar(Rubro rubro) throws ServiceLayerException {
        try {
            rubroRepository.getEntityManager().merge(rubro);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_RUBRO_EXCEPTION);
        }
    }

    public List<Rubro> listar() throws ServiceLayerException {
        try {
            return rubroRepository.listAll();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_RUBRO_EXCEPTION);
        }
    }
}
