package com.ar.sgi.api;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ar.sgi.model.Respuesta;
import com.ar.sgi.model.dto.ProductoListaDTO;
import com.ar.sgi.model.mappers.ListMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.ar.sgi.exceptions.ApiResourceException;
import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.ProductoLista;
import com.ar.sgi.services.ProductoListaService;

@Path("/lista")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Lista de Producto Resource", description = "Gesti\u00F3n de todo lo relacionado a las listas de productos")
public class ProductoListaApiResource {
	
	private static final Logger LOGGER = Logger.getLogger(ProductoListaApiResource.class.getName());
	
	@Inject
	ProductoListaService productoListaService;

    @GET
	@Operation(summary = "Listar todas las listas")
	@APIResponses({
			@APIResponse(responseCode = "200", description = "Listas"),
			@APIResponse(responseCode = "204", description = "No hay resultados")
	})
	public Response list() {
		try {
			List<ProductoLista> listas = productoListaService.listar();

			if (listas != null && !listas.isEmpty()) {
				return Response.ok().entity(ListMapper.mapList(listas, ProductoListaDTO.class)).build();
			} else {
				return Response.noContent().build();
			}

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

    @POST
	@Operation(summary = "Persistir una lista de precio")
	@APIResponses({
			@APIResponse(responseCode = "202", description = "Lista de precio persisitida éxitosamente"),
	})
	public Response agregar(@Valid ProductoListaDTO productoListaDTO) {
    	try {
			Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
			productoListaService.agregar(mapper.map(productoListaDTO, ProductoLista.class));
			return Response.accepted().build();
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

    @PUT
	@Operation(summary = "Actualizar una lista de precio")
	@APIResponses({
			@APIResponse(responseCode = "202", description = "Lista de precio actualizada éxitosamente"),
			@APIResponse(responseCode = "400", description = "Identificador de lista de precio no válido")
	})
	public Response actualizar(@Valid ProductoListaDTO productoListaDTO) {
		try {
			if (productoListaDTO.getId() == null || productoListaDTO.getId() <= 0) {
				return Response.status(Response.Status.BAD_REQUEST).entity(new Respuesta("Identificador no válido")).build();
			}
			Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
			productoListaService.actualizar(mapper.map(productoListaDTO, ProductoLista.class));
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
}
