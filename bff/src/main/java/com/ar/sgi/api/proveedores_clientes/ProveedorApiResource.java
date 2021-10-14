package com.ar.sgi.api.proveedores_clientes;

import com.ar.sgi.models.proveedores_clientes.ProveedorDTO;
import com.ar.sgi.models.proveedores_clientes.ProveedorFiltroDTO;
import com.ar.sgi.restclient.proveedores_clientes.ProveedorService;
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

@Path("/v1/proveedorcliente/proveedor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Proveedor Api Resource", description = "Gesti\u00F3n de todo lo relacionado a los proveedores")
public class ProveedorApiResource {

    @Inject
    @RestClient
    ProveedorService proveedorService;

    @GET
    @Path("/{pagina}/{item}")
    @Operation(summary = "Listar departamentos de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Departamentos paginados"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarProveedores(@PathParam("pagina") int pagina, @PathParam("item") int item) {
        return proveedorService.listarProveedores(pagina, item);
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Devuelve un proveedor mediante un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Proveedor"),
            @APIResponse(responseCode = "404", description = "No se encontro un proveedor con ese identificador")
    })
    public Response getById(@PathParam("id") Long id) {
        return proveedorService.getById(id);
    }

    @POST
    public Response agregar(@Valid ProveedorDTO proveedorDTO) {
        return proveedorService.agregar(proveedorDTO);
    }

    @PUT
    @Operation(summary = "Persistir un proveedor")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Departamento persisitido éxitosamente"),
    })
    public Response actualizar(@Valid ProveedorDTO proveedorDTO) {
        return proveedorService.actualizar(proveedorDTO);
    }

    @POST
    @Path("/{pagina}/{items}")
    @Operation(summary = "Buscar todos los clientes")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Clientes"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response buscar(@PathParam("pagina") int pagina, @PathParam("items") int items, ProveedorFiltroDTO proveedorFiltroDTO) {
        return proveedorService.buscar(pagina, items, proveedorFiltroDTO);
    }
}