package com.ar.sgi.api;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.FormaPago;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.services.FormaPagoService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/formaPago")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Forma Pago Api Resource", description = "Gesti\u00F3n de todo lo relacionado a las formas de pago")
public class FormaPagoApiResource {

    @Inject
    FormaPagoService formaPagoService;

    @GET
    @Path("/{pagina}/{item}")
    public Response listarFormaPago(@PathParam("pagina") int pagina, @PathParam("item") int item) {
        try {
            Paginator<FormaPago> formaPago = formaPagoService.listarTodo(pagina, item);

            if (formaPago.validar()) {
                return Response.ok().entity(formaPago).build();
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
    public Response agregar(@Valid FormaPago formaPago) {
        try {
            formaPagoService.agregar(formaPago);
        } catch (ServiceLayerException ex) {
            return Response.serverError().entity(ex).build();
        } catch (Exception e) {
            return Response.serverError().entity(new ApiResourceException(ApiResourceException.GENERIC_MESSAGE)).build();
        }
        return Response.ok().build();
    }
}
