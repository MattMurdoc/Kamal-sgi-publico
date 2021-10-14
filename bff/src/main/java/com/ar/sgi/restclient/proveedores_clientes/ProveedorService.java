package com.ar.sgi.restclient.proveedores_clientes;

import com.ar.sgi.models.proveedores_clientes.ProveedorDTO;
import com.ar.sgi.models.proveedores_clientes.ProveedorFiltroDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/proveedor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "provcli")
public interface ProveedorService {

    @GET
    @Path("/{pagina}/{item}")
    Response listarProveedores(@PathParam("pagina") int pagina, @PathParam("item") int item);

    @GET
    @Path("/{id}")
    Response getById(@PathParam("id") Long id);

    @POST
    Response agregar(@Valid ProveedorDTO proveedorDTO);

    @PUT
    Response actualizar(@Valid ProveedorDTO proveedorDTO);

    @POST
    @Path("/{pagina}/{items}")
    Response buscar(@PathParam("pagina") int pagina, @PathParam("items") int items, ProveedorFiltroDTO proveedorFiltroDTO);
}
