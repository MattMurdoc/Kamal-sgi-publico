package com.ar.sgi.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import com.ar.sgi.exceptions.SolicitarCodigoInternoException;
import com.ar.sgi.exceptions.ValidationException;
import com.ar.sgi.model.*;
import com.ar.sgi.model.dto.*;
import com.ar.sgi.model.mappers.ListMapper;
import com.ar.sgi.repository.*;
import com.ar.sgi.restclient.ProveedorService;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Clase encargada de la gesti\u00F3n de los productos
 *
 * @author Alejandro Toigo
 * @version 1.0
 * @see com.ar.sgi.model.ProductoLista
 * @since Octubre 2020
 */
@ApplicationScoped
public class ProductoService {

    private static final Logger LOGGER = Logger.getLogger(ProductoService.class.getName());

    @Inject
    ProductoRepository productoRepository;
    @Inject
    @RestClient
    ProveedorService proveedorService;
    @Inject
    ProductoStockRepository productoStockRepository;
    @Inject
    DepartamentoRepository departamentoRepository;
    @Inject
    SeccionRepository seccionRepository;
    @Inject
    FamiliaRepository familiaRepository;
    @Inject
    SubFamiliaRepository subFamiliaRepository;
    @Inject
    ProductoListaPrecioRepository productoListaPrecioRepository;
    @Inject
    ProductoListaRepository productoListaRepository;
    @Inject
    UnidadRepository unidadRepository;
    @Inject
    MarcaRepository marcaRepository;
    @Inject
    ProductoService productoService;

