package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.Maestro;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * Clase encargada de la capa de presistencia de Maestro 
 * @see Maestro
 * 
 * @author Alejandro Toigo 
 * @since Octubre 2020
 * @version 1.0
 */
@ApplicationScoped
public class MaestroRepository implements PanacheRepositoryBase<Maestro, Long>{

	public static final String TIPO_DNI = "Tipo DNI";
	public static final String TIPO_RESPONSABILIDAD_CLIENTE = "Tipo responsabilidad cliente";

	private static final Logger LOGGER = Logger.getLogger(MaestroRepository.class.getName());
	

	/**
	 * Devuelve los datos maestros a partir del tipo de dato
	 * @param tipoDato valor de la COLUMNA tipo de la BD
	 * @return {@link List} de {@link Maestro}
	 * @throws PersistanceLayerException: error en la capa de persistencia
	 */
	public List<Maestro> listByTipoDato(final String tipoDato) throws PersistanceLayerException {
		try {
			return this.list("tipoDato = ?1", tipoDato);
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new PersistanceLayerException(PersistanceLayerException.LISTAR_MAESTRO_POR_TIPO_DATO_EXCEPTION);
		}
	}
	
	/**
	 * Devuelve los datos maestros a partir del tipo de dato y un filtro
	 * @param tipoDato valor de la COLUMNA tipo de la BD
	 * @param filtro valor de la COLUMNA filtro de la BD
	 * @return {@link List} de {@link Maestro}
	 * @throws PersistanceLayerException: error en la capa de persistencia
	 */
	public List<Maestro> listByTipoDatoYFiltro(final String tipoDato, final Long filtro) throws PersistanceLayerException {
		try {
			return this.list("tipoDato = ?1 and filtro = ?2", tipoDato, filtro);
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new PersistanceLayerException(PersistanceLayerException.LISTAR_MAESTRO_POR_TIPO_DATO_EXCEPTION);
		}
	}
	
	/**
	 * Devuelve la descripcion del dato maestro a partir del valor y del tipo de dato
	 * @param valor valor de la COLUMNA valor de la BD
	 * @param tipoDato valor de la COLUMNA tipo de la BD
	 * @return {@link String} que contiene la descripcion del dato maestro
	 * @throws PersistanceLayerException: error en la capa de persistencia
	 */
	public String getDescripcionByValorYTipoDato(final Long valor, final String tipoDato) throws PersistanceLayerException {
		String resultado = null;
		try {
			PanacheQuery<Maestro> query = this.find("valor = ?1 and tipoDato = ?2" , valor, tipoDato);
			Maestro maestro = query.firstResult();
			if(maestro != null) {
				resultado = maestro.getDescripcion();
			}
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new PersistanceLayerException(PersistanceLayerException.GET_MAESTRO_POR_VALOR_TIPO_DATO_EXCEPTION);
		}
		
		return resultado;
	}

	/**
	 * Devuelve la descripcion del dato maestro a partir del valor y del tipo de dato
	 * @param valor valor de la COLUMNA valor de la BD
	 * @param tipoDato valor de la COLUMNA tipo de la BD
	 * @param filtro valor de la COLUMNA filtro de la BD
	 * @return {@link String} que contiene la descripcion del dato maestro
	 * @throws PersistanceLayerException: error en la capa de persistencia
	 */
	public String getDescripcionByValorYTipoDatoYFiltro(final Long valor, final String tipoDato, final String filtro) throws PersistanceLayerException {
		String resultado = null;
		try {
			PanacheQuery<Maestro> query = this.find("valor = ?1 and tipoDato = ?2 and filtro = ?3", valor, tipoDato, filtro);
			Maestro maestro = query.firstResult();
			if(maestro != null) {
				resultado = maestro.getDescripcion();
			}
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new PersistanceLayerException(PersistanceLayerException.GET_MAESTRO_POR_VALOR_TIPO_DATO_EXCEPTION);
		}
		return resultado;
	}

	public Long selectByTipoDatoDescripcion(final String tipoDato, final String descripcion) throws PersistanceLayerException {
	    Long resultado = null;
		try {
			if (tipoDato != null && descripcion != null) {
				PanacheQuery<Maestro> query = this.find("descripcion = ?1 and tipoDato = ?2 and filtro = ?3", descripcion, tipoDato);
				Maestro maestro = query.firstResult();
				if(maestro != null) {
					resultado = maestro.getValor();
				}
			}
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new PersistanceLayerException(PersistanceLayerException.GET_MAESTRO_POR_VALOR_TIPO_DATO_EXCEPTION);
		}
		return resultado;
	}
}