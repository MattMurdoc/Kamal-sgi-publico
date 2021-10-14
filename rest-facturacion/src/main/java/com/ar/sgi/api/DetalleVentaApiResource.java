package com.ar.sgi.api;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.DetalleVenta;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.services.DetalleVentaService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/detalleVenta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Detalle Venta Api Resource", description = "Gesti\u00F3n de todo lo relacionado a detalles de venta")
public class DetalleVentaApiResource {

    @Inject
    DetalleVentaService detalleVentaService;

    @GET
    @Path("/{pagina}/{item}")
    public Response listarDetalleVenta(@PathParam("pagina") int pagina, @PathParam("item") int item) {
        try {
            Paginator<DetalleVenta> detalleVenta = detalleVentaService.listarTodo(pagina, item);

            if (detalleVenta.validar()) {
                return Response.ok().entity(detalleVenta).build();
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
    public Response agregar(@Valid DetalleVenta detalleVenta) {
        try {
            detalleVentaService.agregar(detalleVenta);
        } catch (ServiceLayerException ex) {
            return Response.serverError().entity(ex).build();
        } catch (Exception e) {
            return Response.serverError().entity(new ApiResourceException(ApiResourceException.GENERIC_MESSAGE)).build();
        }
        return Response.ok().build();
    }
}
