package com.ar.sgi.exceptions;

public class PersistanceLayerException extends ExceptionWithId {

	public static final String LISTAR_ROL_EXCEPTION = "Ha ocurrido un error al buscar los roles";
	public static final String CREAR_ROL_EXCEPTION = "Ha ocurrido un error al crear el rol";
	public static final String BORRAR_ROL_EXCEPTION = "Ha ocurrido un error al borrar el rol";

	public static final String LISTAR_USUARIO_EXCEPTION = "Ha ocurrido un error al buscar los usuarios";
	public static final String CREAR_USUARIO_EXCEPTION = "Ha ocurrido un error al crear el usuario";
	public static final String BORRAR_USUARIO_EXCEPTION = "Ha ocurrido un error al actualizar el usuario";

	public static final String LISTAR_PERMISO_EXCEPTION = "Ha ocurrido un error al buscar los permisos";
	public static final String CREAR_PERMISO_EXCEPTION = "Ha ocurrido un error al crear el permiso";
	public static final String BORRAR_PERMISO_EXCEPTION = "Ha ocurrido un error al borrar el permiso";

	public PersistanceLayerException(String msg) {
		super(msg);
	}
	
}
