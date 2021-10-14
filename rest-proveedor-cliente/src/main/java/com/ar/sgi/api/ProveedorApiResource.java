package com.ar.sgi.api;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Proveedor;
import com.ar.sgi.model.Respuesta;
import com.ar.sgi.model.dto.ProveedorDTO;
import com.ar.sgi.model.dto.ProveedorFiltroDTO;
import com.ar.sgi.model.mappers.PaginatorMapper;
import com.ar.sgi.service.ProveedorService;
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
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/proveedor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Proveedor Api Resource", description = "Gesti\u00F3n de todo lo relacionado a los proveedores")
public class ProveedorApiResource {

    private static final Logger LOGGER = Logger.getLogger(ProveedorApiResource.class.getName());

    @Inject
    ProveedorService proveedorService;

    @GET
    @Path("/{pagina}/{item}")
    @Operation(summary = "Listar departamentos de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Departamentos paginados"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarProveedores(@PathParam("pagina") int pagina, @PathParam("item") int item) {
        try {
            Paginator<Proveedor> proveedorPaginator = proveedorService.listarTodo(pagina, item);

            if (proveedorPaginator.validar()) {
                return Response.ok().entity(PaginatorMapper.mapPaginator(proveedorPaginator, ProveedorDTO.class)).build();
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
    @Operation(summary = "Devuelve un proveedor mediante un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Proveedor"),
            @APIResponse(responseCode = "404", description = "No se encontro un proveedor con ese identificador")
    })
    public Response getById(@PathParam("id") Long id) {
        try {
            Proveedor proveedor = proveedorService.getById(id);
            if (proveedor == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No existe el proveedor")).build();
            } else {
                Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
                return Response.ok(mapper.map(proveedor, ProveedorDTO.class)).build();
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
    public Response agregar(@Valid ProveedorDTO proveedorDTO) {
        try {
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            proveedorService.agregar(mapper.map(proveedorDTO, Proveedor.class));
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
    @Operation(summary = "Persistir un proveedor")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Departamento persisitido éxitosamente"),
    })
    public Response actualizar(@Valid ProveedorDTO proveedorDTO) {
        try {
            if (proveedorDTO.getId() == null || proveedorDTO.getId() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new Respuesta("Identificador no válido")).build();
            }
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            proveedorService.actualizar(mapper.map(proveedorDTO, Proveedor.class));
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

    @POST
    @Path("/{pagina}/{items}")
    @Operation(summary = "Buscar todos los clientes")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Clientes"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response buscar(@PathParam("pagina") int pagina, @PathParam("items") int items, ProveedorFiltroDTO proveedorFiltroDTO) {
        Paginator<Proveedor> proveedorPaginator = null;
        try {
            proveedorPaginator = proveedorService.listar(pagina, items, proveedorFiltroDTO);
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
        return Response.ok().entity(proveedorPaginator).build();
    }
}
