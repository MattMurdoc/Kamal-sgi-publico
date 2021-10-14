package com.ar.sgi.api;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ar.sgi.model.*;
import com.ar.sgi.model.Respuesta;
import com.ar.sgi.model.dto.UnidadDTO;
import com.ar.sgi.model.mappers.ListMapper;
import com.ar.sgi.model.mappers.PaginatorMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.services.UnidadService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/unidad")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Unidad Resource", description = "Gesti\u00F3n de todo lo relacionado a Unidad")
public class UnidadApiResource {

    private static final Logger LOGGER = Logger.getLogger(UnidadApiResource.class.getName());

    @Inject
    UnidadService unidadService;

    @GET
    @Path("/get/{id}")
    @Operation(summary = "Devuelve la unidad a partir de un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Unidad"),
            @APIResponse(responseCode = "404", description = "No existe la unidad")
    })
    public Response getUnidadById(@PathParam("id") Long unidadId) {
        try {
            Unidad unidad = unidadService.buscarUnidad(unidadId);
            if (unidad == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No existe la unidad")).build();
            } else {
                Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
                return Response.ok(mapper.map(unidad, UnidadDTO.class)).build();
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
    @Path("/all")
    @Operation(summary = "Listar todas las unidades")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Unidades"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarTodoUnidad() {
        try {
            List<Unidad> unidades = unidadService.listarTodo();

            if (unidades != null && !unidades.isEmpty()) {
                return Response.ok().entity(ListMapper.mapList(unidades, UnidadDTO.class)).build();
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
    @Operation(summary = "Persistir una unidad")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Unidad persisitida éxitosamente"),
    })
    public Response agregar(@Valid UnidadDTO unidadDTO) {
        try {
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            unidadService.agregar(mapper.map(unidadDTO, Unidad.class));
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
    @Path("/{pagina}/{items}")
    @Operation(summary = "Listar unidades de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Unidades paginadas"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response list(@PathParam("pagina") Integer pagina, @PathParam("items") Integer items, UnidadDTO unidadDTO) {
        try {
            Paginator<Unidad> unidadesPaginator = unidadService.listarFiltro(pagina, items, unidadDTO);

            if (unidadesPaginator.validar()) {
                return Response.ok().entity(PaginatorMapper.mapPaginator(unidadesPaginator, UnidadDTO.class)).build();
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

    @PUT
    @Operation(summary = "Actualizar una unidad")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Unidad actualizada éxitosamente"),
            @APIResponse(responseCode = "400", description = "Identificador de unidad no válido")
    })
    public Response actualizar(@Valid UnidadDTO unidadDTO) {
        try {
            if (unidadDTO.getId() == null || unidadDTO.getId() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new Respuesta("Identificador no válido")).build();
            }
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            unidadService.actualizar(mapper.map(unidadDTO, Unidad.class));
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
    @Operation(summary = "Eliminar/restaurar una unidad")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Unidad eliminada/restaurada éxitosamente"),
    })
    public Response cambiarEstado(@PathParam("id") Long id) {
        try {
            unidadService.cambiarEstado(id);
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
}