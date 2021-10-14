package com.ar.sgi.api;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Respuesta;
import com.ar.sgi.model.Rol;
import com.ar.sgi.model.dto.RolDTO;
import com.ar.sgi.model.mappers.ListMapper;
import com.ar.sgi.model.mappers.PaginatorMapper;
import com.ar.sgi.services.RolService;
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

@Path("/rol")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Rol Api Resource", description = "Gesti\u00F3n de todo lo relacionado a Rol")
public class RolApiResource {
    private static Logger LOGGER = Logger.getLogger(RolApiResource.class.getName());

    @Inject
    RolService rolService;

    @GET
    @Operation(summary = "Listar todos los roles")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Roles"),
            @APIResponse(responseCode = "204", description = "No hay resultados"),
    })
    public Response listarRoles() {
        try {
            List<Rol> roles = rolService.listarTodo();

            if (roles != null && !roles.isEmpty()) {
                return Response.ok().entity(ListMapper.mapList(roles, RolDTO.class)).build();
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

    @GET
    @Path("/{pagina}/{item}")
    @Operation(summary = "Listar roles de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Roles paginados"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarRolesPag(@PathParam("pagina") int pagina, @PathParam("item") int item) {
        try {
            Paginator<Rol> rolPaginator = rolService.listarTodoPag(pagina, item);

            if (rolPaginator.validar()) {
                return Response.ok().entity(PaginatorMapper.mapPaginator(rolPaginator, RolDTO.class)).build();
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
}
