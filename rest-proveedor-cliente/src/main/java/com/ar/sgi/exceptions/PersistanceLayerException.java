package com.ar.sgi.exceptions;

public class PersistanceLayerException extends ExceptionWithId {
	public static final String LISTAR_PROVEEDOR_EXCEPTION = "Ha ocurrido un error al buscar los proveedores";


	public static final String CREAR_PROVEEDOR_EXCEPTION = "Ha ocurrido un error al crear el proveedor";
	public static final String ACTUALIZAR_SUBFAMILIA_EXCEPTION = "Ha ocurrido un error al actualizar la subfamilia";
	public static final String BORRAR_SUBFAMILIA_EXCEPTION = "Ha ocurrido un error al borrar la subfamilia";

	public static final String LISTAR_CLIENTE_EXCEPTION = "Ha ocurrido un error al buscar los clientes";
	public static final String LISTAR_LOCALIDAD_EXCEPTION = "Ha ocurrido un error al buscar las localidades";
	public static final String LISTAR_CONTACTO_EXCEPTION = "Ha ocurrido un error al buscar los contactos";
	public static final String LISTAR_PROVINCIA_EXCEPTION = "Ha ocurrido un error al buscar las provincias";
	public static final String LISTAR_RUBRO_EXCEPTION = "Ha ocurrido un error al buscar los rubros";

	public static final String LISTAR_MAESTRO_POR_TIPO_DATO_EXCEPTION = "Ha ocurrido un error al buscar los datos maestros por tipo de dato";
	public static final String GET_MAESTRO_POR_VALOR_TIPO_DATO_EXCEPTION = "Ha ocurrido un error al buscar la descripcion del dato maestro";


	public PersistanceLayerException(String msg) {
		super(msg);
	}
	
}
