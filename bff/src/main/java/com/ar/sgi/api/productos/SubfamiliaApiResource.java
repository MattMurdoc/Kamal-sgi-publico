package com.ar.sgi.api.productos;

import com.ar.sgi.models.productos.SubFamiliaDTO;
import com.ar.sgi.restclient.productos.SubfamiliaService;
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

@Path("/v1/producto/subfamilia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Subfamilia Api Resource", description = "Gesti\u00F3n de todo lo relacionado a Subfamilia")
public class SubfamiliaApiResource {

    @Inject
    @RestClient
    SubfamiliaService subFamiliaService;

    @GET
    @Path("/{pagina}/{item}/{familia}/{estado}")
    @Operation(summary = "Listar subfamilias de manera paginada que pertenezcan a una familia")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Subfamilias paginadas que pertenecen a una familia"),
            @APIResponse(responseCode = "204", description = "No hay resultados"),
            @APIResponse(responseCode = "400", description = "No existe la familia")
    })
    public Response listarSubFamiliasByFamilia(@PathParam("pagina") int pagina,
                                               @PathParam("item") int item,
                                               @PathParam("familia") Long familiaId,
                                               @PathParam("estado") Boolean estado) {
        return subFamiliaService.listarSubFamiliasByFamilia(pagina, item, familiaId, estado);
    }

    @GET
    @Path("/{familia}")
    @Operation(summary = "Listar todas las subfamilias que pertenezcan a una familia")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Subfamilias que pertenecen a una familia"),
            @APIResponse(responseCode = "204", description = "No hay resultados"),
            @APIResponse(responseCode = "404", description = "No existe la familia")
    })
    public Response listarSubFamiliasByFamilia(@PathParam("familia") Long familiaId) {
        return subFamiliaService.listarSubFamiliasByFamilia(familiaId);
    }

    @GET
    @Path("/get/{id}")
    @Operation(summary = "Devuelve la subfamilia a partir de un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Subfamilia"),
            @APIResponse(responseCode = "404", description = "No existe la subfamilia")
    })
    public Response getSubfamiliaById(@PathParam("id") Long subfamiliaId) {
        return subFamiliaService.getSubfamiliaById(subfamiliaId);
    }

    @POST
    @Operation(summary = "Persistir una familia")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Subfamilia creada exitósamente"),
    })
    public Response agregar(@Valid SubFamiliaDTO subFamiliaDTO) {
        return subFamiliaService.agregar(subFamiliaDTO);
    }

    @PUT
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Familia actualizada exitósamente"),
            @APIResponse(responseCode = "400", description = "Identificador de familia no válido")
    })
    public Response actualizar(@Valid SubFamiliaDTO subFamiliaDTO) {
        return subFamiliaService.actualizar(subFamiliaDTO);
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Eliminar/restaurar una sección")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Sección eliminada/restaurada exitósamente"),
    })
    public Response cambiarEstado(@PathParam("id") Long id) {
        return subFamiliaService.cambiarEstado(id);
    }

}