package com.ar.sgi.api;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Familia;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.SubFamilia;
import com.ar.sgi.model.dto.SubFamiliaDTO;
import com.ar.sgi.model.mappers.ListMapper;
import com.ar.sgi.model.mappers.PaginatorMapper;
import com.ar.sgi.services.FamiliaService;
import com.ar.sgi.services.SubFamiliaService;
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

@Path("/subfamilia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Subfamilia Api Resource", description = "Gesti\u00F3n de todo lo relacionado a Subfamilia")
public class SubFamiliaApiResource {

    private static final Logger LOGGER = Logger.getLogger(SubFamiliaApiResource.class.getName());

    @Inject
    SubFamiliaService subFamiliaService;
    @Inject
    FamiliaService familiaService;

    @GET
    @Path("/{pagina}/{item}/{familia}/{estado}")
    @Operation(summary = "Listar subfamilias de manera paginada que pertenezcan a una familia")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Subfamilias paginadas que pertenecen a una familia"),
            @APIResponse(responseCode = "204", description = "No hay resultados"),
            @APIResponse(responseCode = "400", description = "No existe la familia")
    })
    public Response listarSubFamiliasByFamilia(@PathParam("pagina") int pagina,
                                               @PathParam("item") int item,
                                               @PathParam("familia") Long familiaId,
                                               @PathParam("estado") Boolean estado) {
        try {
            Familia familia = familiaService.buscarFamilia(familiaId);
            if (familia == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new Respuesta("No existe la familia")).build();
            }

            Paginator<SubFamilia> subFamiliaPaginator = subFamiliaService.listarPaginatedByFamiliaId(pagina, item, familia, estado);

            if (subFamiliaPaginator != null && subFamiliaPaginator.validar()) {
                return Response.ok().entity(PaginatorMapper.mapPaginator(subFamiliaPaginator, SubFamiliaDTO.class)).build();
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
    @Path("/{familia}")
    @Operation(summary = "Listar todas las subfamilias que pertenezcan a una familia")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Subfamilias que pertenecen a una familia"),
            @APIResponse(responseCode = "204", description = "No hay resultados"),
            @APIResponse(responseCode = "404", description = "No existe la familia")
    })
    public Response listarSubFamiliasByFamilia(@PathParam("familia") Long familiaId) {

        try {
            Familia familia = familiaService.buscarFamilia(familiaId);
            if (familia == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("No existe la familia").build();
            }

            List<SubFamilia> subfamilias = subFamiliaService.listarByFamiliaId(familia);

            if (subfamilias != null && !subfamilias.isEmpty()) {
                return Response.ok().entity(ListMapper.mapList(subfamilias, SubFamiliaDTO.class)).build();
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
    @Operation(summary = "Devuelve la subfamilia a partir de un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Subfamilia"),
            @APIResponse(responseCode = "404", description = "No existe la subfamilia")
    })
    public Response getSubfamiliaById(@PathParam("id") Long subfamiliaId) {
        try {
            SubFamilia subFamilia = subFamiliaService.buscarSubfamilia(subfamiliaId);
            if (subFamilia == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No existe la subfamilia")).build();
            } else {
                Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
                return Response.ok(mapper.map(subFamilia, SubFamiliaDTO.class)).build();
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
            @APIResponse(responseCode = "202", description = "Subfamilia creada exitósamente"),
    })
    public Response agregar(@Valid SubFamiliaDTO subFamiliaDTO) {
        try {
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            subFamiliaService.agregar(mapper.map(subFamiliaDTO, SubFamilia.class));
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
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Familia actualizada exitósamente"),
            @APIResponse(responseCode = "400", description = "Identificador de familia no válido")
    })
    public Response actualizar(@Valid SubFamiliaDTO subFamiliaDTO) {
        try {
            if (subFamiliaDTO.getId() == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new Respuesta("Identificador no válido")).build();
            }
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            subFamiliaService.actualizar(mapper.map(subFamiliaDTO, SubFamilia.class));
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
            subFamiliaService.cambiarEstado(id);
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
