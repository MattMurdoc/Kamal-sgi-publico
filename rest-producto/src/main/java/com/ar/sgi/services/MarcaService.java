package com.ar.sgi.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Marca;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.repository.MarcaRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encargada de la gesti\u00F3n de Marca
 * @see com.ar.sgi.model.Marca
 * @author 	Alejandro Toigo
 * @since 	Octubre 2020
 * @version 1.0
 *
 */
@ApplicationScoped
public class MarcaService {
	private static final Logger LOGGER = Logger.getLogger(MarcaService.class.getName());
	
	
	@Inject
	MarcaRepository marcaRepository;
	
	/**
	 * Metodo que agrega una marca con su correspondiente descripci\u00F3n.
	 * @param	pagina: numero de pagina
	 * @param	items: numeros de elementos por pagina
	 * @return 	Paginator: lista de marcas paginada
	 * @throws PersistanceLayerException: excepcion en la capa de persistencia 
	 * @throws ServiceLayerException: excepcion en la capa de servicio
	 * @version 1.0
	 * @since 	Octubre 2020
	 */
	public Paginator<Marca> listar(Integer pagina, Integer items, Marca marca) throws PersistanceLayerException, ServiceLayerException {
		Paginator<Marca> paginator = null;
		try {
			paginator = marcaRepository.listar(pagina, items, marca);
		} catch(PersistanceLayerException pe) {
			throw pe;
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new ServiceLayerException(ServiceLayerException.LISTAR_MARCAS_EXCEPTION);
		}
		return paginator;
	}

	public List<Marca> listarTodo() throws Exception {
		try {
			return marcaRepository.findAll().list();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new ServiceLayerException(ServiceLayerException.LISTAR_DEPARTAMENTO_EXCEPTION);
		}
	}


	/**
	 * Metodo que agrega una marca
	 * @param marca {@link com.ar.sgi.model.Marca} 
	 * @version 1.0
	 * @throws PersistanceLayerException 
	 * @throws ServiceLayerException 
	 * @since 	Octubre 2020
	 */
	@Transactional
	public void agregar(Marca marca) throws PersistanceLayerException, ServiceLayerException {
		try {
			marcaRepository.crear(marca);
		} catch(PersistanceLayerException pe) {
			throw pe;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new ServiceLayerException(ServiceLayerException.CREAR_MARCA_EXCEPTION);
		}
	}

	/**
	 * Metodo que actualiza una marca
	 * @param marca {@link com.ar.sgi.model.Marca} 
	 * @version 1.0
	 * @throws PersistanceLayerException 
	 * @throws ServiceLayerException 
	 * @since 	Octubre 2020
	 */
	@Transactional
	public void actualizar(Marca marca) throws PersistanceLayerException, ServiceLayerException {
		try {
			marcaRepository.actualizar(marca);;
		} catch(PersistanceLayerException pe) {
			throw pe;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_MARCA_EXCEPTION);
		}
	}

	/**
	 * Metodo que cambia el estado de una marca
	 * @param id 
	 * @version 1.0
	 * @throws PersistanceLayerException 
	 * @throws ServiceLayerException 
	 * @since 	Octubre 2020
	 */
	@Transactional
	public void cambiarEstado(Long id) throws PersistanceLayerException, ServiceLayerException {
		try {
			marcaRepository.cambiarEstado(id);;
		} catch(PersistanceLayerException pe) {
			throw pe;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_MARCA_EXCEPTION);
		}
	}

	public Marca buscarMarca (Long marcaId) throws Exception {
		try{
			return  marcaRepository.findById(marcaId);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new ServiceLayerException(ServiceLayerException.BUSCAR_MARCA_EXCEPTION);
		}
	}
}