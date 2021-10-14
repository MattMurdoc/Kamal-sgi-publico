package com.ar.sgi.restclient.productos;

import com.ar.sgi.models.productos.ActualizarPrecioDTO;
import com.ar.sgi.models.productos.ProductoDTO;
import com.ar.sgi.models.productos.ProductoFiltroDTO;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Path("/producto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "producto")
public interface ProductoService {

    @POST
    @Path("/{pagina}/{items}")
    Response buscar(@PathParam("pagina") int pagina, @PathParam("items") int items, ProductoFiltroDTO productofiltroDTO);

    @GET
    @Path("/id/{id}")
    Response getById(@PathParam("id") Long id);

    @GET
    @Path("/{codigo}")
    Response getByCodigo(@PathParam("codigo") String codigo);

    @GET
    @Path("/{codigo}/{codigoInterno}")
    Response getByCodigoAndCodigoInterno(@PathParam("codigo") String codigo, @PathParam("codigoInterno") String codigoInterno);

    @POST
    Response agregar(@Valid ProductoDTO productoDTO);

    @PUT
    Response actualizar(@Valid ProductoDTO productoDTO);

    @POST
    @Path("/actualizarprecios")
    Response modificarPrecios(ActualizarPrecioDTO actualizarPrecioDTO);

    @POST
    @Path("/listasprecio")
    Response traerListasPrecio(List<Long> ids);

    @PUT
    @Path("/{id}")
    Response cambiarEstado(@PathParam("id") Long id);

    @POST
    @Consumes({MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_FORM_URLENCODED, MediaType.MULTIPART_FORM_DATA, MediaType.TEXT_PLAIN})
    @Path("/import")
    Response importarProductos(@RequestBody(content = @Content(mediaType = "application/octet-stream", schema = @Schema(type = SchemaType.STRING, format = "binary"))) InputStream stream) throws IOException;
}
