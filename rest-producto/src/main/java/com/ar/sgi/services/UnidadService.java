package com.ar.sgi.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.ar.sgi.model.*;
import com.ar.sgi.model.dto.UnidadDTO;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.repository.UnidadRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encargada de la gesti\u00F3n de Unidad 
 * @see com.ar.sgi.model.Unidad
 * @author 	Alejandro Toigo
 * @since 	Octubre 2020
 * @version 1.0
 *
 */
@ApplicationScoped
public class UnidadService {
	private static final Logger LOGGER = Logger.getLogger(UnidadService.class.getName());

	@Inject
	UnidadRepository unidadRepository;
	
	/**
	 * Metodo que lista las unidad, paginadas
	 * @param	pagina: numero de pagina
	 * @param	items: numeros de elementos por pagina
	 * @return 	Paginator: lista de unidades paginada
	 * @throws PersistanceLayerException: excepcion en la capa de persistencia 
	 * @throws ServiceLayerException: excepcion en la capa de servicio
	 * @version 1.0
	 * @since 	Octubre 2020
	 */
	public Paginator<Unidad> listarFiltro(Integer pagina, Integer items, UnidadDTO unidadDTO) throws PersistanceLayerException, ServiceLayerException {
		try {
			Paginator<Unidad> paginator = unidadRepository.listarFiltro(pagina, items, unidadDTO);
			return paginator;
		} catch(PersistanceLayerException pe) {
			throw pe;
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new ServiceLayerException(ServiceLayerException.LISTAR_MARCAS_EXCEPTION);
		}
	}

	public List<Unidad> listarTodo() throws Exception {

		try {
			return unidadRepository.findAll().list();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new ServiceLayerException(ServiceLayerException.LISTAR_DEPARTAMENTO_EXCEPTION);
		}
	}

	public Unidad buscarUnidad (Long unidadId) throws Exception {
		try{
			return  unidadRepository.findById(unidadId);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new ServiceLayerException(ServiceLayerException.BUSCAR_UNIDAD_EXCEPTION);
		}
	}

	/**
	 * Metodo que agrega una unidad
	 * @param unidad {@link com.ar.sgi.model.Unidad}
	 * @version 1.0
	 * @throws PersistanceLayerException 
	 * @throws ServiceLayerException 
	 * @since 	Octubre 2020
	 */
	@Transactional
	public void agregar(Unidad unidad) throws PersistanceLayerException, ServiceLayerException {
		try {
			unidadRepository.crear(unidad);
		} catch(PersistanceLayerException pe) {
			throw pe;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new ServiceLayerException(ServiceLayerException.CREAR_UNIDAD_EXCEPTION);
		}
	}

	/**
	 * Metodo que actualiza una unidad 
	 * @param unidad {@link com.ar.sgi.model.Unidad}
	 * @version 1.0
	 * @throws PersistanceLayerException 
	 * @throws ServiceLayerException 
	 * @since 	Octubre 2020
	 */
	@Transactional
	public void actualizar(Unidad unidad) throws PersistanceLayerException, ServiceLayerException {
		try {
			unidadRepository.actualizar(unidad);;
		} catch(PersistanceLayerException pe) {
			throw pe;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_UNIDAD_EXCEPTION);
		}
	}

	/**
	 * Metodo que cambia el estado de una unidad
	 * @param id 
	 * @version 1.0
	 * @throws PersistanceLayerException 
	 * @throws ServiceLayerException 
	 * @since 	Octubre 2020
	 */
	@Transactional
	public void cambiarEstado(Long id) throws PersistanceLayerException, ServiceLayerException {
		try {
			unidadRepository.cambiarEstado(id);;
		} catch(PersistanceLayerException pe) {
			throw pe;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_UNIDAD_EXCEPTION);
		}
	}
}