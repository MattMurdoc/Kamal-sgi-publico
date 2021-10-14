package com.ar.sgi.api.productos;

import com.ar.sgi.models.productos.VentaDTO;
import com.ar.sgi.restclient.productos.VentaService;
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

@Path("/v1/producto/venta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Venta Api Resource", description = "Gesti\u00F3n de todo lo relacionado a ventas")
public class VentaApiResource {

    @Inject
    @RestClient
    VentaService ventaService;

    @POST
    @Operation(summary = "Persistir una venta")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Venta persisitida éxitosamente"),
    })
    public Response agregar(@Valid VentaDTO ventaDTO) {
        return ventaService.agregar(ventaDTO);
    }

}

