package com.ar.sgi.restclient.productos;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.models.productos.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;

@Path("/stock")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "producto")
public interface MovimientoStockService {

    @POST
    @Path("/{pagina}/{item}")
    Response listarMovimientoStock(@PathParam("pagina") int pagina, @PathParam("item") int item, MovimientoStockFiltroDTO filtro);

    @POST
    Response agregar(@Valid MovimientoStockConDetalleDTO movimientoStockConDetalleDTO);

    @GET
    @Path("/tipoMovimientos")
    public Response getTipoMovimientos();

    @GET
    @Path("/tipoMovimientos/{id}/motivos")
    Response getMotivos(@PathParam("id") Long tipoMovimiento);
}
