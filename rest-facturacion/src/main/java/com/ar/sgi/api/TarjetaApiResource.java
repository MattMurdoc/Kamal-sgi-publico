package com.ar.sgi.api;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Tarjeta;
import com.ar.sgi.services.TarjetaService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tarjeta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Tarjeta Api Resource", description = "Gesti\u00F3n de todo lo relacionado a las tarjetas")
public class TarjetaApiResource {

        @Inject
        TarjetaService tarjetaService;

        @GET
        @Path("/{pagina}/{item}")
        public Response listarTarjeta(@PathParam("pagina") int pagina, @PathParam("item") int item) {
            try {
                Paginator<Tarjeta> tarjeta = tarjetaService.listarTodo(pagina, item);

                if (tarjeta.validar()) {
                    return Response.ok().entity(tarjeta).build();
                } else {
                    return Response.noContent().build();
                }

            } catch (ServiceLayerException | PersistanceLayerException e) {
                return Response.serverError().entity(e).build();
            } catch (Exception e) {
                return Response.serverError().entity(e).build();
            }
        }

        @POST
        public Response agregar(@Valid Tarjeta tarjeta) {
            try {
                tarjetaService.agregar(tarjeta);
            } catch (ServiceLayerException ex) {
                return Response.serverError().entity(ex).build();
            } catch (Exception e) {
                return Response.serverError().entity(new ApiResourceException(ApiResourceException.GENERIC_MESSAGE)).build();
            }
            return Response.ok().build();
        }
}
