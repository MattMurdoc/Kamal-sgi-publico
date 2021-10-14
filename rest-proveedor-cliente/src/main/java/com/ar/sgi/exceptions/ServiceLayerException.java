package com.ar.sgi.exceptions;

public class ServiceLayerException extends ExceptionWithId {

    public static final String LISTAR_PROOEEDOR_EXCEPTION = "Ha ocurrido un error al devolver la informacion de los proveedores";
    public static final String CREAR_PROVEEDOR_EXCEPTION = "Ha ocurrido un error al crear el proveedor";
    public static final String ACTUALIZAR_PROVEEDOR_EXCEPTION = "Ha ocurrido un error al actualizar el proveedor";
    public static final String BUSCAR_PROVEEDOR_EXCEPTION = "Ha ocurrido un error al buscar la informacion del proveedor";

    public static final String LISTAR_CONTACTO_EXCEPTION = "Ha ocurrido un error al devolver la informacion de los contactos";
    public static final String CREAR_CONTACTO_EXCEPTION = "Ha ocurrido un error al crear el contacto";
    public static final String ACTUALIZAR_CONTACTO_EXCEPTION = "Ha ocurrido un error al actualizar el contacto";
    public static final String BUSCAR_CONTACTO_EXCEPTION = "Ha ocurrido un error al buscar la informacion del contacto";

    public static final String LISTAR_LOCALIDAD_EXCEPTION = "Ha ocurrido un error al devolver la informacion de las localidades";
    public static final String CREAR_LOCALIDAD_EXCEPTION = "Ha ocurrido un error al crear la localidad";
    public static final String ACTUALIZAR_LOCALIDAD_EXCEPTION = "Ha ocurrido un error al actualizar la localidad";
    public static final String BUSCAR_LOCALIDAD_EXCEPTION = "Ha ocurrido un error al buscar la informacion de la localidad";

    public static final String LISTAR_PROVINCIA_EXCEPTION = "Ha ocurrido un error al devolver la informacion de las provincias";
    public static final String CREAR_PROVINCIA_EXCEPTION = "Ha ocurrido un error al crear la provincia";
    public static final String ACTUALIZAR_PROVINCIA_EXCEPTION = "Ha ocurrido un error al actualizar la provincia";
    public static final String BUSCAR_PROVINCIA_EXCEPTION = "Ha ocurrido un error al buscar la informacion de la provincia";

    public static final String LISTAR_RUBRO_EXCEPTION = "Ha ocurrido un error al devolver la informacion de los rubros";
    public static final String CREAR_RUBRO_EXCEPTION = "Ha ocurrido un error al crear el rubro";
    public static final String ACTUALIZAR_RUBRO_EXCEPTION = "Ha ocurrido un error al actualizar el rubro";
    public static final String BUSCAR_RUBRO_EXCEPTION = "Ha ocurrido un error al buscar la informacion del rubro";

    public static final String LISTAR_CLIENTE_EXCEPTION = "Ha ocurrido un error al devolver la informacion de los clientes";
    public static final String CREAR_CLIENTE_EXCEPTION = "Ha ocurrido un error al crear el cliente";
    public static final String ACTUALIZAR_CLIENTE_EXCEPTION = "Ha ocurrido un error al actualizar el cliente";
    public static final String BUSCAR_CLIENTE_EXCEPTION = "Ha ocurrido un error al buscar la informacion del cliente";

    public static final String LISTAR_TIPO_DNI = "Ha ocurrido un error al traer los tipos de DNI";
    public static final String LISTAR_RESPONSABILIDAD_CLIENTE = "Ha ocurrido un error al traer las categorias IVA";

    public ServiceLayerException(String msg) {
        super(msg);
    }
}
