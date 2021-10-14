package com.ar.sgi.exceptions;

import liquibase.pro.packaged.S;

public class ServiceLayerException extends ExceptionWithId {

    public static final String LISTAR_MARCAS_EXCEPTION = "Ha ocurrido un error al devolver la informacion de las marcas";
    public static final String CREAR_MARCA_EXCEPTION = "Ha ocurrido un error al crear la marca";
    public static final String ACTUALIZAR_MARCA_EXCEPTION = "Ha ocurrido un error al actualizar la marca";
    public static final String BUSCAR_MARCA_EXCEPTION = "Ha ocurrido un error al buscar la informacion de las marcas";

    public static final String LISTAR_TIPO_EXCEPTION = "Ha ocurrido un error al devolver la informacion de los tipos";
    public static final String CREAR_TIPO_EXCEPTION = "Ha ocurrido un error al crear el tipo";
    public static final String ACTUALIZAR_TIPO_EXCEPTION = "Ha ocurrido un error al actualizar el tipo";

    public static final String LISTAR_UNIDAD_EXCEPTION = "Ha ocurrido un error al devolver la informacion de las unidades";
    public static final String CREAR_UNIDAD_EXCEPTION = "Ha ocurrido un error al crear la unidad";
    public static final String ACTUALIZAR_UNIDAD_EXCEPTION = "Ha ocurrido un error al actualizar la unidad";
    public static final String BUSCAR_UNIDAD_EXCEPTION = "Ha ocurrido un error al buscar la informacion de las unidades";

    public static final String LISTAR_SUBFAMILIA_EXCEPTION = "Ha ocurrido un error al devolver la informacion de las subfamilias";
    public static final String CREAR_SUBFAMILIA_EXCEPTION = "Ha ocurrido un error al crear la subfamilia";
    public static final String ACTUALIZAR_SUBFAMILIA_EXCEPTION = "Ha ocurrido un error al actualizar la subfamilia";
    public static final String BUSCAR_SUBFAMILIA_EXCEPTION = "Ha ocurrido un error al buscar la informacion de las subfamilias";

    public static final String LISTAR_FAMILIA_EXCEPTION = "Ha ocurrido un error al devolver la informacion de las familias";
    public static final String CREAR_FAMILIA_EXCEPTION = "Ha ocurrido un error al crear la familia";
    public static final String ACTUALIZAR_FAMILIA_EXCEPTION = "Ha ocurrido un error al actualizar la familia";
    public static final String BUSCAR_FAMILIA_EXCEPTION = "Ha ocurrido un error al buscar la informacion de la familias";

    public static final String LISTAR_SECCION_EXCEPTION = "Ha ocurrido un error al devolver la informacion de las secciones";
    public static final String CREAR_SECCION_EXCEPTION = "Ha ocurrido un error al crear la seccion";
    public static final String ACTUALIZAR_SECCION_EXCEPTION = "Ha ocurrido un error al actualizar la seccion";
    public static final String BUSCAR_SECCION_EXCEPTION = "Ha ocurrido un error al buscar la informacion de las secciones";

    public static final String LISTAR_DEPARTAMENTO_EXCEPTION = "Ha ocurrido un error al devolver la informacion de los departamentos";
    public static final String CREAR_DEPARTAMENTO_EXCEPTION = "Ha ocurrido un error al crear el departamento";
    public static final String ACTUALIZAR_DEPARTAMENTO_EXCEPTION = "Ha ocurrido un error al actualizar el departamento";
    public static final String BUSCAR_DEPARTAMENTO_EXCEPTION = "Ha ocurrido un error al buscar la informacion de los departamentos";

    public static final String LISTAR_LISTA_EXCEPTION = "Ha ocurrido un error al devolver la informacion de las listas";
    public static final String CREAR_LISTA_EXCEPTION = "Ha ocurrido un error al crear la lista";
    public static final String ACTUALIZAR_LISTA_EXCEPTION = "Ha ocurrido un error al actualizar la lista";
    public static final String BUSCAR_LISTA_EXCEPTION = "Ha ocurrido un error al buscar la informacion de las listas";

    public static final String LISTAR_PRODUCTO_LISTA_EXCEPTION = "Ha ocurrido un error al devolver la informacion de las listas de producto";
    public static final String CREAR_PRODUCTO_LISTA_EXCEPTION = "Ha ocurrido un error al crear la lista de producto";
    public static final String ACTUALIZAR_PRODUCTO_LISTA_EXCEPTION = "Ha ocurrido un error al actualizar la lista de producto";

    public static final String CREAR_PRODUCTO_EXCEPTION = "Ha ocurrido un error al crear producto";
    public static final String ACTUALIZAR_PRODUCTO_EXCEPTION = "Ha ocurrido un error al actualizar el producto";
    public static final String GET_BY_CODIGO_PRODUCTO = "Ha ocurrido un error al buscar el producto";
    public static final String ERROR_COMUNICACION_PROVEEDORES = "Error de comunicacion con los proveedores";

    public static final String LISTAR_MOVIMIENTO_STOCK_EXCEPTION = "Ha ocurrido un error al devolver la informacion de los movimientos de stock";
    public static final String CREAR_MOVIMIENTO_STOCK_EXCEPTION = "Ha ocurrido un error al crear el movimiento de stock";
    public static final String ACTUALIZAR_MOVIMIENTO_STOCK_EXCEPTION = "Ha ocurrido un error al actualizar el movimiento de stock";
    public static final String BUSCAR_MOVIMIENTO_STOCK_EXCEPTION = "Ha ocurrido un error al buscar la informacion de los movimientos de stock";

    public static final String ACTUALIZAR_PRECIO = "Ha ocurrido un error al actualizar precios";

    public static final String LISTAR_TIPO_MOVIMIENTOS = "Ha ocurrido un error al traer los tipos de movimientos";
    public static final String LISTAR_MOTIVOS = "Ha ocurrido un error al traer los motivos";

    public static final String CREAR_VENTA_EXCEPTION = "Ha ocurrido un error al crear la venta";


    public ServiceLayerException(String msg) {
        super(msg);
    }

}
