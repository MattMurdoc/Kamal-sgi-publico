package com.ar.sgi.api.productos;

import com.ar.sgi.models.productos.MarcaDTO;
import com.ar.sgi.restclient.productos.MarcaService;
import org.eclipse.microprofile.openapi.annotations.Operation;
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

@Path("/v1/producto/marca")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Marca Resource", description = "Gesti\u00F3n de todo lo relacionado a Marca")
public class MarcaApiResource {

    @Inject
    @RestClient
    MarcaService marcaService;

    @POST
    @Path("/{pagina}/{items}")
    @Operation(summary = "Listar marcas de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Marcas paginadas"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response list(@PathParam("pagina") Integer pagina, @PathParam("items") Integer items, MarcaDTO marcaDTO) {
        return marcaService.list(pagina, items, marcaDTO);
    }

    @POST
    @Operation(summary = "Persistir una marca")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Marca creada exitósamente"),
    })
    public Response agregar(@Valid MarcaDTO marcaDTO) {
        return marcaService.agregar(marcaDTO);
    }

    @PUT
    @Operation(summary = "Actualizar una marca")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Marca actualizada exitósamente"),
            @APIResponse(responseCode = "400", description = "Identificador de marca no válido")
    })
    public Response actualizar(@Valid MarcaDTO marcaDTO) {
        return marcaService.actualizar(marcaDTO);
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Eliminar/restaurar una marca")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Marca eliminada/restaurada exitósamente"),
    })
    public Response cambiarEstado(@PathParam("id") Long id) {
        return marcaService.cambiarEstado(id);
    }

    @GET
    @Path("/get/{id}")
    @Operation(summary = "Devuelve la marca a partir de un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Marca"),
            @APIResponse(responseCode = "404", description = "No existe la familia")
    })
    public Response getMarcaById(@PathParam("id") Long marcaId) {
        return marcaService.getMarcaById(marcaId);
    }

    @GET
    @Path("/all")
    @Operation(summary = "Listar todas las marcas")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Marcas"),
            @APIResponse(responseCode = "204", description = "No hay resultados"),
    })
    public Response listarTodoMarca() {
        return marcaService.listarTodoMarca();
    }
}