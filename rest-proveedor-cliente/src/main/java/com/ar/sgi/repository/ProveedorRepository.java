package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.*;
import com.ar.sgi.model.dto.ProveedorFiltroDTO;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import org.junit.platform.commons.util.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class ProveedorRepository implements PanacheRepositoryBase<Proveedor, Long> {

    Logger LOGGER = Logger.getLogger(ProveedorRepository.class.getName());

    @Inject
    RubroRepository rubroRepository;
    @Inject
    ProvinciaRepository provinciaRepository;
    @Inject
    LocalidadRepository localidadRepository;

    public List<Proveedor> listar(int pagina, int items)throws PersistanceLayerException {
        try{
            PanacheQuery<Proveedor> query = null;
            query = this.findAll();
            query.page(pagina, items);
            return query.list();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_PROVEEDOR_EXCEPTION);
        }
    }

    public Paginator<Proveedor> listarFiltro(Integer pagina, Integer items, ProveedorFiltroDTO proveedorFiltroDTO) throws PersistanceLayerException {
        Paginator<Proveedor> paginator = null;
        try {
            PanacheQuery<Proveedor> query = null;
            if (proveedorFiltroDTO != null) {
                Map<String, Object> params = new HashMap<>();

                String consulta = "";

                if (StringUtils.isNotBlank(proveedorFiltroDTO.getCodigo())) {
                    params.put("codigo", proveedorFiltroDTO.getCodigo().trim().toUpperCase());
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "upper(codigo) = :codigo ";
                }

                if (StringUtils.isNotBlank(proveedorFiltroDTO.getCuit())) {
                    params.put("cuit", proveedorFiltroDTO.getCuit().trim().toUpperCase());
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "upper(cuit) = :cuit ";
                }

                if (StringUtils.isNotBlank(proveedorFiltroDTO.getRazonSocial())) {
                    params.put("razonSocial", proveedorFiltroDTO.getRazonSocial().trim().toUpperCase());
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "upper(razonSocial) = :razonSocial ";
                }

                if (StringUtils.isNotBlank(proveedorFiltroDTO.getNombreFantasia())) {
                    params.put("nombreFantasia", proveedorFiltroDTO.getNombreFantasia().trim().toUpperCase());
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "upper(nombreFantasia) = :nombreFantasia ";
                }

                if (proveedorFiltroDTO.getRubro() != null && proveedorFiltroDTO.getRubro() > 0) {
                    Rubro rubro = rubroRepository.findById(proveedorFiltroDTO.getRubro());
                    params.put("rubro", rubro);
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "(rubro) = :rubro ";
                }

                if (proveedorFiltroDTO.getProvincia() != null && proveedorFiltroDTO.getProvincia() > 0) {

                    if (proveedorFiltroDTO.getLocalidad() != null && proveedorFiltroDTO.getLocalidad() > 0) {
                        Localidad localidad = localidadRepository.findById(proveedorFiltroDTO.getLocalidad());
                        params.put("localidad", localidad);
                        if (!consulta.isEmpty()) {
                            consulta += " and ";
                        }
                        consulta += "(localidad) = :localidad ";
                    }
                    else {
                        List<Localidad> localidades = localidadRepository.listarByProvincia(provinciaRepository.findById(proveedorFiltroDTO.getProvincia()));
                        params.put("localidades", localidades);
                        if (!consulta.isEmpty()) {
                            consulta += " and ";
                        }
                        consulta += "(localidad) in ( :localidades ) ";
                    }
                }

                query = this.find(consulta, params);

            } else {
                query = this.findAll();
            }
            query.page(Page.of(pagina, items));
            List<Proveedor> proveedores = query.list();

            paginator = proveedores != null && !proveedores.isEmpty() ? new Paginator<Proveedor>(proveedores, query.count()) : null;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_PROVEEDOR_EXCEPTION);
        }
        return paginator;

    }

    public void crear(Proveedor proveedor) throws PersistanceLayerException {
        try {
            this.persist(proveedor);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new PersistanceLayerException(PersistanceLayerException.CREAR_PROVEEDOR_EXCEPTION);
        }
    }

}
