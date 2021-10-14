package com.ar.sgi.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

import org.apache.commons.lang3.StringUtils;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.Marca;
import com.ar.sgi.model.Paginator;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

/**
 * Clase encargada de la capa de presistencia de Marca 
 * @see com.ar.sgi.model.Marca
 * @author 	Alejandro Toigo
 * @since 	Octubre 2020
 * @version 1.0
 *
 */
@ApplicationScoped
public class MarcaRepository implements PanacheRepositoryBase<Marca, Long>{
	
	private static final Logger LOGGER = Logger.getLogger(MarcaRepository.class.getName());
	

	/**
	 * Metodo encargado de buscar en BD las marcas
	 * @param pagina: numero de paginas
	 * @param items: numero de elementos por pagina
	 * @param marca: marca que se utilizara como filtrado
	 * @return 	Paginator: lista de marcas paginada
	 */
	public Paginator<Marca> listar(Integer pagina, Integer items, Marca marca) throws PersistanceLayerException {
		Paginator<Marca> paginator = null;

		try {
			PanacheQuery<Marca> query = null;
			if(marca != null && marca.getId() != null && marca.getId() > 0) {
				if(StringUtils.isNotBlank(marca.getDescripcion())) {
					Map<String, Object> params = new HashMap<>();
					params.put("descripcion", "%" + marca.getDescripcion().toUpperCase() + "%");
					params.put("estado", marca.getEstado());
					query = this.find("upper(descripcion) LIKE :descripcion and estado = :estado", params);
				}
			} else if(marca != null && marca.getEstado() != null) {
					query = this.find("estado = ?1", marca.getEstado());
			} else {
				query = this.findAll();
			}

			if (query != null) {
				query.page(Page.of(pagina, items));
				List<Marca> marcas = query.list();
				paginator = marcas != null && !marcas.isEmpty() ? new Paginator<>(marcas, query.count()) : null;
			}
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new PersistanceLayerException(PersistanceLayerException.LISTAR_MARCA_EXCEPTION);
		}
		return paginator;
	}

	/**
	 * Metodo encargado de persistir una marca
	 * @param marca: marca a persistir
	 * @throws PersistanceLayerException: excepcion en la capa de persistencia
	 */
	public void crear(Marca marca) throws PersistanceLayerException {
		try {
			marca.setId(null);
			marca.setEstado(true);
			this.persist(marca);
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new PersistanceLayerException(PersistanceLayerException.CREAR_MARCA_EXCEPTION);
		}
	}
	
	/**
	 * Metodo encargado de actualizar una marca
	 * @param marca: marca a actualizar
	 * @throws PersistanceLayerException: excepcion en la capa de persistencia
	 */
	public void actualizar(Marca marca) throws PersistanceLayerException {
		try {
			this.update("descripcion = ?1 where id = ?2", marca.getDescripcion(), marca.getId());
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new PersistanceLayerException(PersistanceLayerException.ACTUALIZAR_MARCA_EXCEPTION);
		}
	}

	/**
	 * Metodo encargado de cambiar el estado de una marca
	 * @param id: identificador de la marca
	 * @throws PersistanceLayerException: excepcion en la capa de persistencia
	 */
	public void cambiarEstado(Long id) throws PersistanceLayerException {
		try {
			Marca marca = this.findById(id);
			this.update("estado = ?1 where id = ?2", !marca.getEstado(), id);
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new PersistanceLayerException(PersistanceLayerException.ACTUALIZAR_MARCA_EXCEPTION);
		}
	}
}
