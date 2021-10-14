package com.ar.sgi.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

import com.ar.sgi.model.Departamento;
import com.ar.sgi.model.Marca;
import com.ar.sgi.model.dto.UnidadDTO;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Unidad;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

/**
 * Clase encargada de la capa de presistencia de Unidad 
 * @see com.ar.sgi.model.Unidad
 * @author 	Alejandro Toigo
 * @since 	Octubre 2020
 * @version 1.0
 *
 */
@ApplicationScoped
public class UnidadRepository implements PanacheRepositoryBase<Unidad, Long> {
	private static final Logger LOGGER = Logger.getLogger(UnidadRepository.class.getName());

	/**
	 * Metodo encargado de buscar en BD las unidades 
	 * @param pagina: numero de paginas
	 * @param items: numero de elementos por pagina
	 * @return 	Paginator: lista de unidades paginada
	 */
	public Paginator<Unidad> listarFiltro(Integer pagina, Integer items, UnidadDTO unidadDTO) throws PersistanceLayerException {
		try {
			PanacheQuery<Unidad> query;
			if(StringUtils.isNotBlank(unidadDTO.getDescripcion())) {
				Map<String, Object> params = new HashMap<>();
				params.put("descripcion", "%" + unidadDTO.getDescripcion().toUpperCase() + "%");
				params.put("estado", unidadDTO.getEstado());
				query = this.find("upper(descripcion) LIKE :descripcion and estado = :estado", params);
			} else {
				query = this.find("estado = ?1", unidadDTO.getEstado());
			}
			query.page(Page.of(pagina, items));
			List<Unidad> unidades = query.list();

			return unidades != null && !unidades.isEmpty() ? new Paginator<>(unidades, query.count()) : null;
		} catch(Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new PersistanceLayerException(PersistanceLayerException.LISTAR_MARCA_EXCEPTION);
		}
	}

	public List<Unidad> listarUnidades(int pagina, int items) throws PersistanceLayerException {
		try {
			if(pagina >= 0 && items >= 0){
				PanacheQuery<Unidad> query = this.findAll();
				query.page(pagina, items);
				return query.list();
			}else{
				PanacheQuery<Unidad> query = this.findAll();
				//query.list();
				return query.list();
			}
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new PersistanceLayerException(PersistanceLayerException.LISTAR_UNIDAD_EXCEPTION);
		}
	}

	/**
	 * Metodo encargado de persistir una unidad
	 * @param unidad 
	 * @throws PersistanceLayerException
	 */
	public void crear(Unidad unidad) throws PersistanceLayerException {
		try {
			unidad.setEstado(true);
			this.persist(unidad);
		} catch(Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new PersistanceLayerException(PersistanceLayerException.CREAR_UNIDAD_EXCEPTION);
		}
	}
	
	/**
	 * Metodo encargado de actualizar una unidad 
	 * @param unidad 
	 * @throws PersistanceLayerException
	 */
	public void actualizar(Unidad unidad) throws PersistanceLayerException {
		try {
			Object[] params = { unidad.getDescripcion(), unidad.getId() };
			this.update("descripcion = ?1 where id = ?2", params);
		} catch(Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new PersistanceLayerException(PersistanceLayerException.ACTUALIZAR_UNIDAD_EXCEPTION);
		}
	}

	/**
	 * Metodo encargado de cambiar el estado de una unidad 
	 * @param id
	 * @throws PersistanceLayerException
	 */
	public void cambiarEstado(Long id) throws PersistanceLayerException {
		try {
			Unidad unidad = this.findById(id);
			this.update("estado = ?1 where id = ?2", !unidad.getEstado(), id);
		} catch(Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new PersistanceLayerException(PersistanceLayerException.ACTUALIZAR_UNIDAD_EXCEPTION);
		}
	}

}
