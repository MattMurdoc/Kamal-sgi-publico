package com.ar.sgi.api;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.*;
import com.ar.sgi.model.dto.PermisoDTO;
import com.ar.sgi.model.dto.UsuarioDTO;
import com.ar.sgi.model.mappers.ListMapper;
import com.ar.sgi.model.mappers.PaginatorMapper;
import com.ar.sgi.services.PermisoService;
import com.ar.sgi.services.RolService;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/permiso")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Permiso Api Resource", description = "Gesti\u00F3n de todo lo relacionado a permisos")
public class PermisoApiResource {

    private static Logger LOGGER = Logger.getLogger(PermisoApiResource.class.getName());
    @Inject
    PermisoService permisoService;
    @Inject
    RolService rolService;

    @GET
    @Path("/{pagina}/{item}")
    @Operation(summary = "Listar permisos de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Permisos paginados"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarPermisos(@PathParam("pagina") int pagina, @PathParam("item") int item) {
        try {
            Paginator<Permiso> permisoPaginator = permisoService.listarTodo(pagina, item);

            if (permisoPaginator.validar()) {
                return Response.ok().entity(PaginatorMapper.mapPaginator(permisoPaginator, PermisoDTO.class)).build();
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
    @Operation(summary = "Persistir un permiso")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Permiso creado exitósamente"),
    })
    public Response agregar(@Valid PermisoDTO permisoDTO) {
        try {
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            permisoService.agregar(mapper.map(permisoDTO, Permiso.class));
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
    @Operation(summary = "Actualizar un permiso")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Permiso actualizado exitósamente"),
            @APIResponse(responseCode = "400", description = "Identificador de permiso no válido")
    })
    public Response actualizar(@Valid PermisoDTO permisoDTO) {
        try {
            if (permisoDTO.getId() == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new Respuesta("Identificador no válido")).build();
            }
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            permisoService.actualizar(mapper.map(permisoDTO, Permiso.class));
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

    @GET
    @Path("/{rol}")
    @Operation(summary = "Listar todos los permisos que pertenezcan a un rol")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Permisos que pertenecen a un rol"),
            @APIResponse(responseCode = "204", description = "No hay resultados"),
            @APIResponse(responseCode = "404", description = "No existe el rol")
    })
    public Response listarPermisosByRoles(@PathParam("rol") Long rolId) {

        try {
            Rol rol = rolService.buscarRol(rolId);
            if (rol == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No existe el rol")).build();
            }
            List<Permiso> permisos = permisoService.listarByRolId(rol);
            if (permisos != null && !permisos.isEmpty()) {
                return Response.ok().entity(ListMapper.mapList(permisos, PermisoDTO.class)).build();
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

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Eliminar un permiso")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Permiso eliminado exitósamente"),
    })
    public Response eliminarPermiso(@PathParam("id") Long id) {
        try {
            permisoService.eliminarPermiso(id);
            return Response.ok().build();
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
}
