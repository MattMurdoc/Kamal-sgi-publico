package com.ar.sgi.restclient.productos;

import com.ar.sgi.models.productos.SubFamiliaDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/subfamilia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "producto")
public interface SubfamiliaService {

    @GET
    @Path("/{pagina}/{item}/{familia}/{estado}")
    Response listarSubFamiliasByFamilia(@PathParam("pagina") int pagina, @PathParam("item") int item, @PathParam("familia") Long familiaId, @PathParam("estado") Boolean estado);

    @GET
    @Path("/{familia}")
    Response listarSubFamiliasByFamilia(@PathParam("familia") Long familiaId);

    @GET
    @Path("/get/{id}")
    Response getSubfamiliaById(@PathParam("id") Long subfamiliaId);

    @POST
    Response agregar(@Valid SubFamiliaDTO subFamiliaDTO);

    @PUT
    Response actualizar(@Valid SubFamiliaDTO subFamiliaDTO);

    @PUT
    @Path("/{id}")
    Response cambiarEstado(@PathParam("id") Long id);
}
