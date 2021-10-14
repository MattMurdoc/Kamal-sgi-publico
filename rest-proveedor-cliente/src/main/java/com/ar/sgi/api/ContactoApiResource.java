package com.ar.sgi.api;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.*;
import com.ar.sgi.model.Contacto;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Proveedor;
import com.ar.sgi.model.Respuesta;
import com.ar.sgi.model.dto.ContactoDTO;
import com.ar.sgi.model.mappers.PaginatorMapper;
import com.ar.sgi.service.ContactoService;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/contacto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Contacto Api Resource", description = "Gesti\u00F3n de todo lo relacionado a los contactos")
public class ContactoApiResource {

    private static final Logger LOGGER = Logger.getLogger(ContactoApiResource.class.getName());

    @Inject
    ContactoService contactoService;

    @Inject
    ProveedorService proveedorService;

    @GET
    @Path("/{pagina}/{item}")
    @Operation(summary = "Listar contactos de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Contactos paginados"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarContactos(@PathParam("pagina") int pagina, @PathParam("item") int item) {
        try {
            Paginator<Contacto> contactoPaginator = contactoService.listarTodo(pagina, item);
            if (contactoPaginator.validar()) {
                return Response.ok().entity(PaginatorMapper.mapPaginator(contactoPaginator, ContactoDTO.class)).build();
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
    @Operation(summary = "Devuelve un contacto mediante un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Contacto"),
            @APIResponse(responseCode = "404", description = "No se encontro un contacto con ese identificador")
    })
    public Response getById(@PathParam("id") Long id) {
        try {
            Contacto contacto = contactoService.getById(id);
            if (contacto == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No existe el contacto")).build();
            } else {
                Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
                return Response.ok(mapper.map(contacto, ContactoDTO.class)).build();
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
    @Path("/get/{proveedor}")
    @Operation(summary = "Devuelve una lista de contactos de un proveedor")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Contacto"),
            @APIResponse(responseCode = "204", description = "No hay resultados"),
            @APIResponse(responseCode = "404", description = "No se encontro el proveedor con ese identificador")
    })
    public Response listarContactosByProveedor(@PathParam("proveedor") Long proveedorId) {
        try {
            Proveedor proveedor = proveedorService.getById(proveedorId);
            if (proveedor == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No existe el proveedor")).build();
            }

            List<Contacto> contactos = contactoService.listarTodoByProveedor(proveedor);
            if (contactos != null && !contactos.isEmpty()) {
                return Response.ok(contactos).build();
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
    @Operation(summary = "Persistir un contacto")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Contacto persisitido éxitosamente"),
    })
    public Response agregar(@Valid ContactoDTO contactoDTO) {
        try {
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            contactoService.agregar(mapper.map(contactoDTO, Contacto.class));
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
    @Operation(summary = "Actualizar un contacto")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Contacto actualizado éxitosamente"),
            @APIResponse(responseCode = "400", description = "Identificador de contacto no válido")
    })
    public Response actualizar(@Valid ContactoDTO contactoDTO) {
        try {
            if (contactoDTO.getId() == null || contactoDTO.getId() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new Respuesta("Identificador no válido")).build();
            }
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            contactoService.actualizar(mapper.map(contactoDTO, Contacto.class));
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
