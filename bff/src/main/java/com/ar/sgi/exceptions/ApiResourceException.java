package com.ar.sgi.exceptions;

public class ApiResourceException extends ExceptionWithId {
	
	public static final String GENERIC_MESSAGE = "Ha ocurrido un error al procesar la solicitud";

	public ApiResourceException(String msg) {
		super(msg);
	}

}
