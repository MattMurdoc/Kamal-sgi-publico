package com.ar.sgi.api;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Departamento;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.dto.DepartamentoDTO;
import com.ar.sgi.model.mappers.ListMapper;
import com.ar.sgi.model.mappers.PaginatorMapper;
import com.ar.sgi.services.DepartamentoService;
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

@Path("/departamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Departamento Api Resource", description = "Gesti\u00F3n de todo lo relacionado a Departamento")
public class DepartamentoApiResource {

    private static Logger LOGGER = Logger.getLogger(DepartamentoApiResource.class.getName());

    @Inject
    DepartamentoService departamentoService;

    @GET
    @Path("/{pagina}/{item}")
    @Operation(summary = "Listar departamentos de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Departamentos paginados"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarDepartamento(@PathParam("pagina") int pagina, @PathParam("item") int item) {
        try {
            Paginator<Departamento> departamentoPaginator = departamentoService.listarPaginado(pagina, item);

            if (departamentoPaginator != null && departamentoPaginator.validar()) {
                return Response.ok().entity(PaginatorMapper.mapPaginator(departamentoPaginator, DepartamentoDTO.class)).build();
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
    @Path("/all")
    @Operation(summary = "Listar todos los departamentos")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Departamentos"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarTodoDepartamento() {
        try {
            List<Departamento> departamento = departamentoService.listarTodo();

            if (departamento != null && !departamento.isEmpty()) {
                return Response.ok().entity(ListMapper.mapList(departamento, DepartamentoDTO.class)).build();
            } else {
                return Response.noContent().build();
            }

        } catch (ServiceLayerException | PersistanceLayerException e) {
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(e.getId() + "|" + e.getMessage());
            return Response.serverError().entity(respuesta).build();
        } catch (Exception e) {
            ApiResourceException apiResourceException = new ApiResourceException(ApiResourceException.GENERIC_MESSAGE);
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(apiResourceException.getId() + "|" + apiResourceException.getMessage());
            return Response.serverError().entity(respuesta).build();
        }
    }

    @GET
    @Path("/get/{id}")
    @Operation(summary = "Devuelve un departamento mediante un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Departamento"),
            @APIResponse(responseCode = "404", description = "No se encontro un departamento con ese identificador")
    })
    public Response getDepartamentoById(@PathParam("id") Long departamentoId) {
        try {
            Departamento departamento = departamentoService.buscarDepartamento(departamentoId);
            if (departamento == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No existe el departamento")).build();
            } else {
                Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
                return Response.ok(mapper.map(departamento, DepartamentoDTO.class)).build();
            }
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

    @POST
    @Operation(summary = "Persistir un departamento")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Departamento persisitido éxitosamente"),
    })
    public Response agregar(@Valid DepartamentoDTO departamentoDTO) {
        try {
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            departamentoService.agregar(mapper.map(departamentoDTO, Departamento.class));
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
    @Operation(summary = "Actualizar un departamento")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Departamento actualizado éxitosamente"),
            @APIResponse(responseCode = "400", description = "Identificador de departamento no válido")
    })
    public Response actualizar(@Valid DepartamentoDTO departamentoDTO) {
        try {
            if (departamentoDTO.getId() == null || departamentoDTO.getId() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new Respuesta("Identificador no válido")).build();
            }
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            departamentoService.actualizar(mapper.map(departamentoDTO, Departamento.class));
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
    @Operation(summary = "Eliminar/restaurar un departamento")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Departamento eliminado/restaurado éxitosamente"),
    })
    public Response cambiarEstado(@PathParam("id") Long id) {
        try {
            departamentoService.cambiarEstado(id);
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