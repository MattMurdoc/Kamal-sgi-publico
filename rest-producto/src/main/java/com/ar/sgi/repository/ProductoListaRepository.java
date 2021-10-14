package com.ar.sgi.repository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.ProductoLista;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

/**
 * Clase encargada de la capa de presistencia de las listas de producto  
 * @see com.ar.sgi.model.ProductoLista
 * @author 	Alejandro Toigo
 * @since 	Octubre 2020
 * @version 1.0
 *
 */
@ApplicationScoped
public class ProductoListaRepository implements PanacheRepositoryBase<ProductoLista, Long> {
	private static final Logger LOGGER = Logger.getLogger(ProductoListaRepository.class.getName());
	

	/**
	 * Metodo encargado de buscar en BD las listas de producto 
	 * @return 	List: lista de las listas de producto
	 */
	public List<ProductoLista> listar() throws PersistanceLayerException {
		try {
			return this.listAll();
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new PersistanceLayerException(PersistanceLayerException.LISTAR_PRODUCTO_LISTA_EXCEPTION);
		}
	}

	/**
	 * Metodo encargado de persistir una lista de producto 
	 * @param productoLista: producto lista a actualizar
	 * @throws PersistanceLayerException: error en la capa de persistencia
	 */
	public void crear(ProductoLista productoLista) throws PersistanceLayerException {
		try {
			productoLista.setEstado(true);
			this.persist(productoLista);
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new PersistanceLayerException(PersistanceLayerException.CREAR_PRODUCTO_LISTA_EXCEPTION);
		}
	}
	
	/**
	 * Metodo encargado de actualizar una lista de producto 
	 * @param productoLista: producto lista a actualizar
	 * @throws PersistanceLayerException: error en la capa de persistencia
	 */
	public void actualizar(ProductoLista productoLista) throws PersistanceLayerException {
		try {
			Object[] params = { productoLista.getDescripcion(), productoLista.getId() };
			this.update("descripcion = ?1 where id = ?2", params);
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new PersistanceLayerException(PersistanceLayerException.ACTUALIZAR_PRODUCTO_LISTA_EXCEPTION);
		}
	}

	/**
	 * Metodo encargado de cambiar el estado de una lista de producto  
	 * @param id: identificador del producto lista
	 * @throws PersistanceLayerException: error en la capa de persistencia
	 */
	public void cambiarEstado(Long id) throws PersistanceLayerException {
		try {
			ProductoLista productoLista = this.findById(id);
			this.update("estado = ?1 where id = ?2", !productoLista.getEstado(), id);
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new PersistanceLayerException(PersistanceLayerException.ACTUALIZAR_PRODUCTO_LISTA_EXCEPTION);
		}
	}
}
