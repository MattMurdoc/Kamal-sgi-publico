package com.ar.sgi.restclient.usuarios;

import com.ar.sgi.models.productos.DepartamentoDTO;
import com.ar.sgi.models.usuarios.UsuarioDTO;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "usuario")
public interface UsuarioService {

    @GET
    @Path("/{pagina}/{item}")
    Response listarUsuarios(@PathParam("pagina") int pagina, @PathParam("item") int item);

    @POST
    Response agregar(@Valid UsuarioDTO usuarioDTO);

    @PUT
    Response actualizar(@Valid UsuarioDTO usuarioDTO);

    @PUT
    @Path("/{id}")
    Response cambiarEstado(@PathParam("id") Long id);

    @POST
    @Path("/validate")
    Response validarUsuario(UsuarioDTO usuarioDTO);
}
