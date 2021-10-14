package com.ar.sgi.api.productos;

import com.ar.sgi.models.productos.*;
import com.ar.sgi.restclient.productos.MovimientoStockService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/producto/stock")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Movimientos de Stock Api Resource", description = "Gesti\u00F3n de todo lo relacionado a los movimientos de stock")
public class MovimientoStockApiResource {

    @Inject
    @RestClient
    MovimientoStockService movimientoStockService;

    @POST
    @Path("/{pagina}/{item}")
    @Operation(summary = "Listar productos de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Movimientos de stock paginados"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarMovimientoStock(@PathParam("pagina") int pagina, @PathParam("item") int item, MovimientoStockFiltroDTO filtro) {
        return movimientoStockService.listarMovimientoStock(pagina, item, filtro);
    }

    @POST
    @Operation(summary = "Persistir un movimiento de stock")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Movimiento de stock creado exitósamente"),
    })
    public Response agregar(@Valid MovimientoStockConDetalleDTO movimientoStockConDetalleDTO) {
        return movimientoStockService.agregar(movimientoStockConDetalleDTO);
    }

    @GET
    @Path("/tipoMovimientos")
    @Operation(summary = "Devuelve los tipo de movimimientos de stock")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Lista de movimientos de stock"),
    })
    public Response getTipoMovimientos() {
        return movimientoStockService.getTipoMovimientos();
    }

    @GET
    @Path("/tipoMovimientos/{id}/motivos")
    @Operation(summary = "Devuelve los motivos a partir de un tipo de movimiento")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Lista de motivos"),
    })
    public Response getMotivos(@PathParam("id") Long tipoMovimiento) {
        return movimientoStockService.getMotivos(tipoMovimiento);
    }
}