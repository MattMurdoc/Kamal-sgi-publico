package com.ar.sgi.api.proveedores_clientes;

import com.ar.sgi.models.proveedores_clientes.LocalidadDTO;
import com.ar.sgi.restclient.proveedores_clientes.LocalidadService;
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

@Path("/v1/proveedorcliente/localidad")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Localidad Api Resource", description = "Gesti\u00F3n de todo lo relacionado a las localidades")
public class LocalidadApiResource {

    @Inject
    @RestClient
    LocalidadService localidadService;


    @GET
    @Path("/{pagina}/{item}")
    @Operation(summary = "Listar localidades de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Localidades paginadas"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarLocalidades(@PathParam("pagina") int pagina, @PathParam("item") int item) {
        return localidadService.listarLocalidades(pagina, item);
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Devuelve una localidad mediante un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Localidad"),
            @APIResponse(responseCode = "404", description = "No se encontro una localidad con ese identificador")
    })
    public Response getById(@PathParam("id") Long id) {
        return localidadService.getById(id);
    }

    @GET
    @Path("/get/{provincia}")
    @Operation(summary = "Listar localidades que pertenezcan a una provincia")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Localidades que pertenecen a una provincia"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarLocalidadesByProvincia(@PathParam("provincia") Long provinciaId) {
        return localidadService.listarLocalidadesByProvincia(provinciaId);
    }

    @POST
    @Operation(summary = "Persistir una localidad")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Localidad persisitida éxitosamente"),
    })
    public Response agregar(@Valid LocalidadDTO localidadDTO) {
        return localidadService.agregar(localidadDTO);
    }

    @PUT
    @Operation(summary = "Actualizar un departamento")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Localidad actualizada éxitosamente"),
            @APIResponse(responseCode = "400", description = "Identificador de localidad no válido")
    })
    public Response actualizar(@Valid LocalidadDTO localidadDTO) {
        return localidadService.actualizar(localidadDTO);
    }
}