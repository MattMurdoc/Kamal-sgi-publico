package com.ar.sgi.restclient.productos;

import com.ar.sgi.models.productos.SeccionDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/seccion")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "producto")
public interface SeccionService {

    @GET
    @Path("/{pagina}/{item}/{departamentoId}/{estado}")
    Response listarSeccion(@PathParam("pagina") int pagina, @PathParam("item") int item, @PathParam("departamentoId") long departamentoId, @PathParam("estado") boolean estado);

    @GET
    @Path("/{departamento}")
    Response listarByDepartamento(@PathParam("departamento") Long departamentoId);

    @GET
    @Path("/get/{id}")
    Response getSeccionById(@PathParam("id") Long seccionId);

    @POST
    Response agregar(@Valid SeccionDTO seccionDTO);

    @PUT
    Response actualizar(@Valid SeccionDTO seccionDTO);

    @PUT
    @Path("/{id}")
    Response cambiarEstado(@PathParam("id") Long id);
}
