package com.ar.sgi.api;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Venta;
import com.ar.sgi.model.dto.VentaFiltroDTO;
import com.ar.sgi.services.VentaService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/venta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Venta Api Resource", description = "Gesti\u00F3n de todo lo relacionado a ventas")
public class VentaApiResource {

    @Inject
    VentaService ventaService;

    @GET
    @Path("/{pagina}/{item}")
    public Response listarVenta(@PathParam("pagina") int pagina, @PathParam("item") int item) {
        try {
            Paginator<Venta> venta = ventaService.listarTodo(pagina, item);

            if (venta.validar()) {
                return Response.ok().entity(venta).build();
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
    @Path("/{pagina}/{items}")
    public Response buscarFiltrando(@PathParam("pagina") int pagina, @PathParam("items") int items, VentaFiltroDTO ventaFiltroDTO) {
        Paginator<Venta> ventaPaginator = null;
        try {
            ventaPaginator = ventaService.listar(pagina, items, ventaFiltroDTO);
        } catch (ServiceLayerException ex) {
            return Response.serverError().entity(ex).build();
        } catch (Exception e) {
            return Response.serverError().entity(new ApiResourceException(ApiResourceException.GENERIC_MESSAGE)).build();
        }
        return Response.ok().entity(ventaPaginator).build();
    }

    @POST
    public Response agregar(@Valid Venta venta) {
        try {
            ventaService.agregar(venta);
        } catch (ServiceLayerException ex) {
            return Response.serverError().entity(ex).build();
        } catch (Exception e) {
            return Response.serverError().entity(new ApiResourceException(ApiResourceException.GENERIC_MESSAGE)).build();
        }
        return Response.ok().build();
    }
}
