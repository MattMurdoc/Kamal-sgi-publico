package com.ar.sgi.services;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Familia;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.SubFamilia;
import com.ar.sgi.repository.SubFamiliaRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class SubFamiliaService {

    private static final Logger LOGGER = Logger.getLogger(SubFamiliaService.class.getName());

    @Inject
    SubFamiliaRepository subFamiliaRepository;

    public Paginator<SubFamilia> listarPaginatedByFamiliaId(int pagina, int items, Familia familia, Boolean estado) throws Exception {
        try {
            return subFamiliaRepository.listar(pagina, items, familia, estado);
        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_SUBFAMILIA_EXCEPTION);
        }
    }

    public List<SubFamilia> listarByFamiliaId(Familia familia) throws Exception {
        try {
            return subFamiliaRepository.find("familia", familia).list();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_SUBFAMILIA_EXCEPTION);
        }
    }

    public SubFamilia buscarSubfamilia(Long subfamiliaId) throws Exception {
        try {
            return subFamiliaRepository.findById(subfamiliaId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.BUSCAR_SUBFAMILIA_EXCEPTION);
        }
    }

    @Transactional
    public void agregar(SubFamilia subFamilia) throws ServiceLayerException {
        try {
            subFamilia.setId(null);
            subFamilia.setEstado(true);
            subFamiliaRepository.persist(subFamilia);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.CREAR_SUBFAMILIA_EXCEPTION);
        }
    }

    @Transactional
    public void actualizar(SubFamilia subFamilia) throws ServiceLayerException {
        try {
            subFamiliaRepository.getEntityManager().merge(subFamilia);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_SUBFAMILIA_EXCEPTION);
        }
    }

    /**
     * Metodo que cambia el estado de una subfamilia
     *
     * @param id: identificador de la subfamilia
     * @throws PersistanceLayerException: error en la capa de persistencia
     * @throws ServiceLayerException:     error en la capa de servicio
     * @since Octubre 2020
     */
    @Transactional
    public void cambiarEstado(Long id) throws PersistanceLayerException, ServiceLayerException {
        try {
            subFamiliaRepository.cambiarEstado(id);
        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_SUBFAMILIA_EXCEPTION);
        }
    }
}
