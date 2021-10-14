package com.ar.sgi.restclient.productos;

import com.ar.sgi.models.productos.ProductoListaDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/lista")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "producto")
public interface ProductoListaService {

    @GET
    Response list();

    @POST
    Response agregar(@Valid ProductoListaDTO productoListaDTO);

    @PUT
    Response actualizar(@Valid ProductoListaDTO productoListaDTO);
}
