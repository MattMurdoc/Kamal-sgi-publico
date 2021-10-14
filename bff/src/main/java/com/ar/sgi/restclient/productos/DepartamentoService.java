package com.ar.sgi.restclient.productos;

import com.ar.sgi.models.productos.DepartamentoDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/departamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "producto")
public interface DepartamentoService {

    @GET
    @Path("/{pagina}/{item}")
    Response listarDepartamento(@PathParam("pagina") int pagina, @PathParam("item") int item);

    @GET
    @Path("/all")
    Response listarTodoDepartamento();

    @GET
    @Path("/get/{id}")
    Response getDepartamentoById(@PathParam("id") Long departamentoId);

    @POST
    Response agregar(@Valid DepartamentoDTO departamentoDTO);

    @PUT
    Response actualizar(@Valid DepartamentoDTO departamentoDTO);

    @PUT
    @Path("/{id}")
    Response cambiarEstado(@PathParam("id") Long id);
}
