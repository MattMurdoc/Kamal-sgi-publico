package com.ar.sgi.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.ar.sgi.model.*;
import com.ar.sgi.model.dto.ProductoFiltroDTO;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import org.apache.commons.lang3.StringUtils;

import com.ar.sgi.exceptions.PersistanceLayerException;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encargada de la capa de presistencia de Producto
 *
 * @author Alejandro Toigo
 * @version 1.0
 * @see com.ar.sgi.model.Producto
 * @since Octubre 2020
 */

@ApplicationScoped
public class ProductoRepository implements PanacheRepositoryBase<Producto, Long> {


    private static final Logger LOGGER = Logger.getLogger(ProductoRepository.class.getName());

    @Inject
    MarcaRepository marcaRepository;
    @Inject
    DepartamentoRepository departamentoRepository;
    @Inject
    SeccionRepository seccionRepository;
    @Inject
    FamiliaRepository familiaRepository;
    @Inject
    SubFamiliaRepository subFamiliaRepository;
    @Inject
    UnidadRepository unidadRepository;

    /**
     * Metodo encargado de persistir un producto
     *
     * @param producto: producto a persistir
     * @throws PersistanceLayerException: error en la capa de persistencia
     */
    public void crear(Producto producto) throws PersistanceLayerException {
        try {
            producto.setEstado(true);
            this.persist(producto);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new PersistanceLayerException(PersistanceLayerException.CREAR_PRODUCTO_EXCEPTION);
        }
    }

    public Paginator<Producto> listar(Integer pagina, Integer items, ProductoFiltroDTO productoFiltroDTO) throws PersistanceLayerException {
        Paginator<Producto> paginator;
        try {
            PanacheQuery<Producto> query;
            if (productoFiltroDTO != null) {
                Map<String, Object> params = new HashMap<>();
                String consulta = "";
                if (StringUtils.isNotBlank(productoFiltroDTO.getDescripcion())) {
                    params.put("descripcion", "%" + productoFiltroDTO.getDescripcion().toUpperCase() + "%");
                    consulta += "upper(descripcion) like :descripcion ";
                }
                if (StringUtils.isNotBlank(productoFiltroDTO.getCodigo())) {
                    params.put("codigo", productoFiltroDTO.getCodigo().trim().toUpperCase());
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "upper(codigo) = :codigo ";
                }
                if (StringUtils.isNotBlank(productoFiltroDTO.getCodigoInterno())) {
                    params.put("codigoInterno", productoFiltroDTO.getCodigoInterno().trim().toUpperCase());
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "upper(codigoInterno) = :codigoInterno ";
                }
                if (StringUtils.isNotBlank(productoFiltroDTO.getMarca())) {
                    List<Marca> marca = marcaRepository.find("upper(marca) LIKE ?1", productoFiltroDTO.getMarca().trim().toUpperCase()).list();
                    params.put("marca", marca);
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "upper(marca) in ( :marca ) ";
                }
                if (productoFiltroDTO.getDepartamento() != null && productoFiltroDTO.getDepartamento() > 0) {
                    Departamento departamento = departamentoRepository.findById(productoFiltroDTO.getDepartamento());
                    params.put("departamento", departamento);
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "(departamento) = :departamento ";
                }
                if (productoFiltroDTO.getSeccion() != null && productoFiltroDTO.getSeccion() > 0) {
                    Seccion seccion = seccionRepository.findById(productoFiltroDTO.getSeccion());
                    params.put("seccion", seccion);
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "(seccion) = :seccion ";
                }
                if (productoFiltroDTO.getFamilia() != null && productoFiltroDTO.getFamilia() > 0) {
                    Familia familia = familiaRepository.findById(productoFiltroDTO.getFamilia());
                    params.put("familia", familia);
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "(familia) = :familia ";
                }
                if (productoFiltroDTO.getSubFamilia() != null && productoFiltroDTO.getSubFamilia() > 0) {
                    SubFamilia subFamilia = subFamiliaRepository.findById(productoFiltroDTO.getSubFamilia());
                    params.put("subFamilia", subFamilia);
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "(subFamilia) = :subFamilia ";
                }
                if (productoFiltroDTO.getUnidad() != null && productoFiltroDTO.getUnidad() > 0) {
                    Unidad unidad = unidadRepository.findById(productoFiltroDTO.getUnidad());
                    params.put("unidad", unidad);
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "(unidad) = :unidad ";
                }
                if (productoFiltroDTO.getGramaje() > 0) {
                    params.put("gramaje", productoFiltroDTO.getGramaje());
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "(gramaje) = :gramaje ";
                }
                query = this.find(consulta, params);

            } else {
                query = this.findAll();
            }
            query.page(Page.of(pagina, items));
            List<Producto> productos = query.list();

            paginator = productos != null && !productos.isEmpty() ? new Paginator<>(productos, query.count()) : null;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_PRODUCTO_EXCEPTION);
        }
        return paginator;

    }
    public void modificarPrecios(ProductoListaPrecio productoListaPrecio) throws PersistanceLayerException {
        try {
            this.getEntityManager().merge(productoListaPrecio);
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new PersistanceLayerException(PersistanceLayerException.ACTUALIZAR_MARCA_EXCEPTION);
        }
    }

    /**
     * Metodo encargado de cambiar el estado de un producto
     * @param id: identificador del producto
     * @throws PersistanceLayerException: error en la capa de persistencia
     */
    public void cambiarEstado(Long id) throws PersistanceLayerException {
        try {
            Producto producto = this.findById(id);
            this.update("estado = ?1 where id = ?2", !producto.getEstado(), id);
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new PersistanceLayerException(PersistanceLayerException.ACTUALIZAR_FAMILIA_EXCEPTION);
        }
    }

}
