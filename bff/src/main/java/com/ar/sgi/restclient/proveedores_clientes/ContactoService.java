package com.ar.sgi.restclient.proveedores_clientes;

import com.ar.sgi.models.proveedores_clientes.ContactoDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/contacto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "provcli")
public interface ContactoService {

    @GET
    @Path("/{pagina}/{item}")
    Response listarContactos(@PathParam("pagina") int pagina, @PathParam("item") int item);

    @GET
    @Path("/{id}")
    Response getById(@PathParam("id") Long id);

    @GET
    @Path("/get/{proveedor}")
    Response listarContactosByProveedor(@PathParam("proveedor") Long proveedorId);

    @POST
    Response agregar(@Valid ContactoDTO contactoDTO);

    @PUT
    Response actualizar(@Valid ContactoDTO contactoDTO);
}
