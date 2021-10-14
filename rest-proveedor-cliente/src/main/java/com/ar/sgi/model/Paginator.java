package com.ar.sgi.model;

import java.util.List;

/**
 * Clase utilizada para paginar registros 
 * @author aetoigo
 * @param <T>: entidad parametrizada
 * @since: Octubre 2020
 * @version: 1.0
 */
public class Paginator<T> {
	List<T> entity;
	long cantidadRegistros;

	public Paginator() {

	}

	public Paginator(List<T> entity, long cantidadRegistros) {
		super();
		this.entity = entity;
		this.cantidadRegistros = cantidadRegistros;
	}

	public List<T> getEntity() {
		return entity;
	}
	public void setEntity(List<T> entity) {
		this.entity = entity;
	}
	public Long getCantidadRegistros() {
		return cantidadRegistros;
	}
	public void setCantidadRegistros(Long cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}

	public boolean validar() {
		if(entity != null && !entity.isEmpty() && cantidadRegistros > 0) {
			return true;
		} else {
			return false;
		}
	}
}
