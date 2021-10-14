package com.ar.sgi.exceptions;

import liquibase.pro.packaged.S;

public class ServiceLayerException extends ExceptionWithId {


    public static final String LISTAR_ROL_EXCEPTION = "Ha ocurrido un error al devolver la informacion de los roles";
    public static final String CREAR_ROL_EXCEPTION = "Ha ocurrido un error al crear el rol";
    public static final String ACTUALIZAR_ROL_EXCEPTION = "Ha ocurrido un error al actualizar la familia";
    public static final String BUSCAR_ROL_EXCEPTION = "Ha ocurrido un error al buscar la informacion de los roles";

    public static final String LISTAR_USUARIO_EXCEPTION = "Ha ocurrido un error al devolver la informacion de los usuarios";
    public static final String CREAR_USUARIO_EXCEPTION = "Ha ocurrido un error al crear el usuario";
    public static final String ACTUALIZAR_USUARIO_EXCEPTION = "Ha ocurrido un error al actualizar el usuario";
    public static final String BUSCAR_USUARIO_EXCEPTION = "Ha ocurrido un error al buscar la informacion de los usuarios";
    public static final String VALIDAR_USUARIO_EXCEPTION = "Ha ocurrido un error al validar el usuario";

    public static final String LISTAR_PERMISO_EXCEPTION = "Ha ocurrido un error al devolver la informacion de los permisos";
    public static final String CREAR_PERMISO_EXCEPTION = "Ha ocurrido un error al crear el rol";
    public static final String ACTUALIZAR_PERMISO_EXCEPTION = "Ha ocurrido un error al actualizar el rol";
    public static final String BUSCAR_PERMISO_EXCEPTION = "Ha ocurrido un error al buscar la informacion de los roles";


    public ServiceLayerException(String msg) {
        super(msg);
    }

}
