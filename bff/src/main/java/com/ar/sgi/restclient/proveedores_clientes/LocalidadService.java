package com.ar.sgi.restclient.proveedores_clientes;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.models.proveedores_clientes.LocalidadDTO;
import com.ar.sgi.models.proveedores_clientes.ProveedorDTO;
import com.ar.sgi.models.proveedores_clientes.ProveedorFiltroDTO;
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

@Path("/localidad")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "provcli")
public interface LocalidadService {

    @GET
    @Path("/{pagina}/{item}")
    Response listarLocalidades(@PathParam("pagina") int pagina, @PathParam("item") int item);

    @GET
    @Path("/{id}")
    Response getById(@PathParam("id") Long id);

    @GET
    @Path("/get/{provincia}")
    Response listarLocalidadesByProvincia(@PathParam("provincia") Long provinciaId);

    @POST
    Response agregar(@Valid LocalidadDTO localidadDTO);

    @PUT
    Response actualizar(@Valid LocalidadDTO localidadDTO);
}
