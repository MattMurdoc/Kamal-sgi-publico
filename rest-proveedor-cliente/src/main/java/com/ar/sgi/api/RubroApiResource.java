package com.ar.sgi.api;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.*;
import com.ar.sgi.model.dto.ProvinciaDTO;
import com.ar.sgi.model.dto.RubroDTO;
import com.ar.sgi.model.mappers.ListMapper;
import com.ar.sgi.model.mappers.PaginatorMapper;
import com.ar.sgi.service.LocalidadService;
import com.ar.sgi.service.RubroService;
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

@Path("/rubro")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Rubro Api Resource", description = "Gesti\u00F3n de todo lo relacionado a los rubros")
public class RubroApiResource {

    private static final Logger LOGGER = Logger.getLogger(RubroApiResource.class.getName());

    @Inject
    RubroService rubroService;

    @GET
    @Path("/{pagina}/{item}")
    @Operation(summary = "Listar rubros de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Rubro paginados"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarRubros(@PathParam("pagina") int pagina, @PathParam("item") int item) {
        try {
            Paginator<Rubro> rubroPaginator = rubroService.listarTodo(pagina, item);

            if (rubroPaginator.validar()) {
                return Response.ok().entity(PaginatorMapper.mapPaginator(rubroPaginator, RubroDTO.class)).build();
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
    @Operation(summary = "Devuelve un rubro mediante un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Rubro"),
            @APIResponse(responseCode = "404", description = "No se encontro un rubro con ese identificador")
    })
    public Response getById(@PathParam("id") Long id) {
        try {
            Rubro rubro = rubroService.getById(id);
            if (rubro == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No existe el rubro")).build();
            } else {
                Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
                return Response.ok(mapper.map(rubro, RubroDTO.class)).build();
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
    @Operation(summary = "Persistir un rubro")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Rubro persisitido éxitosamente"),
    })
    public Response agregar(@Valid RubroDTO rubroDTO) {
        try {
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            rubroService.agregar(mapper.map(rubroDTO, Rubro.class));
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
    public Response actualizar(@Valid RubroDTO rubroDTO) {
        try {
            if (rubroDTO.getId() == null || rubroDTO.getId() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new Respuesta("Identificador no válido")).build();
            }
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            rubroService.actualizar(mapper.map(rubroDTO, Rubro.class));
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
    @Path("/all")
    @Operation(summary = "Listar todos los rubros")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Rubros"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarRubro() {
        try {
            List<Rubro> rubros = rubroService.listar();

            if (rubros != null && !rubros.isEmpty()) {
                return Response.ok().entity(ListMapper.mapList(rubros, RubroDTO.class)).build();
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
}

