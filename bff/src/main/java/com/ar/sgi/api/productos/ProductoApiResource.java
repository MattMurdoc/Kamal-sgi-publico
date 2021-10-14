package com.ar.sgi.api.productos;

import com.ar.sgi.models.productos.ActualizarPrecioDTO;
import com.ar.sgi.models.productos.ProductoDTO;
import com.ar.sgi.models.productos.ProductoFiltroDTO;
import com.ar.sgi.restclient.productos.ProductoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Path("/v1/producto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Producto Resource", description = "Gesti\u00F3n de todo lo relacionado a producto")
public class ProductoApiResource {

    @Inject
    @RestClient
    ProductoService productoService;

    @POST
    @Path("/{pagina}/{items}")
    @Operation(summary = "Listar productos de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Productos paginados"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response buscar(@PathParam("pagina") int pagina, @PathParam("items") int items, ProductoFiltroDTO productofiltroDTO) {
        return productoService.buscar(pagina, items, productofiltroDTO);
    }

    @GET
    @Path("/id/{id}")
    @Operation(summary = "Devuelve un producto a partir de su código")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Producto"),
            @APIResponse(responseCode = "404", description = "No existe el producto")
    })
    public Response getById(@PathParam("id") Long id) {
        return productoService.getById(id);
    }

    @GET
    @Path("/{codigo}")
    @Operation(summary = "Devuelve un producto a partir de su código")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Producto"),
            @APIResponse(responseCode = "404", description = "No existe el producto")
    })
    public Response getByCodigo(@PathParam("codigo") String codigo) {
        return productoService.getByCodigo(codigo);
    }

    @GET
    @Path("/{codigo}/{codigoInterno}")
    @Operation(summary = "Devuelve un producto a partir de su código y su código interno")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Producto"),
            @APIResponse(responseCode = "404", description = "No existe el producto")
    })
    public Response getByCodigoAndCodigoInterno(@PathParam("codigo") String codigo, @PathParam("codigoInterno") String codigoInterno) {
        return productoService.getByCodigoAndCodigoInterno(codigo, codigoInterno);
    }

    @POST
    @Operation(summary = "Persistir un producto")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Producto creado exitósamente"),
    })
    public Response agregar(@Valid ProductoDTO productoDTO) {
        return productoService.agregar(productoDTO);
    }

    @PUT
    @Operation(summary = "Actualizar un producto")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Producto actualizado exitósamente"),
            @APIResponse(responseCode = "400", description = "Identificador de producto no válido")
    })
    public Response actualizar(@Valid ProductoDTO productoDTO) {
        return productoService.actualizar(productoDTO);
    }

    @POST
    @Path("/actualizarprecios")
    @Operation(summary = "Actualizar los precios de uno o más productos")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Precios actualizados exitósamente"),
    })
    public Response modificarPrecios(ActualizarPrecioDTO actualizarPrecioDTO) {
        return productoService.modificarPrecios(actualizarPrecioDTO);
    }

    @POST
    @Path("/listasprecio")
    @Operation(summary = "Devuelve las listas de precio de uno o más productos siempre y cuando estos sean iguales")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Listas de precio"),
    })
    public Response traerListasPrecio(List<Long> ids) {
        return productoService.traerListasPrecio(ids);
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Eliminar/restaurar un producto")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Producto eliminado/restaurado exitósamente"),
    })
    public Response cambiarEstado(@PathParam("id") Long id) {
        return productoService.cambiarEstado(id);
    }

    @POST
    @Consumes({MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_FORM_URLENCODED, MediaType.MULTIPART_FORM_DATA, MediaType.TEXT_PLAIN})
    @Path("/import")
    public Response importarProductos(@RequestBody(content = @Content(mediaType = "application/octet-stream", schema = @Schema(type = SchemaType.STRING, format = "binary"))) InputStream stream) throws IOException {
        return productoService.importarProductos(stream);
    }
}