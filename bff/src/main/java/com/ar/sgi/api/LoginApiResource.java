package com.ar.sgi.api;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.models.Respuesta;
import com.ar.sgi.models.usuarios.PrincipalDTO;
import com.ar.sgi.models.usuarios.UsuarioDTO;
import com.ar.sgi.restclient.usuarios.UsuarioService;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/v1/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Login Api Resource")
public class LoginApiResource {

    private static Logger LOGGER = Logger.getLogger(LoginApiResource.class.getName());

    @Inject
    @RestClient
    UsuarioService usuarioService;

    @POST
    @Path("/doLogin")
    @PermitAll
    public Response doLogin(UsuarioDTO usuarioDTO) {
        try {
            Response response = usuarioService.validarUsuario(usuarioDTO);
            PrincipalDTO principalDTO = response.readEntity(PrincipalDTO.class);
            String token =
                    Jwt.issuer("https://kamalcomestibles.com.ar/issuer")
                            .upn(principalDTO.getUsuario())
                            .groups(new HashSet<>(Collections.singletonList(principalDTO.getRol())))
                            .sign();
            principalDTO.setToken(token);
            return Response.ok().entity(principalDTO).build();
        } catch (Exception e) {
            ApiResourceException apiResourceException = new ApiResourceException(ApiResourceException.GENERIC_MESSAGE);
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(apiResourceException.toString());
            LOGGER.log(Level.SEVERE, apiResourceException.toString(), e);
            return Response.serverError().entity(respuesta).build();
        }

    }
}