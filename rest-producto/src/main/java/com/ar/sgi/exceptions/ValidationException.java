package com.ar.sgi.exceptions;

public class ValidationException extends ExceptionWithId {
    public static final String VALIDAR_CODIGO_BARRAS = "No puede existir un producto con el mismo codigo de barras";
    public static final String VALIDAR_CODIGO_BARRAS_CODIGO_INTERNO = "No puede existir un producto con el mismo codigo de barras y codigo interno";
    public static final String VALIDAR_PROVEEDOR = "No existe proveedor con ese id";
    public static final String VALIDAR_DEPARTAMENTO = "No existe departamento";
    public static final String VALIDAR_SECCION = "No existe seccion";
    public static final String VALIDAR_FAMILIA = "No existe familia";
    public static final String VALIDAR_SUBFAMILIA = "No existe sub familia";
    public static final String VALIDAR_STOCK = "Ha ocurrido un error al validar el stock";
    public static final String VALIDAR_STOCK_MIN = "Ha ocurrido un error al validar el stock minimo";
    public static final String VALIDAR_STOCK_MAX = "Ha ocurrido un error al validar el stock maximo";
    public static final String VALIDAR_PUNTO_PEDIDO = "Ha ocurrido un error al validar el punto de pedido";
    public static final String VALIDAR_PERMITE_DESCARGAR = "Ha ocurrido un error al validar si permite descargar";
    public static final String VALIDAR_PERMITE_CARGAR = "Ha ocurrido un error al validar si permite cargar";
    public static final String VALIDAR_EQUIVALENCIA_CARGA = "Ha ocurrido un error al validar si permite equivalencia cargar";
    public static final String VALIDAR_EQUIVALENCIA_DESCARGA = "Ha ocurrido un error al validar si permite equivalencia descargar";

    public static final String LISTA_INACTIVA = "La lista tiene estado inactiva";
    public static final String COSTO_NEGATIVO = "El valor del costo debe ser positivo";
    public static final String PRECIO_COMPRA_NEGATIVO = "El valor del precio de compra debe ser positivo";
    public static final String PRECIO_VENTA_NEGATIVO = "El valor del precio de venta debe ser positivo";
    public static final String COSTO_VACIO = "El valor del precio de venta debe ser positivo";
    public static final String PRECIO_COMPRA_VACIO = "El valor del precio de venta debe ser positivo";
    public static final String PRECIO_VENTA_VACIO = "El valor del precio de venta debe ser positivo";
    public static final String VALIDAR_UNIDAD = "No existe la unidad";
    public static final String VALIDAR_MARCA = "No existe la marca";

    public static final String FORMATO_FECHA_INVALIDO = "Formato de fecha inválido";

    public ValidationException(String msg) {
        super(msg);
    }


}
