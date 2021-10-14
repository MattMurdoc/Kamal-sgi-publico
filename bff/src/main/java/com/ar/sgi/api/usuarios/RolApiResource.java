package com.ar.sgi.api.usuarios;

import com.ar.sgi.restclient.usuarios.RolService;
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
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/usuario/rol")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Rol Api Resource", description = "Gesti\u00F3n de todo lo relacionado a Rol")
@SecuritySchemes(value = {
        @SecurityScheme(securitySchemeName = "apiKey",
                type = SecuritySchemeType.HTTP,
                scheme = "Bearer")}
)
public class RolApiResource {

    @Inject
    @RestClient
    RolService rolService;

    @GET
    @RolesAllowed("admin")
    @SecurityRequirement(name = "apiKey")
    @Operation(summary = "Listar todos los roles")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Roles"),
            @APIResponse(responseCode = "204", description = "No hay resultados"),
    })
    public Response listarRoles() {
        return rolService.listarRoles();
    }
}