package com.ar.sgi.service;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Contacto;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Proveedor;
import com.ar.sgi.model.dto.ProveedorFiltroDTO;
import com.ar.sgi.repository.ContactoRepository;
import com.ar.sgi.repository.ProveedorRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class ProveedorService {

    private static Logger LOGGER = Logger.getLogger(ProveedorService.class.getName());

    @Inject
    ProveedorRepository proveedorRepository;
    @Inject
    ContactoRepository contactoRepository;

    public Paginator<Proveedor>listarTodo(int pagina, int items)throws Exception {
        try {
            return new Paginator<>(proveedorRepository.listar(pagina, items), proveedorRepository.count());
        }catch (PersistanceLayerException pe){
            throw pe;
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_PROOEEDOR_EXCEPTION);
        }
    }

    public Proveedor getById(Long id) throws ServiceLayerException {
        try {
            return proveedorRepository.findById(id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.BUSCAR_PROVEEDOR_EXCEPTION);
        }
    }

    @Transactional
    public void agregar(Proveedor proveedor) throws ServiceLayerException {
        try {
            proveedor.setId(null);
            if(proveedor.getRubro() != null && proveedor.getRubro().getId() == 0) {
                proveedor.setRubro(null);
            }
            if(proveedor.getProvincia() != null && proveedor.getProvincia().getId() == 0) {
                proveedor.setProvincia(null);
            }
            if(proveedor.getLocalidad() != null && proveedor.getLocalidad().getId() == 0) {
                proveedor.setLocalidad(null);
            }
            proveedorRepository.crear(proveedor);
            for (Contacto contacto : proveedor.getContactos()) {
                if(contacto.getId() == 0) {
                    contacto.setId(null);
                }
                contacto.setProveedor(proveedor);
            }
            contactoRepository.persist(proveedor.getContactos());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.CREAR_PROVEEDOR_EXCEPTION);
        }
    }

    @Transactional
    public void actualizar(Proveedor proveedor) throws ServiceLayerException {
        try {
            proveedorRepository.getEntityManager().merge(proveedor);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_PROVEEDOR_EXCEPTION);
        }
    }

    public Paginator<Proveedor> listar(Integer pagina, Integer items, ProveedorFiltroDTO proveedorFiltroDTO) throws ServiceLayerException {
        Paginator<Proveedor> paginator = null;
        try {
            paginator = proveedorRepository.listarFiltro(pagina, items, proveedorFiltroDTO);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_PROOEEDOR_EXCEPTION);
        }
        return paginator;
    }
}
