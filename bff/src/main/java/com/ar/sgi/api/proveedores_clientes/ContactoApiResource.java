package com.ar.sgi.api.proveedores_clientes;

import com.ar.sgi.models.proveedores_clientes.ContactoDTO;
import com.ar.sgi.restclient.proveedores_clientes.ContactoService;
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

@Path("/v1/proveedorcliente/contacto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Contacto Api Resource", description = "Gesti\u00F3n de todo lo relacionado a los contactos")
public class ContactoApiResource {

    @Inject
    @RestClient
    ContactoService contactoService;

    @GET
    @Path("/{pagina}/{item}")
    @Operation(summary = "Listar contactos de manera paginada")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Contactos paginados"),
            @APIResponse(responseCode = "204", description = "No hay resultados")
    })
    public Response listarContactos(@PathParam("pagina") int pagina, @PathParam("item") int item) {
        return contactoService.listarContactos(pagina, item);
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Devuelve un contacto mediante un identificador")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Contacto"),
            @APIResponse(responseCode = "404", description = "No se encontro un contacto con ese identificador")
    })
    public Response getById(@PathParam("id") Long id) {
        return contactoService.getById(id);
    }

    @GET
    @Path("/get/{proveedor}")
    @Operation(summary = "Devuelve una lista de contactos de un proveedor")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Contacto"),
            @APIResponse(responseCode = "204", description = "No hay resultados"),
            @APIResponse(responseCode = "404", description = "No se encontro el proveedor con ese identificador")
    })
    public Response listarContactosByProveedor(@PathParam("proveedor") Long proveedorId) {
        return contactoService.listarContactosByProveedor(proveedorId);
    }

    @POST
    @Operation(summary = "Persistir un contacto")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Contacto persisitido éxitosamente"),
    })
    public Response agregar(@Valid ContactoDTO contactoDTO) {
        return contactoService.agregar(contactoDTO);
    }

    @PUT
    @Operation(summary = "Actualizar un contacto")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Contacto actualizado éxitosamente"),
            @APIResponse(responseCode = "400", description = "Identificador de contacto no válido")
    })
    public Response actualizar(@Valid ContactoDTO contactoDTO) {
        return contactoService.actualizar(contactoDTO);
    }
}
