package com.ar.sgi.restclient.productos;

import com.ar.sgi.models.productos.MarcaDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/marca")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "producto")
public interface MarcaService {

    @POST
    @Path("/{pagina}/{items}")
    Response list(@PathParam("pagina") Integer pagina, @PathParam("items") Integer items, MarcaDTO marcaDTO);

    @POST
    Response agregar(@Valid MarcaDTO marcaDTO);

    @PUT
    Response actualizar(@Valid MarcaDTO marcaDTO);

    @PUT
    @Path("/{id}")
    Response cambiarEstado(@PathParam("id") Long id);

    @GET
    @Path("/get/{id}")
    Response getMarcaById(@PathParam("id") Long marcaId);

    @GET
    @Path("/all")
    Response listarTodoMarca();

}