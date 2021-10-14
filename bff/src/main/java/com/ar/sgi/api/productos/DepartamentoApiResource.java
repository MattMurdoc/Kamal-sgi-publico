package com.ar.sgi.api.productos;

import com.ar.sgi.models.productos.DepartamentoDTO;
import com.ar.sgi.restclient.productos.DepartamentoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
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

@Path("/v1/producto/departamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Departamento Api Resource", description = "Gesti\u00F3n de todo lo relacionado a Departamento")
@SecuritySchemes(value = {
        @SecurityScheme(securitySchemeName = "apiKey",
                type = SecuritySchemeType.HTTP,
                scheme = "Bearer")}
)
public class DepartamentoApiResource {

    @Inject
    @RestClient
    DepartamentoService departamentoService;

    @GET
    @Path("/{pagina}/{item}")
    @SecurityRequirement(name = "apiKey")
    @Operation(summary = "Listar departamentos de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Departamentos paginados"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarDepartamento(@PathParam("pagina") int pagina, @PathParam("item") int item) {
        return departamentoService.listarDepartamento(pagina, item);
    }

    @GET
    @Path("/all")
    @SecurityRequirement(name = "apiKey")
    @Operation(summary = "Listar todos los departamentos")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Departamentos"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarTodoDepartamento() {
        return departamentoService.listarTodoDepartamento();
    }

    @GET
    @Path("/get/{id}")
    @SecurityRequirement(name = "apiKey")
    @Operation(summary = "Devuelve un departamento mediante un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Departamento"),
            @APIResponse(responseCode = "404", description = "No se encontro un departamento con ese identificador")
    })
    public Response getDepartamentoById(@PathParam("id") Long departamentoId) {
        return departamentoService.getDepartamentoById(departamentoId);
    }

    @POST
    @SecurityRequirement(name = "apiKey")
    @Operation(summary = "Persistir un departamento")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Departamento persisitido éxitosamente"),
    })
    public Response agregar(@Valid DepartamentoDTO departamentoDTO) {
        return departamentoService.agregar(departamentoDTO);
    }

    @PUT
    @SecurityRequirement(name = "apiKey")
    @Operation(summary = "Actualizar un departamento")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Departamento actualizado éxitosamente"),
            @APIResponse(responseCode = "400", description = "Identificador de departamento no válido")
    })
    public Response actualizar(@Valid DepartamentoDTO departamentoDTO) {
        return departamentoService.actualizar(departamentoDTO);
    }

    @PUT
    @Path("/{id}")
    @SecurityRequirement(name = "apiKey")
    @Operation(summary = "Eliminar/restaurar un departamento")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Departamento eliminado/restaurado éxitosamente"),
    })
    public Response cambiarEstado(@PathParam("id") Long id) {
        return departamentoService.cambiarEstado(id);
    }
}
