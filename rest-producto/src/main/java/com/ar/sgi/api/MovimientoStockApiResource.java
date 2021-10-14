package com.ar.sgi.api;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.exceptions.ValidationException;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Respuesta;
import com.ar.sgi.model.dto.MovimientoStockConDetalleDTO;
import com.ar.sgi.model.dto.MovimientoStockDTO;
import com.ar.sgi.model.dto.MovimientoStockFiltroDTO;
import com.ar.sgi.services.MaestroService;
import com.ar.sgi.services.MovimientoStockService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;


@Path("/stock")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Movimientos de Stock Api Resource", description = "Gesti\u00F3n de todo lo relacionado a los movimientos de stock")

public class MovimientoStockApiResource {

    private static final Logger LOGGER = Logger.getLogger(MovimientoStockApiResource.class.getName());
    @Inject
    MovimientoStockService movimientoStockService;
    @Inject
    MaestroService maestroService;

    @POST
    @Path("/{pagina}/{item}")
    @Operation(summary = "Listar productos de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Movimientos de stock paginados"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarMovimientoStock(@PathParam("pagina") int pagina, @PathParam("item") int item, MovimientoStockFiltroDTO filtro) {
        try {
            Paginator<MovimientoStockDTO> movimientoStock = movimientoStockService.listarTodo(pagina, item, filtro);
            if (movimientoStock != null && movimientoStock.validar()) {
                return Response.ok().entity(movimientoStock).build();
            } else {
                return Response.noContent().build();
            }
        } catch (ServiceLayerException | PersistanceLayerException | ValidationException e) {
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(e.toString());
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return Response.serverError().entity(respuesta).build();
        } catch (Exception e) {
            ApiResourceException apiResourceException = new ApiResourceException(ApiResourceException.GENERIC_MESSAGE);
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(apiResourceException.toString());
            LOGGER.log(Level.SEVERE, apiResourceException.toString(), e);
            return Response.serverError().entity(respuesta).build();
        }
    }

    @POST
    @Operation(summary = "Persistir un movimiento de stock")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Movimiento de stock creado exitósamente"),
    })
    public Response agregar(@Valid MovimientoStockConDetalleDTO movimientoStockConDetalleDTO) {
        try {
            movimientoStockService.agregar(movimientoStockConDetalleDTO);
            return Response.accepted().build();
        } catch (ServiceLayerException e) {
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(e.toString());
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return Response.serverError().entity(respuesta).build();
        } catch (Exception e) {
            ApiResourceException apiResourceException = new ApiResourceException(ApiResourceException.GENERIC_MESSAGE);
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(apiResourceException.toString());
            LOGGER.log(Level.SEVERE, apiResourceException.toString(), e);
            return Response.serverError().entity(respuesta).build();
        }
    }

    @GET
    @Path("/tipoMovimientos")
    @Operation(summary = "Devuelve los tipo de movimimientos de stock")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Lista de movimientos de stock"),
    })
    public Response getTipoMovimientos() {
        try {
            return Response.ok(maestroService.getTipoMovimientos()).build();
        } catch (ServiceLayerException | PersistanceLayerException e) {
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(e.toString());
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return Response.serverError().entity(respuesta).build();
        } catch (Exception e) {
            ApiResourceException apiResourceException = new ApiResourceException(ApiResourceException.GENERIC_MESSAGE);
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(apiResourceException.toString());
            LOGGER.log(Level.SEVERE, apiResourceException.toString(), e);
            return Response.serverError().entity(respuesta).build();
        }
    }

    @GET
    @Path("/tipoMovimientos/{id}/motivos")
    @Operation(summary = "Devuelve los motivos a partir de un tipo de movimiento")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Lista de motivos"),
    })
    public Response getMotivos(@PathParam("id") Long tipoMovimiento) {
        try {
            return Response.ok(maestroService.getMotivoByTipoMovimiento(tipoMovimiento)).build();
        } catch (ServiceLayerException | PersistanceLayerException e) {
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(e.toString());
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return Response.serverError().entity(respuesta).build();
        } catch (Exception e) {
            ApiResourceException apiResourceException = new ApiResourceException(ApiResourceException.GENERIC_MESSAGE);
            Respuesta respuesta = new Respuesta();
            respuesta.setMensaje(apiResourceException.toString());
            LOGGER.log(Level.SEVERE, apiResourceException.toString(), e);
            return Response.serverError().entity(respuesta).build();
        }
    }
}
