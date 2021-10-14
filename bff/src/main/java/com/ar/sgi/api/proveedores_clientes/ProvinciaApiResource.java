package com.ar.sgi.api.proveedores_clientes;

import com.ar.sgi.models.proveedores_clientes.ProvinciaDTO;
import com.ar.sgi.restclient.proveedores_clientes.ProvinciaService;
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

@Path("/v1/proveedorcliente/provincia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Provincia Api Resource", description = "Gesti\u00F3n de todo lo relacionado a las provincias")
public class ProvinciaApiResource {

    @Inject
    @RestClient
    ProvinciaService provinciaService;

    @GET
    @Path("/{pagina}/{item}")
    @Operation(summary = "Listar provincias de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Provincias paginados"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarProvinciasPaginated(@PathParam("pagina") int pagina, @PathParam("item") int item) {
        return provinciaService.listarProvinciasPaginated(pagina, item);
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Devuelve una provincia mediante un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Provincia"),
            @APIResponse(responseCode = "404", description = "No se encontro una provincia con ese identificador")
    })
    public Response getById(@PathParam("id") Long id) {
        return provinciaService.getById(id);
    }

    @POST
    @Operation(summary = "Persistir un departamento")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Departamento persisitido éxitosamente"),
    })
    public Response agregar(@Valid ProvinciaDTO provinciaDTO) {
        return provinciaService.agregar(provinciaDTO);
    }

    @PUT
    @Operation(summary = "Actualizar un departamento")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Departamento actualizado éxitosamente"),
            @APIResponse(responseCode = "400", description = "Identificador de departamento no válido")
    })
    public Response actualizar(@Valid ProvinciaDTO provinciaDTO) {
        return provinciaService.actualizar(provinciaDTO);
    }

    @GET
    @Path("/all")
    @Operation(summary = "Listar todos las provincias")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Provincias"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarProvincias() {
        return provinciaService.listarProvincias();
    }
}