package com.ar.sgi.restclient.usuarios;

import com.ar.sgi.models.usuarios.PermisoDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/permiso")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "usuario")
public interface PermisoService {

    @GET
    @Path("/{pagina}/{item}")
    Response listarPermisos(@PathParam("pagina") int pagina, @PathParam("item") int item);

    @POST
    Response agregar(@Valid PermisoDTO permisoDTO);

    @PUT
    Response actualizar(@Valid PermisoDTO permisoDTO);

    @GET
    @Path("/{rol}")
    Response listarPermisosByRoles(@PathParam("rol") Long rolId);

    @DELETE
    @Path("/{id}")
    Response eliminarPermiso(@PathParam("id") Long id);
}
