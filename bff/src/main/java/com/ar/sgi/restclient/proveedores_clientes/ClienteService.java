package com.ar.sgi.restclient.proveedores_clientes;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.models.proveedores_clientes.ClienteDTO;
import com.ar.sgi.models.proveedores_clientes.ClienteFiltroDTO;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;

@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "provcli")
public interface ClienteService {

    @GET
    @Path("/{pagina}/{item}")
    Response listarClientes(@PathParam("pagina") int pagina, @PathParam("item") int item);

    @GET
    @Path("/{id}")
    Response getById(@PathParam("id") Long id);

    @POST
    Response agregar(@Valid ClienteDTO clienteDTO);

    @POST
    @Path("/{pagina}/{items}")
    Response buscar(@PathParam("pagina") int pagina, @PathParam("items") int items, ClienteFiltroDTO clienteFiltroDTO);

    @PUT
    Response actualizar(@Valid ClienteDTO clienteDTO);

    @GET
    @Path("/tipoDNI")
    Response getTipoDNI();

    @GET
    @Path("/categoriaIVA")
    Response getCategoriaIVA();
}
