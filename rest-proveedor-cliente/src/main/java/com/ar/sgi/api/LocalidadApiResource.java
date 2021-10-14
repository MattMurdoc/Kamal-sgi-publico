package com.ar.sgi.api;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Localidad;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Provincia;
import com.ar.sgi.model.Respuesta;
import com.ar.sgi.model.dto.LocalidadDTO;
import com.ar.sgi.model.mappers.ListMapper;
import com.ar.sgi.model.mappers.PaginatorMapper;
import com.ar.sgi.service.LocalidadService;
import com.ar.sgi.service.ProvinciaService;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ejb.Local;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/localidad")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Localidad Api Resource", description = "Gesti\u00F3n de todo lo relacionado a las localidades")
public class LocalidadApiResource {

    private static final Logger LOGGER = Logger.getLogger(LocalidadApiResource.class.getName());

    @Inject
    LocalidadService localidadService;
    @Inject
    ProvinciaService provinciaService;


    @GET
    @Path("/{pagina}/{item}")
    @Operation(summary = "Listar localidades de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Localidades paginadas"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarLocalidades(@PathParam("pagina") int pagina, @PathParam("item") int item) {
        try {
            Paginator<Localidad> localidadPaginator = localidadService.listarTodo(pagina, item);
            if (localidadPaginator.validar()) {
                return Response.ok().entity(PaginatorMapper.mapPaginator(localidadPaginator, LocalidadDTO.class)).build();
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
    @Path("/{id}")
    @Operation(summary = "Devuelve una localidad mediante un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Localidad"),
            @APIResponse(responseCode = "404", description = "No se encontro una localidad con ese identificador")
    })
    public Response getById(@PathParam("id") Long id) {
        try {
            Localidad localidad = localidadService.getById(id);
            if (localidad == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No existe la localidad")).build();
            } else {
                Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
                return Response.ok(mapper.map(localidad, LocalidadDTO.class)).build();
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

    @GET
    @Path("/get/{provincia}")
    @Operation(summary = "Listar localidades que pertenezcan a una provincia")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Localidades que pertenecen a una provincia"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarLocalidadesByProvincia(@PathParam("provincia") Long provinciaId) {
        try {
            Provincia provincia = provinciaService.getById(provinciaId);
            if (provincia == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("No existe la provincia").build();
            }

            List<Localidad> localidad = localidadService.listarTodoByProvincia(provincia);
            if (localidad != null && !localidad.isEmpty()) {
                return Response.ok(ListMapper.mapList(localidad, LocalidadDTO.class)).build();
            } else {
                return Response.noContent().build();
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
    @Operation(summary = "Persistir una localidad")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Localidad persisitida éxitosamente"),
    })
    public Response agregar(@Valid LocalidadDTO localidadDTO) {
        try {
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            localidadService.agregar(mapper.map(localidadDTO, Localidad.class));
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
            @APIResponse(responseCode = "202", description = "Localidad actualizada éxitosamente"),
            @APIResponse(responseCode = "400", description = "Identificador de localidad no válido")
    })
    public Response actualizar(@Valid LocalidadDTO localidadDTO) {
        try {
            if (localidadDTO.getId() == null || localidadDTO.getId() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new Respuesta("Identificador no válido")).build();
            }
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            localidadService.actualizar(mapper.map(localidadDTO, Localidad.class));
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
