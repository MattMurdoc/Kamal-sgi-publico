package com.ar.sgi.api.usuarios;

import com.ar.sgi.models.usuarios.PermisoDTO;
import com.ar.sgi.restclient.usuarios.PermisoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
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

@Path("/v1/usuario/permiso")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Permiso Api Resource", description = "Gesti\u00F3n de todo lo relacionado a permisos")
@SecuritySchemes(value = {
        @SecurityScheme(securitySchemeName = "apiKey",
                type = SecuritySchemeType.HTTP,
                scheme = "Bearer")}
)
public class PermisoApiResource {

    @Inject
    @RestClient
    PermisoService permisoService;

    @GET
    @Path("/{pagina}/{item}")
    @RolesAllowed("admin")
    @SecurityRequirement(name = "apiKey")
    @Operation(summary = "Listar permisos de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Permisos paginados"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarPermisos(@PathParam("pagina") int pagina, @PathParam("item") int item) {
        return permisoService.listarPermisos(pagina, item);
    }

    @POST
    @RolesAllowed("admin")
    @SecurityRequirement(name = "apiKey")
    @Operation(summary = "Persistir un permiso")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Permiso creado exitósamente"),
    })
    public Response agregar(@Valid PermisoDTO permisoDTO) {
        return permisoService.agregar(permisoDTO);
    }

    @PUT
    @RolesAllowed("admin")
    @SecurityRequirement(name = "apiKey")
    @Operation(summary = "Actualizar un permiso")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Permiso actualizado exitósamente"),
            @APIResponse(responseCode = "400", description = "Identificador de permiso no válido")
    })
    public Response actualizar(@Valid PermisoDTO permisoDTO) {
        return permisoService.actualizar(permisoDTO);
    }

    @GET
    @Path("/{rol}")
    @RolesAllowed("admin")
    @SecurityRequirement(name = "apiKey")
    @Operation(summary = "Listar todos los permisos que pertenezcan a un rol")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Permisos que pertenecen a un rol"),
            @APIResponse(responseCode = "204", description = "No hay resultados"),
            @APIResponse(responseCode = "404", description = "No existe el rol")
    })
    public Response listarPermisosByRoles(@PathParam("rol") Long rolId) {
        return permisoService.listarPermisosByRoles(rolId);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    @SecurityRequirement(name = "apiKey")
    @Operation(summary = "Eliminar un permiso")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Permiso eliminado exitósamente"),
    })
    public Response eliminarPermiso(@PathParam("id") Long id) {
        return permisoService.eliminarPermiso(id);
    }
}