package com.ar.sgi.api;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Respuesta;
import com.ar.sgi.model.Usuario;
import com.ar.sgi.model.dto.PrincipalDTO;
import com.ar.sgi.model.dto.UsuarioDTO;
import com.ar.sgi.model.dto.UsuarioFiltroDTO;
import com.ar.sgi.model.mappers.PaginatorMapper;
import com.ar.sgi.services.PasswordService;
import com.ar.sgi.services.UsuarioService;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Usuario Api Resource", description = "Gesti\u00F3n de todo lo relacionado a Usuario")

public class UsuarioApiResource {
    private static Logger LOGGER = Logger.getLogger(RolApiResource.class.getName());

    @Inject
    UsuarioService usuarioService;
    @Inject
    PasswordService passwordService;

    @GET
    @Path("/{pagina}/{item}")
    @Operation(summary = "Listar usuarios de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Usuarios paginados"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarUsuarios(@PathParam("pagina") int pagina, @PathParam("item") int item) {
        try {
            Paginator<Usuario> usuarioPaginator = usuarioService.listarTodo(pagina, item);

            if (usuarioPaginator.validar()) {
                return Response.ok().entity(PaginatorMapper.mapPaginator(usuarioPaginator, UsuarioDTO.class)).build();
            } else {
                return Response.noContent().build();
            }

        } catch (ServiceLayerException | PersistanceLayerException e) {
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(e.toString());
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return Response.serverError().entity(respuesta).build();
        } catch (Exception e) {
            ApiResourceException apiResourceException = new ApiResourceException(ApiResourceException.GENERIC_MESSAGE);
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(apiResourceException.toString());
            LOGGER.log(Level.SEVERE, apiResourceException.toString(), e);
            return Response.serverError().entity(respuesta).build();
        }
    }

    @POST
    @Operation(summary = "Persistir un usuario")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Usuario creado exitósamente"),
    })
    public Response agregar(@Valid UsuarioDTO usuarioDTO) {
        try {
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            usuarioService.agregar(mapper.map(usuarioDTO, Usuario.class));
            return Response.accepted().build();
        } catch (ServiceLayerException e) {
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(e.toString());
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return Response.serverError().entity(respuesta).build();
        } catch (Exception e) {
            ApiResourceException apiResourceException = new ApiResourceException(ApiResourceException.GENERIC_MESSAGE);
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(apiResourceException.toString());
            LOGGER.log(Level.SEVERE, apiResourceException.toString(), e);
            return Response.serverError().entity(respuesta).build();
        }
    }

    @PUT
    @Operation(summary = "Actualizar un usuario")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Usuario actualizado exitósamente"),
            @APIResponse(responseCode = "400", description = "Identificador de usuario no válido")
    })
    public Response actualizar(@Valid UsuarioDTO usuarioDTO) {
        try {
            if(usuarioDTO.getId() == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new Respuesta("Identificador no válido")).build();
            }
            usuarioDTO.setPassword(passwordService.encriptar(usuarioDTO.getPassword()));
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            usuarioService.actualizar(mapper.map(usuarioDTO, Usuario.class));
            return Response.accepted().build();
        } catch (ServiceLayerException e) {
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(e.toString());
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return Response.serverError().entity(respuesta).build();
        } catch (Exception e) {
            ApiResourceException apiResourceException = new ApiResourceException(ApiResourceException.GENERIC_MESSAGE);
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(apiResourceException.toString());
            LOGGER.log(Level.SEVERE, apiResourceException.toString(), e);
            return Response.serverError().entity(respuesta).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Eliminar/restaurar un usuario")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Usuario eliminado/restaurado exitósamente"),
    })
    public Response cambiarEstado(@PathParam("id") Long id) {
        try {
            usuarioService.cambiarEstado(id);
            return Response.accepted().build();
        } catch (ServiceLayerException | PersistanceLayerException e) {
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(e.toString());
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return Response.serverError().entity(respuesta).build();
        } catch (Exception e) {
            ApiResourceException apiResourceException = new ApiResourceException(ApiResourceException.GENERIC_MESSAGE);
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(apiResourceException.toString());
            LOGGER.log(Level.SEVERE, apiResourceException.toString(), e);
            return Response.serverError().entity(respuesta).build();
        }
    }

    @POST
    @Path("/validate")
    @Operation(summary = "Eliminar/restaurar un usuario")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Usuario correcto"),
    })
    public Response validarUsuario(UsuarioDTO usuarioDTO) {
        try {
            usuarioDTO.setPassword(passwordService.encriptar(usuarioDTO.getPassword()));
            PrincipalDTO principalDTO = usuarioService.validarUsuario(usuarioDTO);
            return Response.ok().entity(principalDTO).build();
        } catch (ServiceLayerException | PersistanceLayerException e) {
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(e.toString());
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return Response.serverError().entity(respuesta).build();
        } catch (Exception e) {
            ApiResourceException apiResourceException = new ApiResourceException(ApiResourceException.GENERIC_MESSAGE);
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(apiResourceException.toString());
            LOGGER.log(Level.SEVERE, apiResourceException.toString(), e);
            return Response.serverError().entity(respuesta).build();
        }
    }

    @POST
    @Path("/{pagina}/{items}")
    @Operation(summary = "Buscar todos los usuarios")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Usuarios"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response buscar(@PathParam("pagina") int pagina, @PathParam("items") int items, UsuarioFiltroDTO usuarioFiltroDTO) {
        Paginator<Usuario> usuarioPaginator = null;
        try {

            usuarioPaginator = usuarioService.listar(pagina, items, usuarioFiltroDTO);

        } catch (ServiceLayerException ex) {
            LOGGER.log(Level.SEVERE, ex.toString());
            return Response.serverError().entity(ex).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            return Response.serverError().entity(new ApiResourceException(ApiResourceException.GENERIC_MESSAGE)).build();
        }
        return Response.ok().entity(usuarioPaginator).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Devuelve un usuario mediante un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Usuario"),
            @APIResponse(responseCode = "404", description = "No se encontro un usuario con ese identificador")
    })
    public Response getById(@PathParam("id") Long id) {
        try {
            Usuario usuario = usuarioService.getById(id);
            if (usuario == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok().entity(usuario).build();
        } catch (ServiceLayerException e) {
            return Response.serverError().entity(e).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
