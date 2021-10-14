package com.ar.sgi.api.proveedores_clientes;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.models.proveedores_clientes.ClienteDTO;
import com.ar.sgi.models.proveedores_clientes.ClienteFiltroDTO;
import com.ar.sgi.restclient.proveedores_clientes.ClienteService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;

@Path("/v1/proveedorcliente/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Cliente Api Resource", description = "Gesti\u00F3n de todo lo relacionado a los clientes")
public class ClienteApiResource {

    @Inject
    @RestClient
    ClienteService clienteService;

    @POST
    @Path("/{pagina}/{items}")
    @Operation(summary = "Buscar todos los clientes")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Clientes"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response buscar(@PathParam("pagina") int pagina, @PathParam("items") int items, ClienteFiltroDTO clienteFiltroDTO) {
        return clienteService.buscar(pagina, items, clienteFiltroDTO);
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Devuelve un cliente mediante un indentificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Clientes"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response getById(@PathParam("id") Long id) {
        return clienteService.getById(id);
    }

    @POST
    @Operation(summary = "Persistir un cliente")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "cliente persisitido éxitosamente"),
    })
    public Response agregar(@Valid ClienteDTO clienteDTO) {
        return clienteService.agregar(clienteDTO);
    }

    @PUT
    @Operation(summary = "Actualizar un Cliente")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Cliente actualizado éxitosamente"),
            @APIResponse(responseCode = "400", description = "Identificador de cliente no válido")
    })
    public Response actualizar(@Valid ClienteDTO clienteDTO) {
        return clienteService.actualizar(clienteDTO);
    }

    @GET
    @Path("/tipoDNI")
    @Operation(summary = "Devuelve los tipos de DNI")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Lista de movimientos de stock"),
    })
    public Response getTipoDNI() {
        return clienteService.getTipoDNI();
    }

    @GET
    @Path("/categoriaIVA")
    @Operation(summary = "Devuelve las categorías de IVA")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Lista de movimientos de stock"),
    })
    public Response getCategoriaIVA() {
        return clienteService.getCategoriaIVA();
    }
}