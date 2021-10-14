package com.ar.sgi.api.productos;

import com.ar.sgi.models.productos.SeccionDTO;
import com.ar.sgi.restclient.productos.SeccionService;
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
import java.util.logging.Logger;

@Path("/v1/producto/seccion")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Seccion Api Resource", description = "Gesti\u00F3n de todo lo relacionado a Seccion")
public class SeccionApiResource {

    private static Logger LOGGER = Logger.getLogger(SeccionApiResource.class.getName());

    @Inject
    @RestClient
    SeccionService seccionService;

    @GET
    @Path("/{pagina}/{item}/{departamentoId}/{estado}")
    @Operation(summary = "Listar secciones de manera paginada que pertenezcan a un departamento")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Secciones paginadas que pertenecen a un departamento"),
            @APIResponse(responseCode = "404", description = "No existe el departamento"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarSeccion(@PathParam("pagina") int pagina, @PathParam("item") int item, @PathParam("departamentoId") long departamentoId, @PathParam("estado") boolean estado) {
        return seccionService.listarSeccion(pagina, item, departamentoId, estado);
    }

    @GET
    @Path("/{departamento}")
    @Operation(summary = "Listar todas las secciones que pertenezcan a un departamento")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Secciones que pertenecen a un departamento"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarByDepartamento(@PathParam("departamento") Long departamentoId) {
        return seccionService.listarByDepartamento(departamentoId);
    }

    @GET
    @Path("/get/{id}")
    @Operation(summary = "Devuelve una sección a partir de un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Seccion"),
            @APIResponse(responseCode = "404", description = "No existe la seccion")
    })
    public Response getSeccionById(@PathParam("id") Long seccionId) {
        return seccionService.getSeccionById(seccionId);
    }

    @POST
    @Operation(summary = "Persistir una sección")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Sección creada exitósamente"),
    })
    public Response agregar(@Valid SeccionDTO seccionDTO) {
        return seccionService.agregar(seccionDTO);
    }

    @PUT
    @Operation(summary = "Actualizar una sección")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Sección actualizada exitósamente"),
            @APIResponse(responseCode = "400", description = "Identificador de sección no válido")
    })
    public Response actualizar(@Valid SeccionDTO seccionDTO) {
        return seccionService.actualizar(seccionDTO);
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Eliminar/restaurar una sección")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Sección eliminada/restaurada exitósamente"),
    })
    public Response cambiarEstado(@PathParam("id") Long id) {
        return seccionService.cambiarEstado(id);
    }
}