    /**
     * +
     * Metodo que se encarga de crear un producto
     *
     * @param producto
     * @see {@link com.ar.sgi.model.Producto}
     */
    @Transactional
    public void agregar(Producto producto) throws PersistanceLayerException, ServiceLayerException, ValidationException {
        try {
            producto.setId(null);
            producto.getProductoStock().setId(null);
            producto.setEstado(true);
            sacarIdsInnecesarios(producto);

            Producto producto2 = productoRepository.find("codigo", producto.getCodigo()).firstResult();
            if (producto2 != null && StringUtils.isNotBlank(producto.getCodigoInterno())) {
                producto2 = productoRepository.find("codigo = ?1 AND codigoInterno = ?2", producto.getCodigo(), producto.getCodigoInterno()).firstResult();
                if (producto2 != null) {
                    throw new ValidationException(ValidationException.VALIDAR_CODIGO_BARRAS_CODIGO_INTERNO);
                }
            } else if (producto2 != null) {
                throw new ValidationException(ValidationException.VALIDAR_CODIGO_BARRAS);
            }

            if (producto.getProductoStock().getControlaStock()) {
                validarProductoStock(producto.getProductoStock());
            } else {
                producto.getProductoStock().setStock(null);
                producto.getProductoStock().setStockMin(null);
                producto.getProductoStock().setStockMax(null);
                producto.getProductoStock().setPuntoPedido(null);
                producto.getProductoStock().setPermiteCargar(null);
                producto.getProductoStock().setPermiteDescargar(null);
                producto.getProductoStock().setEquivalenciaCarga(null);
                producto.getProductoStock().setEquivalenciaDescarga(null);
            }
            validarSurtido(producto);
            validarComunicacion(producto);
            validarListas(producto);
            validarUnidadMarca(producto);

            productoStockRepository.persist(producto.getProductoStock());
            productoRepository.crear(producto);
            for (ProductoListaPrecio productoListaPrecio : producto.getProductoListaPrecios()) {
                productoListaPrecio.setProducto(producto);
            }
            productoListaPrecioRepository.persist(producto.getProductoListaPrecios());
        } catch (PersistanceLayerException | ServiceLayerException | ValidationException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.CREAR_PRODUCTO_EXCEPTION);
        }
    }

    private void sacarIdsInnecesarios(Producto producto) {
        if (producto.getDepartamento() != null && producto.getDepartamento().getId() != null && producto.getDepartamento().getId() <= 0) {
            producto.setDepartamento(null);
        }
        if (producto.getSeccion() != null && producto.getSeccion().getId() != null && producto.getSeccion().getId() <= 0) {
            producto.setSeccion(null);
        }
        if (producto.getFamilia() != null && producto.getFamilia().getId() != null && producto.getFamilia().getId() <= 0) {
            producto.setFamilia(null);
        }
        if (producto.getSubfamilia() != null && producto.getSubfamilia().getId() != null && producto.getSubfamilia().getId() <= 0) {
            producto.setSubfamilia(null);
        }
        if (producto.getProveedorId() != null && producto.getProveedorId() <= 0) {
            producto.setProveedorId(null);
        }
        if (producto.getMarca() != null && producto.getMarca().getId() != null && producto.getMarca().getId() <= 0) {
            producto.setMarca(null);
        }
        if (producto.getUnidad() != null && producto.getUnidad().getId() != null && producto.getUnidad().getId() <= 0) {
            producto.setUnidad(null);
        }
    }

    public Boolean validarProductoStock(ProductoStock productoStock) throws ValidationException {

        if (!(productoStock.getStockMin() != null && productoStock.getStockMin() >= 0)) {
            throw new ValidationException(ValidationException.VALIDAR_STOCK_MIN);
        }
        if (!(productoStock.getStockMax() != null && productoStock.getStockMax() >= 0)) {
            throw new ValidationException(ValidationException.VALIDAR_STOCK_MAX);
        }
        if (!(productoStock.getPuntoPedido() != null && productoStock.getPuntoPedido() >= 0)) {
            throw new ValidationException(ValidationException.VALIDAR_PUNTO_PEDIDO);
        }
        if (!(productoStock.getEquivalenciaCarga() != null && productoStock.getEquivalenciaCarga().longValue() >= 0)) {
            throw new ValidationException(ValidationException.VALIDAR_EQUIVALENCIA_CARGA);
        }
        if (!(productoStock.getEquivalenciaDescarga() != null && productoStock.getEquivalenciaCarga().longValue() >= 0)) {
            throw new ValidationException(ValidationException.VALIDAR_EQUIVALENCIA_DESCARGA);
        }
        return true;
    }

    public Boolean validarSurtido(Producto producto) throws ValidationException {

        if (producto.getDepartamento() != null && producto.getDepartamento().getId() != null
                && departamentoRepository.findById(producto.getDepartamento().getId()) == null) {
            throw new ValidationException(ValidationException.VALIDAR_DEPARTAMENTO);
        }

        if (producto.getDepartamento() != null && producto.getSeccion() != null && producto.getSeccion().getId() != null
                && seccionRepository.findById(producto.getSeccion().getId()) == null) {
            throw new ValidationException(ValidationException.VALIDAR_SECCION);
        }

        if (producto.getSeccion() != null && producto.getFamilia() != null && producto.getFamilia().getId() != null
                && familiaRepository.findById(producto.getFamilia().getId()) == null) {
            throw new ValidationException(ValidationException.VALIDAR_FAMILIA);
        }

        if (producto.getFamilia() != null && producto.getSubfamilia() != null && producto.getSubfamilia().getId() != null
                && subFamiliaRepository.findById(producto.getSubfamilia().getId()) == null) {
            throw new ValidationException(ValidationException.VALIDAR_SUBFAMILIA);
        }

        return true;
    }

    public Boolean validarComunicacion(Producto producto) throws ServiceLayerException, ValidationException {
        if (producto.getProveedorId() != null && producto.getProveedorId() > 0) {
            Response response = proveedorService.getById(producto.getProveedorId());
            if (response != null) {
                if (response.getStatus() == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
                    throw new ServiceLayerException(ServiceLayerException.ERROR_COMUNICACION_PROVEEDORES);
                }
                if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
                    throw new ValidationException(ValidationException.VALIDAR_PROVEEDOR);
                }
                if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                    Proveedor proveedor = response.readEntity(Proveedor.class);
                    producto.setNombreFantasia(proveedor.getNombreFantasia());
                }
            } else {
                throw new ServiceLayerException(ServiceLayerException.ERROR_COMUNICACION_PROVEEDORES);
            }
        }
        return true;
    }

    public Boolean validarListas(Producto producto) throws ValidationException {

        for (ProductoListaPrecio productoListaPrecio : producto.getProductoListaPrecios()) {
            if (productoListaPrecio.getListaPrecio().getId() != null) {
                ProductoLista productoLista = productoListaRepository.findById(productoListaPrecio.getListaPrecio().getId());
                if (!productoLista.getEstado()) {
                    throw new ValidationException(ValidationException.LISTA_INACTIVA);
                }
            }
            if (productoListaPrecio.getCosto() != null) {
                if (productoListaPrecio.getCosto().compareTo(BigDecimal.ZERO) == -1) {
                    throw new ValidationException(ValidationException.COSTO_NEGATIVO);
                }
            } else {
                throw new ValidationException(ValidationException.COSTO_VACIO);
            }
            if (productoListaPrecio.getPrecioCompra() != null) {
                if (productoListaPrecio.getPrecioCompra().compareTo(BigDecimal.ZERO) == -1) {
                    throw new ValidationException(ValidationException.PRECIO_COMPRA_NEGATIVO);
                }
            } else {
                throw new ValidationException(ValidationException.PRECIO_COMPRA_VACIO);
            }
            if (productoListaPrecio.getPrecioVenta() != null) {
                if (productoListaPrecio.getPrecioVenta().compareTo(BigDecimal.ZERO) == -1) {
                    throw new ValidationException(ValidationException.PRECIO_VENTA_NEGATIVO);
                }
            } else {
                throw new ValidationException(ValidationException.PRECIO_VENTA_VACIO);
            }
        }
        return true;
    }

    public boolean validarUnidadMarca(Producto producto) throws ValidationException {

        if (producto.getUnidad() != null && producto.getUnidad().getId() != null
                && unidadRepository.findById(producto.getUnidad().getId()) == null) {
            throw new ValidationException(ValidationException.VALIDAR_UNIDAD);
        }
        if (producto.getMarca() != null && producto.getMarca().getId() != null
                && marcaRepository.findById(producto.getMarca().getId()) == null) {
            throw new ValidationException(ValidationException.VALIDAR_MARCA);
        }
        return true;
    }

    @Transactional
    public void actualizar(Producto producto) throws ServiceLayerException, ValidationException {
        sacarIdsInnecesarios(producto);

        Producto producto2 = productoRepository.find("codigo = ?1 and id <> ?2 ", producto.getCodigo(), producto.getId()).firstResult();
        if (producto2 != null && StringUtils.isNotBlank(producto.getCodigoInterno())) {
            producto2 = productoRepository.find("codigo = ?1 AND codigoInterno = ?2 AND id <> ?3", producto.getCodigo(), producto.getCodigoInterno(), producto.getId()).firstResult();
            if (producto2 != null) {
                throw new ValidationException(ValidationException.VALIDAR_CODIGO_BARRAS_CODIGO_INTERNO);
            }
        } else if (producto2 != null) {
            throw new ValidationException(ValidationException.VALIDAR_CODIGO_BARRAS);
        }

        if (producto.getProductoStock().getControlaStock()) {
            validarProductoStock(producto.getProductoStock());
        } else {
            producto.getProductoStock().setStock(null);
            producto.getProductoStock().setStockMin(null);
            producto.getProductoStock().setStockMax(null);
            producto.getProductoStock().setPuntoPedido(null);
            producto.getProductoStock().setPermiteCargar(null);
            producto.getProductoStock().setPermiteDescargar(null);
            producto.getProductoStock().setEquivalenciaCarga(null);
            producto.getProductoStock().setEquivalenciaDescarga(null);
        }
        validarSurtido(producto);
        validarComunicacion(producto);
        validarListas(producto);
        validarUnidadMarca(producto);
        try {
            productoStockRepository.getEntityManager().merge(producto.getProductoStock());
            for (ProductoListaPrecio productoListaPrecio : producto.getProductoListaPrecios()) {
                productoListaPrecio.setProducto(producto);
                productoListaPrecioRepository.getEntityManager().merge(productoListaPrecio);
            }
            productoRepository.getEntityManager().merge(producto);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_PRODUCTO_EXCEPTION);
        }
    }

    public Paginator<Producto> listar(Integer pagina, Integer items, ProductoFiltroDTO productoFiltroDTO) throws ServiceLayerException {
        Paginator<Producto> paginator = null;
        try {
            paginator = productoRepository.listar(pagina, items, productoFiltroDTO);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_MARCAS_EXCEPTION);
        }
        return paginator;
    }

    @Transactional
    public void modificarPrecios(ActualizarPrecioDTO actualizarPrecioDTO) throws ServiceLayerException {
        try {
            for (Long id : actualizarPrecioDTO.getIds()) {
                Producto producto = productoRepository.findById(id);
                if (producto == null) throw new ServiceLayerException("No se encontro el producto");
                for (ProductoListaPrecio lista : ListMapper.mapList(actualizarPrecioDTO.getProductoListaPrecios(), ProductoListaPrecio.class)) {
                    lista.setProducto(producto);
                    productoRepository.modificarPrecios(lista);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_PRECIO);
        }
    }

    public List<ProductoListaPrecio> traerListasPrecio(List<Long> ids) throws ServiceLayerException {
        List<ProductoListaPrecio> listaReturn = new ArrayList<>();
        List<ProductoListaPrecio> productoListaPrecios = new ArrayList<>();
        try {
            //Buscamos todas las listas de precio
            for (Long id : ids) {
                Producto producto = productoRepository.findById(id);
                productoListaPrecios.addAll(this.productoListaPrecioRepository.find("producto", producto).list());
            }
            //Filtramos por todas las listas
            List<ProductoLista> listas = productoListaPrecios.stream()
                    .map(ProductoListaPrecio::getListaPrecio)
                    .distinct()
                    .collect(Collectors.toList());

            for (ProductoLista lista : listas) {
                List<ProductoListaPrecio> productoListas = productoListaPrecios.stream()
                        .filter(it -> it.getListaPrecio().getId().equals(lista.getId()))
                        .collect(Collectors.toList());
                if (!productoListas.isEmpty()) {
                    final BigDecimal costo = productoListas.get(0).getCosto();
                    final BigDecimal precioCompra = productoListas.get(0).getPrecioCompra();
                    final BigDecimal precioVenta = productoListas.get(0).getPrecioVenta();

                    boolean sonIguales = productoListas.stream().allMatch(it ->
                            costo.compareTo(it.getCosto()) == 0
                                    && precioCompra.compareTo(it.getPrecioCompra()) == 0
                                    && precioVenta.compareTo(it.getPrecioVenta()) == 0
                    );

                    if (sonIguales) {
                        listaReturn.add(productoListas.get(0));
                    } else {
                        listaReturn = new ArrayList<>();
                        for (ProductoLista l : listas) {
                            ProductoListaPrecio plp = new ProductoListaPrecio();
                            plp.setListaPrecio(l);
                            listaReturn.add(plp);
                        }
                        break;
                    }
                }

            }
            return listaReturn;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.BUSCAR_LISTA_EXCEPTION);
        }
    }

    /**
     * Metodo que cambia el estado de una FAMILIA
     *
     * @param id
     * @throws PersistanceLayerException
     * @throws ServiceLayerException
     * @version 1.0
     * @since Octubre 2020
     */
    @Transactional
    public void cambiarEstado(Long id) throws PersistanceLayerException, ServiceLayerException {
        try {
            productoRepository.cambiarEstado(id);

        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_FAMILIA_EXCEPTION);
        }
    }

    public Producto getById(Long id) throws ServiceLayerException {
        try {
            return productoRepository.findById(id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.GET_BY_CODIGO_PRODUCTO);
        }
    }

    public Producto getByCodigo(String codigo) throws ServiceLayerException, SolicitarCodigoInternoException {
        try {
            Producto producto = null;
            List<Producto> productos = productoRepository.find("codigo", codigo).list();
            if (productos != null && !productos.isEmpty()) {
                if (productos.size() > 1) {
                    throw new SolicitarCodigoInternoException();
                } else {
                    producto = productos.get(0);
                }
            }
            return producto;
        } catch (SolicitarCodigoInternoException sci) {
            throw sci;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.GET_BY_CODIGO_PRODUCTO);
        }
    }

    public Producto getByCodigoAndCodigoInterno(String codigo, String codigoInterno) throws ServiceLayerException {
        try {
            if(codigo.startsWith("2000020")) {
                codigo = "2000020";
            }
            return productoRepository.find("codigo = ?1 AND codigoInterno = ?2", codigo, codigoInterno).firstResult();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_FAMILIA_EXCEPTION);
        }
    }

    @Transactional
    public void procesarArchivo() throws IOException, PersistanceLayerException, ValidationException, ServiceLayerException {
        BufferedReader csvReader = new BufferedReader(new FileReader("C:\\produc10.csv"));
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setCodigo(data[0].trim());
            productoDTO.setDescripcion(data[1]);
            List<ProductoListaPrecioDTO> productoListaPrecioDTOS = new ArrayList<>();
            ProductoListaPrecioDTO precio = new ProductoListaPrecioDTO();
            precio.setListaPrecioId(1L);
            precio.setCosto(StringUtils.isNotBlank(data[2]) ? new BigDecimal(data[2]) : BigDecimal.ZERO);
            precio.setPrecioCompra(StringUtils.isNotBlank(data[2]) ? new BigDecimal(data[2]) : BigDecimal.ZERO);
            precio.setPrecioVenta(StringUtils.isNotBlank(data[3]) ? new BigDecimal(data[3]) : BigDecimal.ZERO);
            productoListaPrecioDTOS.add(precio);
            productoDTO.setProductoListaPrecios(productoListaPrecioDTOS);
            if (data.length == 5) {
                productoDTO.setImpuestoIva(new BigDecimal(data[4]).compareTo(BigDecimal.ZERO) == 0 ? new BigDecimal(21) : new BigDecimal(data[4]));
            } else if (data.length == 4) {
                productoDTO.setImpuestoIva(new BigDecimal(21));
            }
            productoDTO.setControlaStock(true);
            productoDTO.setEquivalenciaCarga(BigDecimal.ONE);
            productoDTO.setEquivalenciaDescarga(BigDecimal.ONE);
            productoDTO.setPermiteCargar(true);
            productoDTO.setPermiteDescargar(true);
            productoDTO.setStock(0L);
            productoDTO.setStockMin(0L);
            productoDTO.setStockMax(999L);
            productoDTO.setPuntoPedido(0L);
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            Producto producto = mapper.map(productoDTO, Producto.class);
            producto.setDepartamento(null);
            producto.setSeccion(null);
            producto.setFamilia(null);
            producto.setSubfamilia(null);
            producto.setMarca(null);
            producto.setUnidad(null);
            this.agregar(producto);
        }
        csvReader.close();
    }

    @Transactional
    public void procesarArchivo2() throws IOException, PersistanceLayerException, ValidationException, ServiceLayerException {
        BufferedReader csvReader = new BufferedReader(new FileReader("C:\\lista_precio_2.csv"));
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(";");
            Producto producto = productoRepository.find("codigo", data[0].trim()).firstResult();
            if (producto != null) {
                for (ProductoListaPrecio precio : producto.getProductoListaPrecios()) {
                    if (StringUtils.isNotBlank(data[2])) {
                        precio.setPrecioVenta(new BigDecimal(data[2].trim()).divide(new BigDecimal(1).add(producto.getImpuestoIva().divide(new BigDecimal(100), MathContext.DECIMAL128)), MathContext.DECIMAL128).setScale(2, RoundingMode.FLOOR));
                        productoListaPrecioRepository.getEntityManager().merge(precio);
                    }
                }
            }
        }
        csvReader.close();
    }

}
