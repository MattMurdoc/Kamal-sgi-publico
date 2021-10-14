package com.ar.sgi.exceptions;

import liquibase.pro.packaged.S;

public class ServiceLayerException extends ExceptionWithId {

    public static final String LISTAR_VENTA_EXCEPTION = "Ha ocurrido un error al devolver la informacion de las ventas";
    public static final String CREAR_VENTA_EXCEPTION = "Ha ocurrido un error al crear la venta";
    public static final String ACTUALIZAR_SECCION_EXCEPTION = "Ha ocurrido un error al actualizar la seccion";
    public static final String BUSCAR_SECCION_EXCEPTION = "Ha ocurrido un error al buscar la informacion de las secciones";

    public static final String LISTAR_TARJETA_EXCEPTION = "Ha ocurrido un error al devolver la informacion de las tarjetas";
    public static final String CREAR_TARJETA_EXCEPTION = "Ha ocurrido un error al crear la tarjeta";

    public static final String LISTAR_FORMA_PAGO_EXCEPTION = "Ha ocurrido un error al devolver la informacion de la forma de pago";
    public static final String CREAR_FORMA_PAGO_EXCEPTION = "Ha ocurrido un error al crear la forma de pago";

    public static final String LISTAR_DETALLE_VENTA_EXCEPTION = "Ha ocurrido un error al devolver la informacion de los detalles de venta";
    public static final String CREAR_DETALLE_VENTA_EXCEPTION = "Ha ocurrido un error al crear la forma de los detalles de venta";

    public ServiceLayerException(String msg) {
        super(msg);
    }

}
