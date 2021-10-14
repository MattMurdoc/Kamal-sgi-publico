package com.ar.sgi.restclient.proveedores_clientes;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.models.proveedores_clientes.LocalidadDTO;
import com.ar.sgi.models.proveedores_clientes.ProvinciaDTO;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;

@Path("/provincia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "provcli")
public interface ProvinciaService {

    @GET
    @Path("/{pagina}/{item}")
    Response listarProvinciasPaginated(@PathParam("pagina") int pagina, @PathParam("item") int item);

    @GET
    @Path("/{id}")
    Response getById(@PathParam("id") Long id);

    @POST
    Response agregar(@Valid ProvinciaDTO provinciaDTO);

    @PUT
    Response actualizar(@Valid ProvinciaDTO provinciaDTO);

    @GET
    @Path("/all")
    Response listarProvincias();
}
