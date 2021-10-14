package com.ar.sgi.exceptions;

public class PersistanceLayerException extends ExceptionWithId {

	public static final String LISTAR_MARCA_EXCEPTION = "Ha ocurrido un error al buscar las marcas";
	public static final String CREAR_MARCA_EXCEPTION = "Ha ocurrido un error al crear la marca";
	public static final String ACTUALIZAR_MARCA_EXCEPTION = "Ha ocurrido un error al actualizar la marca";

	public static final String LISTAR_TIPO_EXCEPTION = "Ha ocurrido un error al buscar los tipos";
	public static final String CREAR_TIPO_EXCEPTION = "Ha ocurrido un error al crear el tipo";
	public static final String ACTUALIZAR_TIPO_EXCEPTION = "Ha ocurrido un error al actualizar el tipo";

	public static final String LISTAR_UNIDAD_EXCEPTION = "Ha ocurrido un error al buscar las unidades";
	public static final String CREAR_UNIDAD_EXCEPTION = "Ha ocurrido un error al crear la unidad";
	public static final String ACTUALIZAR_UNIDAD_EXCEPTION = "Ha ocurrido un error al actualizar la unidad";

	public static final String LISTAR_MAESTRO_POR_TIPO_DATO_EXCEPTION = "Ha ocurrido un error al buscar los datos maestros por tipo de dato";
	public static final String GET_MAESTRO_POR_VALOR_TIPO_DATO_EXCEPTION = "Ha ocurrido un error al buscar la descripcion del dato maestro";
	public static final String GET_MAESTRO_POR_DESCRIPCION_TIPO_DATO_EXCEPTION = "Ha ocurrido un error al buscar el valor del dato maestro";

	public static final String LISTAR_SUBFAMILIA_EXCEPTION = "Ha ocurrido un error al buscar las subfamilias";
	public static final String CREAR_SUBFAMILIA_EXCEPTION = "Ha ocurrido un error al crear la subfamilia";
	public static final String ACTUALIZAR_SUBFAMILIA_EXCEPTION = "Ha ocurrido un error al actualizar la subfamilia";
	public static final String BORRAR_SUBFAMILIA_EXCEPTION = "Ha ocurrido un error al borrar la subfamilia";

	public static final String LISTAR_FAMILIA_EXCEPTION = "Ha ocurrido un error al buscar las familias";
	public static final String CREAR_FAMILIA_EXCEPTION = "Ha ocurrido un error al crear la familia";
	public static final String ACTUALIZAR_FAMILIA_EXCEPTION = "Ha ocurrido un error al actualizar la familia";
	public static final String BORRAR_FAMILIA_EXCEPTION = "Ha ocurrido un error al borrar la familia";

	public static final String LISTAR_SECCION_EXCEPTION = "Ha ocurrido un error al buscar las seccion";
	public static final String CREAR_SECCION_EXCEPTION = "Ha ocurrido un error al crear la seccion";
	public static final String ACTUALIZAR_SECCION_EXCEPTION = "Ha ocurrido un error al actualizar la seccion";
	public static final String BORRAR_SECCION_EXCEPTION = "Ha ocurrido un error al borrar la seccion";

	public static final String LISTAR_DEPARTAMENTO_EXCEPTION = "Ha ocurrido un error al buscar los departamento";
	public static final String CREAR_DEPARTAMENTO_EXCEPTION = "Ha ocurrido un error al crear el departamento";
	public static final String ACTUALIZAR_DEPARTAMENTO_EXCEPTION = "Ha ocurrido un error al actualizar el departamento";
	public static final String BORRAR_DEPARTAMENTO_EXCEPTION = "Ha ocurrido un error al borrar el departamento";

	public static final String LISTAR_PRODUCTO_LISTA_PRECIO_EXCEPTION = "Ha ocurrido un error al buscar las listas";
	public static final String CREAR_PRODUCTO_LISTA_PRECIO_EXCEPTION = "Ha ocurrido un error al crear la lista";
	public static final String ACTUALIZAR_PRODUCTO_LISTA_PRECIO_EXCEPTION = "Ha ocurrido un error al actualizar la lista";
	public static final String BORRAR_PRODUCTO_LISTA_PRECIO_EXCEPTION = "Ha ocurrido un error al borrar la lista";

	public static final String LISTAR_PRODUCTO_LISTA_EXCEPTION = "Ha ocurrido un error al buscar las listas de producto";
	public static final String CREAR_PRODUCTO_LISTA_EXCEPTION = "Ha ocurrido un error al crear la lista de producto";
	public static final String ACTUALIZAR_PRODUCTO_LISTA_EXCEPTION = "Ha ocurrido un error al actualizar la lista de producto";

	public static final String CREAR_PRODUCTO_EXCEPTION = "Ha ocurrido un error al crear el producto";
	public static final String LISTAR_PRODUCTO_EXCEPTION = "Ha ocurrido un error al buscar los productos";

	public static final String LISTAR_MOVIMIENTO_STOCK_EXCEPTION = "Ha ocurrido un error al buscar los movimientos de stock";


	public PersistanceLayerException(String msg) {
		super(msg);
	}
	
}
