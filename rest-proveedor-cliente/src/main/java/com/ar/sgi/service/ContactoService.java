package com.ar.sgi.service;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.*;
import com.ar.sgi.repository.ContactoRepository;
import com.ar.sgi.repository.ProveedorRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class ContactoService {

    private static Logger LOGGER = Logger.getLogger(ContactoService.class.getName());

    @Inject
    ContactoRepository contactoRepository;

    public Paginator<Contacto>listarTodo(int pagina, int items)throws Exception {
        try {
            return new Paginator<>(contactoRepository.listar(pagina, items), contactoRepository.count());
        }catch (PersistanceLayerException pe){
            throw pe;
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_CONTACTO_EXCEPTION);
        }
    }

    public Contacto getById(Long id) throws ServiceLayerException {
        try {
            return contactoRepository.findById(id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.BUSCAR_CONTACTO_EXCEPTION);
        }
    }

    @Transactional
    public void agregar(Contacto contacto) throws ServiceLayerException {
        try {
            contacto.setId(null);
            contactoRepository.persist(contacto);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.CREAR_CONTACTO_EXCEPTION);
        }
    }

    @Transactional
    public void actualizar(Contacto contacto) throws ServiceLayerException {
        try {
            contactoRepository.getEntityManager().merge(contacto);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_CONTACTO_EXCEPTION);
        }
    }

    public List<Contacto> listarTodoByProveedor(Proveedor proveedor) throws Exception {
        try{
            return contactoRepository.listarByProveedor(proveedor);

        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            throw new ServiceLayerException(ServiceLayerException.LISTAR_LOCALIDAD_EXCEPTION);
        }

    }
}
