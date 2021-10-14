package com.ar.sgi.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.ProductoLista;
import com.ar.sgi.repository.ProductoListaRepository;

/**
 * Clase encargada de la gesti\u00F3n de las listas de producto 
 * @see com.ar.sgi.model.ProductoLista
 * @author 	Alejandro Toigo
 * @since 	Octubre 2020
 * @version 1.0
 *
 */
@ApplicationScoped
public class ProductoListaService {
	private static final Logger LOGGER = Logger.getLogger(ProductoListaService.class.getName());

	@Inject
	ProductoListaRepository productoListaRepository;
	
	/**
	 * Metodo que devuelve una lista con las listas de producto
	 * @return 	List: lista de lista de producto
	 * @throws PersistanceLayerException: excepcion en la capa de persistencia 
	 * @throws ServiceLayerException: excepcion en la capa de servicio
	 * @version 1.0
	 * @since 	Octubre 2020
	 */
	public List<ProductoLista> listar() throws PersistanceLayerException, ServiceLayerException {
		List<ProductoLista> listas = null;
		try {
			listas = productoListaRepository.listar();
		} catch(PersistanceLayerException pe) {
			throw pe;
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new ServiceLayerException(ServiceLayerException.LISTAR_PRODUCTO_LISTA_EXCEPTION);
		}
		return listas;
	}

	/**
	 * Metodo que agrega una lista de producto 
	 * @param productoLista {@link com.ar.sgi.model.ProductoLista}
	 * @version 1.0
	 * @throws PersistanceLayerException 
	 * @throws ServiceLayerException 
	 * @since 	Octubre 2020
	 */
	@Transactional
	public void agregar(ProductoLista productoLista) throws PersistanceLayerException, ServiceLayerException {
		try {
			productoListaRepository.crear(productoLista);
		} catch(PersistanceLayerException pe) {
			throw pe;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new ServiceLayerException(ServiceLayerException.CREAR_PRODUCTO_LISTA_EXCEPTION);
		}
	}

	/**
	 * Metodo que actualiza una lista de producto 
	 * @param productoLista {@link com.ar.sgi.model.ProductoLista}
	 * @version 1.0
	 * @throws PersistanceLayerException 
	 * @throws ServiceLayerException 
	 * @since 	Octubre 2020
	 */
	@Transactional
	public void actualizar(ProductoLista productoLista) throws PersistanceLayerException, ServiceLayerException {
		try {
			productoListaRepository.actualizar(productoLista);;
		} catch(PersistanceLayerException pe) {
			throw pe;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_PRODUCTO_LISTA_EXCEPTION);
		}
	}

	/**
	 * Metodo que cambia el estado de una lista de producto
	 * @param id 
	 * @version 1.0
	 * @throws PersistanceLayerException 
	 * @throws ServiceLayerException 
	 * @since 	Octubre 2020
	 */
	@Transactional
	public void cambiarEstado(Long id) throws PersistanceLayerException, ServiceLayerException {
		try {
			productoListaRepository.cambiarEstado(id);;
		} catch(PersistanceLayerException pe) {
			throw pe;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_PRODUCTO_LISTA_EXCEPTION);
		}
	}
}