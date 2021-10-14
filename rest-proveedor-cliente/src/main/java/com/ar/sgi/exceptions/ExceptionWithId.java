package com.ar.sgi.exceptions;

import java.util.UUID;

public abstract class ExceptionWithId extends Exception {
	private String id;

	public ExceptionWithId(String msg) {
		super(msg);
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CODIGO: " + this.id + "\n Mensaje: " + super.getMessage();
	}
	
}
