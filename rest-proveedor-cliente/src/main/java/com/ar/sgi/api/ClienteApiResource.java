package com.ar.sgi.api;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Cliente;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Respuesta;
import com.ar.sgi.model.dto.ClienteDTO;
import com.ar.sgi.model.dto.ClienteFiltroDTO;
import com.ar.sgi.model.mappers.PaginatorMapper;
import com.ar.sgi.service.ClienteService;
import com.ar.sgi.service.MaestroService;
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

@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Cliente Api Resource", description = "Gesti\u00F3n de todo lo relacionado a los clientes")
public class ClienteApiResource {

    private static final Logger LOGGER = Logger.getLogger(ClienteApiResource.class.getName());

    @Inject
    ClienteService clienteService;

    @Inject
    MaestroService maestroService;

    @POST
    @Path("/{pagina}/{items}")
    @Operation(summary = "Buscar todos los clientes")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Clientes"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response buscar(@PathParam("pagina") int pagina, @PathParam("items") int items, ClienteFiltroDTO clienteFiltroDTO) {
        try {
            Paginator<ClienteDTO> clientePaginator = clienteService.listar(pagina, items, clienteFiltroDTO);
            if(clientePaginator != null && clientePaginator.validar()) {
                return Response.ok().entity(clientePaginator).build();
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

    @GET
    @Path("/{id}")
    @Operation(summary = "Devuelve un cliente mediante un indentificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Clientes"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response getById(@PathParam("id") Long id) {
        try {
            Cliente cliente = clienteService.getById(id);
            if (cliente == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No existe el cliente")).build();
            } else {
                ClienteDTO clienteDTO = new ClienteDTO();
                clienteDTO.setCategoriaIVA(cliente.getCategoriaIVA());
                clienteDTO.setTipoDNI(cliente.getTipoDNI());
                clienteDTO.setCuit(cliente.getCuit());
                clienteDTO.setRazonSocial(cliente.getRazonSocial());
                clienteDTO.setDomicilio(cliente.getDomicilio());
                clienteDTO.setId(cliente.getId());
                return Response.ok(clienteDTO).build();
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
    @Operation(summary = "Persistir un cliente")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "cliente persisitido éxitosamente"),
    })
    public Response agregar(@Valid ClienteDTO clienteDTO) {
        try {
            Cliente cliente = new Cliente();
            cliente.setCategoriaIVA(clienteDTO.getCategoriaIVA());
            cliente.setCuit(clienteDTO.getCuit());
            cliente.setRazonSocial(clienteDTO.getRazonSocial());
            cliente.setDomicilio(clienteDTO.getDomicilio());
            cliente.setTipoDNI(clienteDTO.getTipoDNI());
            clienteService.agregar(cliente);
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
    @Operation(summary = "Actualizar un Cliente")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Cliente actualizado éxitosamente"),
            @APIResponse(responseCode = "400", description = "Identificador de cliente no válido")
    })
    public Response actualizar(@Valid ClienteDTO clienteDTO) {
        try {
            if (clienteDTO.getId() == null || clienteDTO.getId() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new Respuesta("Identificador no válido")).build();
            }
            Cliente cliente = new Cliente();
            cliente.setCategoriaIVA(clienteDTO.getCategoriaIVA());
            cliente.setCuit(clienteDTO.getCuit());
            cliente.setRazonSocial(clienteDTO.getRazonSocial());
            cliente.setDomicilio(clienteDTO.getDomicilio());
            cliente.setTipoDNI(clienteDTO.getTipoDNI());
            cliente.setId(clienteDTO.getId());
            clienteService.actualizar(cliente);
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
    @Path("/tipoDNI")
    @Operation(summary = "Devuelve los tipos de DNI")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Lista de movimientos de stock"),
    })
    public Response getTipoDNI() {
        try {
            return Response.ok(maestroService.getTipoDNI()).build();
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
    @Path("/categoriaIVA")
    @Operation(summary = "Devuelve las categorías de IVA")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Lista de movimientos de stock"),
    })
    public Response getCategoriaIVA() {
        try {
            return Response.ok(maestroService.getCategoriaIVA()).build();
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