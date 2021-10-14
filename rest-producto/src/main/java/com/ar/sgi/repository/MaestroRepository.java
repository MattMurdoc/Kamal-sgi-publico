package com.ar.sgi.repository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.Maestro;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

/**
 * 
 * Clase encargada de la capa de presistencia de Maestro 
 * @see com.ar.sgi.model.Maestro
 * 
 * @author Alejandro Toigo 
 * @since Octubre 2020
 * @version 1.0
 */
@ApplicationScoped
public class MaestroRepository implements PanacheRepositoryBase<Maestro, Long>{

	public static final String TIPO_DATO_TIPO_MOVIMIENTO = "Tipo movimiento";
	public static final String TIPO_DATO_MOTIVO = "Motivo";

	public static final Long TIPO_MOVIMIENTO_ENTRADA_LONG = 1L;
	public static final Long TIPO_MOVIMIENTO_SALIDA_LONG = 2L;
	public static final Long TIPO_MOVIMIENTO_AJUSTE_STOCK_LONG = 3L;

	private static final Logger LOGGER = Logger.getLogger(MaestroRepository.class.getName());
	

	/**
	 * Devuelve los datos maestros a partir del tipo de dato
	 * @param tipoDato valor de la COLUMNA tipo de la BD
	 * @return {@link java.util.List} de {@link com.ar.sgi.model.Maestro}
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
	 * @return {@link java.util.List} de {@link com.ar.sgi.model.Maestro}
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
	 * @return {@link java.lang.String} que contiene la descripcion del dato maestro
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
	 * @return {@link java.lang.String} que contiene la descripcion del dato maestro
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