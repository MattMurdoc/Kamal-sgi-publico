package com.ar.sgi.api.proveedores_clientes;

import com.ar.sgi.models.proveedores_clientes.RubroDTO;
import com.ar.sgi.restclient.proveedores_clientes.RubroService;
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

@Path("/v1/proveedorcliente/rubro")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Rubro Api Resource", description = "Gesti\u00F3n de todo lo relacionado a los rubros")
public class RubroApiResource {

    @Inject
    @RestClient
    RubroService rubroService;

    @GET
    @Path("/{pagina}/{item}")
    @Operation(summary = "Listar rubros de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Rubro paginados"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarRubros(@PathParam("pagina") int pagina, @PathParam("item") int item) {
        return rubroService.listarRubros(pagina, item);
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Devuelve un rubro mediante un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Rubro"),
            @APIResponse(responseCode = "404", description = "No se encontro un rubro con ese identificador")
    })
    public Response getById(@PathParam("id") Long id) {
        return rubroService.getById(id);
    }

    @POST
    @Operation(summary = "Persistir un rubro")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Rubro persisitido éxitosamente"),
    })
    public Response agregar(@Valid RubroDTO rubroDTO) {
        return rubroService.agregar(rubroDTO);
    }

    @PUT
    @Operation(summary = "Actualizar un departamento")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Departamento actualizado éxitosamente"),
            @APIResponse(responseCode = "400", description = "Identificador de departamento no válido")
    })
    public Response actualizar(@Valid RubroDTO rubroDTO) {
        return rubroService.actualizar(rubroDTO);
    }

    @GET
    @Path("/all")
    @Operation(summary = "Listar todos los rubros")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Rubros"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarRubro() {
        return rubroService.listarRubro();
    }
}