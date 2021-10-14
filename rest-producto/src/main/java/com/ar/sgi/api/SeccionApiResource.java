package com.ar.sgi.api;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Departamento;
import com.ar.sgi.model.Paginator;

import com.ar.sgi.model.Seccion;
import com.ar.sgi.model.dto.SeccionDTO;
import com.ar.sgi.model.mappers.ListMapper;
import com.ar.sgi.model.mappers.PaginatorMapper;
import com.ar.sgi.services.DepartamentoService;
import com.ar.sgi.services.SeccionService;
import com.ar.sgi.model.Respuesta;
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

@Path("/seccion")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Seccion Api Resource", description = "Gesti\u00F3n de todo lo relacionado a Seccion")
public class SeccionApiResource {

    private static Logger LOGGER = Logger.getLogger(SeccionApiResource.class.getName());

    @Inject
    SeccionService seccionService;
    @Inject
    DepartamentoService departamentoService;

    @GET
    @Path("/{pagina}/{item}/{departamentoId}/{estado}")
    @Operation(summary = "Listar secciones de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Secciones paginadas"),
            @APIResponse(responseCode = "204", description = "No hay resultados"),
            @APIResponse(responseCode = "404", description = "No existe el departamento")
    })
    public Response listarSeccion(@PathParam("pagina") int pagina, @PathParam("item") int item, @PathParam("departamentoId") long departamentoId, @PathParam("estado") boolean estado) {
        try {
            Departamento departamento = departamentoService.buscarDepartamento(departamentoId);
            if (departamento == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No existe el departamento")).build();
            }
            Paginator<Seccion> seccionPaginator = seccionService.listarTodo(pagina, item, departamento, estado);

            if (seccionPaginator != null && seccionPaginator.validar()) {
                return Response.ok().entity(PaginatorMapper.mapPaginator(seccionPaginator, SeccionDTO.class)).build();
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
    @Path("/{departamento}")
    @Operation(summary = "Listar todas las secciones que pertenezcan a un departamento")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Secciones que pertenecen a un departamento"),
            @APIResponse(responseCode = "204", description = "No hay resultados"),
            @APIResponse(responseCode = "404", description = "No existe el departamento")
    })
    public Response listarByDepartamento(@PathParam("departamento") Long departamentoId) {
        try {
            Departamento departamento = departamentoService.buscarDepartamento(departamentoId);
            if (departamento == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No existe el departamento")).build();
            }
            List<Seccion> secciones = seccionService.listarByDepartamento(departamento);
            if (secciones != null && !secciones.isEmpty()) {
                return Response.ok(ListMapper.mapList(secciones, SeccionDTO.class)).build();
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
    @Path("/get/{id}")
    @Operation(summary = "Devuelve una sección a partir de un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Seccion"),
            @APIResponse(responseCode = "404", description = "No existe la seccion")
    })
    public Response getSeccionById(@PathParam("id") Long seccionId) {
        try {
            Seccion seccion = seccionService.buscarSeccion(seccionId);
            if (seccion == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No existe la seccion")).build();
            } else {
                Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
                return Response.ok(mapper.map(seccion, SeccionDTO.class)).build();
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
    @Operation(summary = "Persistir una sección")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Sección creada exitósamente"),
    })
    public Response agregar(@Valid SeccionDTO seccionDTO) {
        try {
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            seccionService.agregar(mapper.map(seccionDTO, Seccion.class));
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
    @Operation(summary = "Actualizar una sección")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Sección actualizada exitósamente"),
            @APIResponse(responseCode = "400", description = "Identificador de sección no válido")
    })
    public Response actualizar(@Valid SeccionDTO seccionDTO) {
        try {
            if(seccionDTO.getId() == null || seccionDTO.getId() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new Respuesta("Identificador no válido")).build();
            }
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            seccionService.actualizar(mapper.map(seccionDTO, Seccion.class));
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
    @Operation(summary = "Eliminar/restaurar una sección")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Sección eliminada/restaurada exitósamente"),
    })
    public Response cambiarEstado(@PathParam("id") Long id) {
        try {
            seccionService.cambiarEstado(id);
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
}