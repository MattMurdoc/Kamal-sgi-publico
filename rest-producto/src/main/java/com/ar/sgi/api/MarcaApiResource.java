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

import com.ar.sgi.model.dto.MarcaDTO;
import com.ar.sgi.model.mappers.ListMapper;
import com.ar.sgi.model.mappers.PaginatorMapper;
import com.ar.sgi.model.Respuesta;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Marca;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.services.MarcaService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/marca")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Marca Resource", description = "Gesti\u00F3n de todo lo relacionado a Marca")
public class MarcaApiResource {

    private static final Logger LOGGER = Logger.getLogger(MarcaApiResource.class.getName());

    @Inject
    MarcaService marcaService;

    @POST
    @Path("/{pagina}/{items}")
    @Operation(summary = "Listar marcas de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Marcas paginadas"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response list(@PathParam("pagina") Integer pagina, @PathParam("items") Integer items, MarcaDTO marcaDTO) {
        try {
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            Paginator<Marca> marcaPaginator = marcaService.listar(pagina, items, mapper.map(marcaDTO, Marca.class));

            if (marcaPaginator != null && marcaPaginator.validar()) {
                return Response.ok().entity(PaginatorMapper.mapPaginator(marcaPaginator, MarcaDTO.class)).build();
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
    @Operation(summary = "Persistir una marca")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Marca creada exitósamente"),
    })
    public Response agregar(@Valid MarcaDTO marcaDTO) {
        try {
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            marcaService.agregar(mapper.map(marcaDTO, Marca.class));
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

    @PUT
    @Operation(summary = "Actualizar una marca")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Marca actualizada exitósamente"),
            @APIResponse(responseCode = "400", description = "Identificador de marca no válido")
    })
    public Response actualizar(@Valid MarcaDTO marcaDTO) {
        try {
            if (marcaDTO.getId() == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new Respuesta("Identificador no válido")).build();
            }
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            marcaService.actualizar(mapper.map(marcaDTO, Marca.class));
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
    @Operation(summary = "Eliminar/restaurar una marca")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Marca eliminada/restaurada exitósamente"),
    })
    public Response cambiarEstado(@PathParam("id") Long id) {
        try {
            marcaService.cambiarEstado(id);
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

    @GET
    @Path("/get/{id}")
    @Operation(summary = "Devuelve la marca a partir de un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Marca"),
            @APIResponse(responseCode = "404", description = "No existe la familia")
    })
    public Response getMarcaById(@PathParam("id") Long marcaId) {
        try {
            Marca marca = marcaService.buscarMarca(marcaId);
            if (marca == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No existe la marca")).build();
            } else {
                Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
                return Response.ok(mapper.map(marca, MarcaDTO.class)).build();
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
    @Operation(summary = "Listar todas las marcas")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Marcas"),
            @APIResponse(responseCode = "204", description = "No hay resultados"),
    })
    public Response listarTodoMarca() {
        try {
            List<Marca> marcas = marcaService.listarTodo();

            if (marcas != null && !marcas.isEmpty()) {
                return Response.ok(ListMapper.mapList(marcas, MarcaDTO.class)).build();
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
