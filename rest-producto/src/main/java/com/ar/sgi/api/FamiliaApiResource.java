package com.ar.sgi.api;


import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.*;
import com.ar.sgi.model.dto.FamiliaDTO;
import com.ar.sgi.model.mappers.ListMapper;
import com.ar.sgi.model.mappers.PaginatorMapper;
import com.ar.sgi.services.FamiliaService;
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

@Path("/familia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Familia Api Resource", description = "Gesti\u00F3n de todo lo relacionado a Familia")
public class FamiliaApiResource {

    private static final Logger LOGGER = Logger.getLogger(FamiliaApiResource.class.getName());

    @Inject
    FamiliaService familiaService;
    @Inject
    SeccionService seccionService;

    @GET
    @Path("/{pagina}/{item}/{seccion}/{estado}")
    @Operation(summary = "Listar familias de manera paginada que pertenezcan a una sección")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Familias paginadas que pertenecen a una sección"),
            @APIResponse(responseCode = "204", description = "No hay resultados"),
            @APIResponse(responseCode = "404", description = "No existe el departamento")
    })
    public Response listarFamiliaBySeccion(@PathParam("pagina") int pagina, @PathParam("item") int item, @PathParam("seccion") Long seccionId, @PathParam("estado") Boolean estado) {
        try {
            Seccion seccion = seccionService.buscarSeccion(seccionId);
            if (seccion == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No existe la sección")).build();
            }
            Paginator<Familia> familiaPaginator = familiaService.listarTodoBySeccion(pagina, item, seccion, estado);
            if (familiaPaginator != null && familiaPaginator.validar()) {
                return Response.ok(PaginatorMapper.mapPaginator(familiaPaginator, FamiliaDTO.class)).build();
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
    @Path("/{seccion}")
    @Operation(summary = "Listar todas las familias que pertenezcan a una sección")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Familias que pertenecen a una sección"),
            @APIResponse(responseCode = "204", description = "No hay resultados"),
            @APIResponse(responseCode = "404", description = "No existe la sección")
    })
    public Response listarBySeccion(@PathParam("seccion") Long seccionId) {
        try {
            Seccion seccion = seccionService.buscarSeccion(seccionId);
            if (seccion == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No existe la sección")).build();
            }
            List<Familia> familias = familiaService.listarBySeccion(seccion);
            if (familias != null && !familias.isEmpty()) {
                return Response.ok(ListMapper.mapList(familias, FamiliaDTO.class)).build();
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
    @Operation(summary = "Devuelve la familia a partir de un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Familia"),
            @APIResponse(responseCode = "404", description = "No existe la familia")
    })
    public Response getFamiliaById(@PathParam("id") Long familiaId) {
        try {
            Familia familia = familiaService.buscarFamilia(familiaId);
            if (familia == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No existe la familia")).build();
            }else{
                Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
                return Response.ok(mapper.map(familia, FamiliaDTO.class)).build();
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
    @Operation(summary = "Persistir una familia")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Familia creada exitósamente"),
    })
    public Response agregar(@Valid FamiliaDTO familiaDTO) {
        try {
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            familiaService.agregar(mapper.map(familiaDTO, Familia.class));
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
    @Operation(summary = "Actualizar una familia")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Familia actualizada exitósamente"),
            @APIResponse(responseCode = "400", description = "Identificador de familia no válido")
    })
    public Response actualizar(@Valid FamiliaDTO familiaDTO) {
        try {
            if(familiaDTO.getId() == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new Respuesta("Identificador no válido")).build();
            }
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            familiaService.actualizar(mapper.map(familiaDTO, Familia.class));
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
            familiaService.cambiarEstado(id);
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