package com.ar.sgi.exceptions;

public class PersistanceLayerException extends ExceptionWithId {

	public static final String LISTAR_VENTA_EXCEPTION = "Ha ocurrido un error al buscar las ventas";
	public static final String CREAR_VENTA_EXCEPTION = "Ha ocurrido un error al crear la venta";
	public static final String ACTUALIZAR_VENTA_EXCEPTION = "Ha ocurrido un error al actualizar la venta";
	public static final String BORRAR_VENTA_EXCEPTION = "Ha ocurrido un error al borrar la venta";

	public static final String LISTAR_TARJETA_EXCEPTION = "Ha ocurrido un error al buscar las tarjetas";

	public static final String LISTAR_FORMA_PAGO_EXCEPTION = "Ha ocurrido un error al buscar las formas de pago";

	public static final String LISTAR_DETALLE_VENTA_EXCEPTION = "Ha ocurrido un error al buscar los detalles de venta";

	public PersistanceLayerException(String msg) {
		super(msg);
	}
	
}
