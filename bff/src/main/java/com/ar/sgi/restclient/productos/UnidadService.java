package com.ar.sgi.restclient.productos;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.models.productos.DepartamentoDTO;
import com.ar.sgi.models.productos.UnidadDTO;
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

@Path("/unidad")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "producto")
public interface UnidadService {

    @GET
    @Path("/get/{id}")
    Response getUnidadById(@PathParam("id") Long unidadId);

    @GET
    @Path("/all")
    Response listarTodoUnidad();

    @POST
    Response agregar(@Valid UnidadDTO unidadDTO);

    @POST
    @Path("/{pagina}/{items}")
    Response list(@PathParam("pagina") Integer pagina, @PathParam("items") Integer items, UnidadDTO unidadDTO);

    @PUT
    Response actualizar(@Valid UnidadDTO unidadDTO);

    @PUT
    @Path("/{id}")
    Response cambiarEstado(@PathParam("id") Long id);
}
