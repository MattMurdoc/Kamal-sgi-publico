package com.ar.sgi.api;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Contacto;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Provincia;
import com.ar.sgi.model.Respuesta;
import com.ar.sgi.model.dto.ProvinciaDTO;
import com.ar.sgi.model.mappers.ListMapper;
import com.ar.sgi.model.mappers.PaginatorMapper;
import com.ar.sgi.service.ProvinciaService;
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

@Path("/provincia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Provincia Api Resource", description = "Gesti\u00F3n de todo lo relacionado a las provincias")
public class ProvinciaApiResource {

    private static final Logger LOGGER = Logger.getLogger(ProvinciaApiResource.class.getName());

    @Inject
    ProvinciaService provinciaService;

    @GET
    @Path("/{pagina}/{item}")
    @Operation(summary = "Listar provincias de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Provincias paginados"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarProvinciasPaginated(@PathParam("pagina") int pagina, @PathParam("item") int item) {
        try {
            Paginator<Provincia> provinciaPaginator = provinciaService.listarTodo(pagina, item);
            if (provinciaPaginator.validar()) {
                return Response.ok().entity(PaginatorMapper.mapPaginator(provinciaPaginator, ProvinciaDTO.class)).build();
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
    @Operation(summary = "Devuelve una provincia mediante un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Provincia"),
            @APIResponse(responseCode = "404", description = "No se encontro una provincia con ese identificador")
    })
    public Response getById(@PathParam("id") Long id) {
        try {
            Provincia provincia = provinciaService.getById(id);
            if (provincia == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No existe la provincia")).build();
            } else {
                Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
                return Response.ok(mapper.map(provincia, ProvinciaDTO.class)).build();
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
    public Response agregar(@Valid ProvinciaDTO provinciaDTO) {
        try {
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            provinciaService.agregar(mapper.map(provinciaDTO, Provincia.class));
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
    public Response actualizar(@Valid ProvinciaDTO provinciaDTO) {
        try {
            if (provinciaDTO.getId() == null || provinciaDTO.getId() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new Respuesta("Identificador no válido")).build();
            }
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            provinciaService.actualizar(mapper.map(provinciaDTO, Provincia.class));
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
    @Operation(summary = "Listar todas las provincias")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Provincias"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarProvincias() {
        try {
            List<Provincia> provincias = provinciaService.listar();

            if (provincias != null && !provincias.isEmpty()) {
                return Response.ok().entity(ListMapper.mapList(provincias, ProvinciaDTO.class)).build();
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