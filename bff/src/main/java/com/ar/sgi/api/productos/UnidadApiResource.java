package com.ar.sgi.api.productos;

import com.ar.sgi.models.productos.UnidadDTO;
import com.ar.sgi.restclient.productos.UnidadService;
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

@Path("/v1/producto/unidad")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Unidad Resource", description = "Gesti\u00F3n de todo lo relacionado a Unidad")
public class UnidadApiResource {

    @Inject
    @RestClient
    UnidadService unidadService;

    @GET
    @Path("/get/{id}")
    @Operation(summary = "Devuelve la unidad a partir de un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Unidad"),
            @APIResponse(responseCode = "404", description = "No existe la unidad")
    })
    public Response getUnidadById(@PathParam("id") Long unidadId) {
        return unidadService.getUnidadById(unidadId);
    }

    @GET
    @Path("/all")
    @Operation(summary = "Listar todas las unidades")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Unidades"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarTodoUnidad() {
        return unidadService.listarTodoUnidad();
    }

    @POST
    @Operation(summary = "Persistir una unidad")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Unidad persisitida éxitosamente"),
    })
    public Response agregar(@Valid UnidadDTO unidadDTO) {
        return unidadService.agregar(unidadDTO);
    }

    @POST
    @Path("/{pagina}/{items}")
    @Operation(summary = "Listar unidades de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Unidades paginadas"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response list(@PathParam("pagina") Integer pagina, @PathParam("items") Integer items, UnidadDTO unidadDTO) {
        return unidadService.list(pagina, items, unidadDTO);
    }

    @PUT
    @Operation(summary = "Actualizar una unidad")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Unidad actualizada éxitosamente"),
            @APIResponse(responseCode = "400", description = "Identificador de unidad no válido")
    })
    public Response actualizar(@Valid UnidadDTO unidadDTO) {
        return unidadService.actualizar(unidadDTO);
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Eliminar/restaurar una unidad")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Unidad eliminada/restaurada éxitosamente"),
    })
    public Response cambiarEstado(@PathParam("id") Long id) {
        return unidadService.cambiarEstado(id);
    }
}
