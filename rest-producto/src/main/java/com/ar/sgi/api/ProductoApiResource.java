package com.ar.sgi.api;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ar.sgi.exceptions.*;
import com.ar.sgi.model.*;
import com.ar.sgi.model.dto.*;
import com.ar.sgi.model.mappers.ListMapper;
import com.ar.sgi.model.mappers.PaginatorMapper;
import com.ar.sgi.model.Respuesta;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.ar.sgi.services.ProductoService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/producto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Producto Resource", description = "Gesti\u00F3n de todo lo relacionado a producto")
public class ProductoApiResource {

    private static final Logger LOGGER = Logger.getLogger(ProductoApiResource.class.getName());

    @Inject
    ProductoService productoService;

    @POST
    @Path("/{pagina}/{items}")
    @Operation(summary = "Listar productos de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Productos paginados"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response buscar(@PathParam("pagina") int pagina, @PathParam("items") int items, ProductoFiltroDTO productofiltroDTO) {
        try {
            Paginator<Producto> productoPaginator = productoService.listar(pagina, items, productofiltroDTO);
            if (productoPaginator != null && productoPaginator.validar()) {
                return Response.ok().entity(PaginatorMapper.mapPaginator(productoPaginator, ProductoDTO.class)).build();
            } else {
                return Response.noContent().build();
            }
        } catch (ServiceLayerException e) {
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(e.toString());
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return Response.serverError().entity(respuesta).build();
        } catch (Exception e) {
            ApiResourceException apiResourceException = new ApiResourceException(ApiResourceException.GENERIC_MESSAGE);
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(apiResourceException.toString());
            LOGGER.log(Level.SEVERE, apiResourceException.toString(), e);
            return Response.serverError().entity(respuesta).build();
        }
    }

    @GET
    @Path("/id/{id}")
    @Operation(summary = "Devuelve un producto a partir de su código")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Producto"),
            @APIResponse(responseCode = "404", description = "No existe el producto")
    })
    public Response getById(@PathParam("id") Long id) {
        try {
            Producto producto = productoService.getById(id);
            if (producto == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No existe el producto")).build();
            } else {
                Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
                return Response.ok(mapper.map(producto, ProductoDTO.class)).build();
            }
        } catch (ServiceLayerException e) {
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(e.toString());
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return Response.serverError().entity(respuesta).build();
        } catch (Exception e) {
            ApiResourceException apiResourceException = new ApiResourceException(ApiResourceException.GENERIC_MESSAGE);
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(apiResourceException.toString());
            LOGGER.log(Level.SEVERE, apiResourceException.toString(), e);
            return Response.serverError().entity(respuesta).build();
        }
    }

    @GET
    @Path("/{codigo}")
    @Operation(summary = "Devuelve un producto a partir de su código")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Producto"),
            @APIResponse(responseCode = "250", description = "Producto con codigo interno"),
            @APIResponse(responseCode = "404", description = "No existe el producto")
    })
    public Response getByCodigo(@PathParam("codigo") String codigo) {
        try {
            Producto producto = productoService.getByCodigo(codigo);
            if (producto == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No existe el producto")).build();
            }else{
                Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
                return Response.ok(mapper.map(producto, ProductoDTO.class)).build();
            }
        } catch (ServiceLayerException e) {
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(e.toString());
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return Response.serverError().entity(respuesta).build();
        } catch (SolicitarCodigoInternoException sci) {
            return Response.status(250).build();
        } catch (Exception e) {
            ApiResourceException apiResourceException = new ApiResourceException(ApiResourceException.GENERIC_MESSAGE);
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(apiResourceException.toString());
            LOGGER.log(Level.SEVERE, apiResourceException.toString(), e);
            return Response.serverError().entity(respuesta).build();
        }
    }

    @GET
    @Path("/{codigo}/{codigoInterno}")
    @Operation(summary = "Devuelve un producto a partir de su código y su código interno")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Producto"),
            @APIResponse(responseCode = "404", description = "No existe el producto")
    })
    public Response getByCodigoAndCodigoInterno(@PathParam("codigo") String codigo, @PathParam("codigoInterno") String codigoInterno) {
        try {
            Producto producto = productoService.getByCodigoAndCodigoInterno(codigo, codigoInterno);
            if (producto == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No existe el producto")).build();
            }else{
                Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
                return Response.ok(mapper.map(producto, ProductoDTO.class)).build();
            }
        } catch (ServiceLayerException e) {
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(e.toString());
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return Response.serverError().entity(respuesta).build();
        } catch (Exception e) {
            ApiResourceException apiResourceException = new ApiResourceException(ApiResourceException.GENERIC_MESSAGE);
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(apiResourceException.toString());
            LOGGER.log(Level.SEVERE, apiResourceException.toString(), e);
            return Response.serverError().entity(respuesta).build();
        }
    }

    @POST
    @Operation(summary = "Persistir un producto")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Producto creado exitósamente"),
    })
    public Response agregar(@Valid ProductoDTO productoDTO) {
        try {
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            productoService.agregar(mapper.map(productoDTO, Producto.class));
            return Response.accepted().build();

        } catch (ServiceLayerException | PersistanceLayerException | ValidationException e) {
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(e.toString());
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return Response.serverError().entity(respuesta).build();
        } catch (Exception e) {
            ApiResourceException apiResourceException = new ApiResourceException(ApiResourceException.GENERIC_MESSAGE);
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(apiResourceException.toString());
            LOGGER.log(Level.SEVERE, apiResourceException.toString(), e);
            return Response.serverError().entity(respuesta).build();
        }
    }

    @PUT
    @Operation(summary = "Actualizar un producto")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Producto actualizado exitósamente"),
            @APIResponse(responseCode = "400", description = "Identificador de producto no válido")
    })
    public Response actualizar(@Valid ProductoDTO productoDTO) {
        try {
            if (productoDTO.getId() == null)
                return Response.status(Response.Status.BAD_REQUEST).entity(new Respuesta("Identificador no válido")).build();

            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            productoService.actualizar(mapper.map(productoDTO, Producto.class));
            return Response.accepted().build();
        } catch (ServiceLayerException | ValidationException e) {
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(e.toString());
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return Response.serverError().entity(respuesta).build();
        } catch (Exception e) {
            ApiResourceException apiResourceException = new ApiResourceException(ApiResourceException.GENERIC_MESSAGE);
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(apiResourceException.toString());
            LOGGER.log(Level.SEVERE, apiResourceException.toString(), e);
            return Response.serverError().entity(respuesta).build();
        }
    }

    @POST
    @Path("/actualizarprecios")
    @Operation(summary = "Actualizar los precios de uno o más productos")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Precios actualizados exitósamente"),
    })
    public Response modificarPrecios(ActualizarPrecioDTO actualizarPrecioDTO) {
        try {
            productoService.modificarPrecios(actualizarPrecioDTO);
            return Response.accepted().build();
        } catch (ServiceLayerException e) {
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(e.toString());
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return Response.serverError().entity(respuesta).build();
        } catch (Exception e) {
            ApiResourceException apiResourceException = new ApiResourceException(ApiResourceException.GENERIC_MESSAGE);
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(apiResourceException.toString());
            LOGGER.log(Level.SEVERE, apiResourceException.toString(), e);
            return Response.serverError().entity(respuesta).build();
        }
    }

    @POST
    @Path("/listasprecio")
    @Operation(summary = "Devuelve las listas de precio de uno o más productos siempre y cuando estos sean iguales")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Listas de precio"),
    })
    public Response traerListasPrecio(List<Long> ids) {
        try {
            List<ProductoListaPrecio> listasPrecio = productoService.traerListasPrecio(ids);
            return Response.ok(ListMapper.mapList(listasPrecio, ProductoListaPrecioDTO.class)).build();
        } catch (ServiceLayerException e) {
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(e.toString());
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return Response.serverError().entity(respuesta).build();
        } catch (Exception e) {
            ApiResourceException apiResourceException = new ApiResourceException(ApiResourceException.GENERIC_MESSAGE);
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(apiResourceException.toString());
            LOGGER.log(Level.SEVERE, apiResourceException.toString(), e);
            return Response.serverError().entity(respuesta).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Eliminar/restaurar un producto")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Producto eliminado/restaurado exitósamente"),
    })
    public Response cambiarEstado(@PathParam("id") Long id) {
        try {
            productoService.cambiarEstado(id);
            return Response.accepted().build();
        } catch (ServiceLayerException | PersistanceLayerException e) {
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(e.toString());
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return Response.serverError().entity(respuesta).build();
        } catch (Exception e) {
            ApiResourceException apiResourceException = new ApiResourceException(ApiResourceException.GENERIC_MESSAGE);
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(apiResourceException.toString());
            LOGGER.log(Level.SEVERE, apiResourceException.toString(), e);
            return Response.serverError().entity(respuesta).build();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_FORM_URLENCODED, MediaType.MULTIPART_FORM_DATA, MediaType.TEXT_PLAIN})
    @Path("/import")
    public Response importarProductos(@RequestBody(content = @Content(mediaType = "application/octet-stream",schema = @Schema(type = SchemaType.STRING, format = "binary"))) InputStream stream) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(stream);
        return Response.ok().build();
    }

    @POST
    @Path("/procesararchivo")
    public Response procesarArchivo() throws ValidationException, ServiceLayerException, PersistanceLayerException, IOException {
        productoService.procesarArchivo();
        return Response.ok().build();
    }

    @POST
    @Path("/updatePrecios")
    public Response procesarArchivo2() throws ValidationException, ServiceLayerException, PersistanceLayerException, IOException {
        productoService.procesarArchivo2();
        return Response.ok().build();
    }
}