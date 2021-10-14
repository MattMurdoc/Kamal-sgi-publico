package com.ar.sgi.api.usuarios;

import com.ar.sgi.models.usuarios.UsuarioDTO;
import com.ar.sgi.restclient.usuarios.UsuarioService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Usuario Api Resource", description = "Gesti\u00F3n de todo lo relacionado a Usuario")
@SecuritySchemes(value = {
        @SecurityScheme(securitySchemeName = "apiKey",
                type = SecuritySchemeType.HTTP,
                scheme = "Bearer")}
)
public class UsuarioApiResource {

    @Inject
    @RestClient
    UsuarioService usuarioService;

    @GET
    @Path("/{pagina}/{item}")
    @Operation(summary = "Listar usuarios de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Usuarios paginados"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarUsuarios(@PathParam("pagina") int pagina, @PathParam("item") int item) {
        return usuarioService.listarUsuarios(pagina, item);
    }

    @POST
    @Operation(summary = "Persistir un usuario")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Usuario creado exitósamente"),
    })
    public Response agregar(@Valid UsuarioDTO usuarioDTO) {
        return usuarioService.agregar(usuarioDTO);
    }

    @PUT
    @Operation(summary = "Actualizar un usuario")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Usuario actualizado exitósamente"),
            @APIResponse(responseCode = "400", description = "Identificador de usuario no válido")
    })
    public Response actualizar(@Valid UsuarioDTO usuarioDTO) {
        return usuarioService.actualizar(usuarioDTO);
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Eliminar/restaurar un usuario")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Usuario eliminado/restaurado exitósamente"),
    })
    public Response cambiarEstado(@PathParam("id") Long id) {
        return usuarioService.cambiarEstado(id);
    }

    @POST
    @Path("/validate")
    @PermitAll
    @Operation(summary = "Validar usuario")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Usuario correcto"),
    })
    public Response validarUsuario(UsuarioDTO usuarioDTO) {
        return usuarioService.validarUsuario(usuarioDTO);
    }
}