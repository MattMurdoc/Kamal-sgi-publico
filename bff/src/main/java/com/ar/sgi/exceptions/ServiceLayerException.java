package com.ar.sgi.exceptions;

public class ServiceLayerException extends ExceptionWithId {

    public static final String VALIDAR_USUARIO_EXCEPTION = "Ha ocurrido un error al hacer el login";


    public ServiceLayerException(String msg) {
        super(msg);
    }

}
