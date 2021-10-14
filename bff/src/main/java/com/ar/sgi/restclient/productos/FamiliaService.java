package com.ar.sgi.restclient.productos;

import com.ar.sgi.models.productos.FamiliaDTO;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/familia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "producto")
public interface FamiliaService {

        @GET
        @Path("/{pagina}/{item}/{seccion}/{estado}")
        Response listarFamiliaBySeccion(@PathParam("pagina") int pagina, @PathParam("item") int item, @PathParam("seccion") Long seccionId, @PathParam("estado") Boolean estado);

        @GET
        @Path("/{seccion}")
        Response listarBySeccion(@PathParam("seccion") Long seccionId);

        @GET
        @Path("/get/{id}")
        Response getFamiliaById(@PathParam("id") Long familiaId);

        @POST
        Response agregar(@Valid FamiliaDTO familiaDTO);

        @PUT
        @Operation(summary = "Actualizar una familia")
        Response actualizar(@Valid FamiliaDTO familiaDTO);

        @PUT
        @Path("/{id}")
        Response cambiarEstado(@PathParam("id") Long id);
}
