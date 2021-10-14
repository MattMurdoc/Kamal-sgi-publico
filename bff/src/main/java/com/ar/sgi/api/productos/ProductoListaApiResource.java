package com.ar.sgi.api.productos;

import com.ar.sgi.models.productos.ProductoListaDTO;
import com.ar.sgi.restclient.productos.ProductoListaService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/producto/lista")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Lista de Producto Resource", description = "Gesti\u00F3n de todo lo relacionado a las listas de productos")
public class ProductoListaApiResource {

    @Inject
    @RestClient
    ProductoListaService productoListaService;

    @GET
    @Operation(summary = "Listar todas las listas")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Listas"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response list() {
        return productoListaService.list();
    }

    @POST
    @Operation(summary = "Persistir una lista de precio")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Lista de precio persisitida éxitosamente"),
    })
    public Response agregar(@Valid ProductoListaDTO productoListaDTO) {
        return productoListaService.agregar(productoListaDTO);
    }

    @PUT
    @Operation(summary = "Actualizar una lista de precio")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Lista de precio actualizada éxitosamente"),
            @APIResponse(responseCode = "400", description = "Identificador de lista de precio no válido")
    })
    public Response actualizar(@Valid ProductoListaDTO productoListaDTO) {
        return productoListaService.actualizar(productoListaDTO);
    }
}