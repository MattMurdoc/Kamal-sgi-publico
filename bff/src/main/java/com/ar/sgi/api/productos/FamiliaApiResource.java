package com.ar.sgi.api.productos;

import com.ar.sgi.models.productos.FamiliaDTO;
import com.ar.sgi.restclient.productos.FamiliaService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/producto/familia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Familia Api Resource", description = "Gesti\u00F3n de todo lo relacionado a Familia")
@SecuritySchemes(value = {
        @SecurityScheme(securitySchemeName = "apiKey",
                type = SecuritySchemeType.HTTP,
                scheme = "Bearer")}
)
public class FamiliaApiResource {

    @Inject
    @RestClient
    FamiliaService familiaService;

    @GET
    @Path("/{pagina}/{item}/{seccion}/{estado}")
    @Operation(summary = "Listar familias de manera paginada que pertenezcan a una sección")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Familias paginadas que pertenecen a una sección"),
            @APIResponse(responseCode = "204", description = "No hay resultados"),
            @APIResponse(responseCode = "404", description = "No existe la sección")
    })
    public Response listarFamiliaBySeccion(@PathParam("pagina") int pagina, @PathParam("item") int item, @PathParam("seccion") Long seccionId, @PathParam("estado") Boolean estado) {
        return familiaService.listarFamiliaBySeccion(pagina, item, seccionId, estado);
    }

    @GET
    @Path("/{seccion}")
    @Operation(summary = "Listar todas las familias que pertenezcan a una sección")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Familias que pertenecen a una sección"),
            @APIResponse(responseCode = "204", description = "No hay resultados"),
            @APIResponse(responseCode = "404", description = "No existe la sección")
    })
    public Response listarBySeccion(@PathParam("seccion") Long seccionId) {
        return familiaService.listarBySeccion(seccionId);
    }

    @GET
    @Path("/get/{id}")
    @Operation(summary = "Devuelve la familia a partir de un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Familia"),
            @APIResponse(responseCode = "404", description = "No existe la familia")
    })
    public Response getFamiliaById(@PathParam("id") Long familiaId) {
        return familiaService.getFamiliaById(familiaId);
    }

    @POST
    @Operation(summary = "Persistir una familia")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Familia creada exitósamente"),
    })
    public Response agregar(@Valid FamiliaDTO familiaDTO) {
        return familiaService.agregar(familiaDTO);
    }

    @PUT
    @Operation(summary = "Actualizar una familia")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Familia actualizada exitósamente"),
            @APIResponse(responseCode = "400", description = "Identificador de familia no válido")
    })
    public Response actualizar(@Valid FamiliaDTO familiaDTO) {
        return familiaService.actualizar(familiaDTO);
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Eliminar/restaurar una sección")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Sección eliminada/restaurada exitósamente"),
    })
    public Response cambiarEstado(@PathParam("id") Long id) {
        return familiaService.cambiarEstado(id);
    }
}
