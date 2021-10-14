package com.ar.sgi.model.dto;

import com.ar.sgi.model.Departamento;
import com.ar.sgi.model.Producto;
import com.ar.sgi.model.ProductoListaPrecio;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductoDTOMapToProductoTest {

    @Test
    public void testMapProductoDTOToProducto() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(1L);
        productoDTO.setCodigo("Codigo123");
        productoDTO.setCodigoInterno("123123");
        productoDTO.setDescripcion("Producto Test 1");
        productoDTO.setProveedorId(1L);
        productoDTO.setNombreFantasia("Proveedor 123");
        productoDTO.setDepartamentoId(1L);
        productoDTO.setSeccionId(1L);
        productoDTO.setFamiliaId(1L);
        productoDTO.setSubfamiliaId(1L);
        productoDTO.setMarcaId(1L);
        productoDTO.setUnidadId(1L);
        productoDTO.setGramaje(1);
        productoDTO.setEstado(true);
        productoDTO.setProductoStockId(1L);
        productoDTO.setControlaStock(true);
        productoDTO.setStock(123L);
        productoDTO.setStockMin(123L);
        productoDTO.setStockMax(123L);
        productoDTO.setPuntoPedido(123L);
        productoDTO.setPermiteCargar(true);
        productoDTO.setPermiteDescargar(true);
        productoDTO.setEquivalenciaCarga(BigDecimal.ONE);
        productoDTO.setEquivalenciaDescarga(BigDecimal.ONE);
        productoDTO.setImpuestoIva(new BigDecimal(21));

        ProductoListaPrecioDTO productoListaPrecioDTO = new ProductoListaPrecioDTO();
        productoListaPrecioDTO.setListaPrecioId(1L);
        productoListaPrecioDTO.setCosto(BigDecimal.ONE);
        productoListaPrecioDTO.setPrecioCompra(BigDecimal.ONE);
        productoListaPrecioDTO.setPrecioVenta(BigDecimal.ONE);
        ProductoListaPrecioDTO productoListaPrecioDTO2 = new ProductoListaPrecioDTO();
        productoListaPrecioDTO2.setListaPrecioId(2L);
        productoListaPrecioDTO2.setCosto(BigDecimal.ONE);
        productoListaPrecioDTO2.setPrecioCompra(BigDecimal.ONE);
        productoListaPrecioDTO2.setPrecioVenta(BigDecimal.ONE);
        List<ProductoListaPrecioDTO> listas = new ArrayList<>();
        listas.add(productoListaPrecioDTO);
        listas.add(productoListaPrecioDTO2);

        productoDTO.setProductoListaPrecios(listas);

        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        Producto producto = mapper.map(productoDTO, Producto.class);

        Assertions.assertEquals(productoDTO.getId(), producto.getId());
        Assertions.assertEquals(productoDTO.getCodigo(), producto.getCodigo());
        Assertions.assertEquals(productoDTO.getCodigoInterno(), producto.getCodigoInterno());
        Assertions.assertEquals(productoDTO.getDescripcion(), producto.getDescripcion());
        Assertions.assertEquals(productoDTO.getProveedorId(), producto.getProveedorId());
        Assertions.assertEquals(productoDTO.getNombreFantasia(), producto.getNombreFantasia());
        Assertions.assertEquals(productoDTO.getDepartamentoId(), producto.getDepartamento().getId());
        Assertions.assertEquals(productoDTO.getSeccionId(), producto.getSeccion().getId());
        Assertions.assertEquals(productoDTO.getFamiliaId(), producto.getFamilia().getId());
        Assertions.assertEquals(productoDTO.getSubfamiliaId(), producto.getSubfamilia().getId());
        Assertions.assertEquals(productoDTO.getMarcaId(), producto.getMarca().getId());
        Assertions.assertEquals(productoDTO.getUnidadId(), producto.getUnidad().getId());
        Assertions.assertEquals(productoDTO.getGramaje(), producto.getGramaje());
        Assertions.assertEquals(productoDTO.getEstado(), producto.getEstado());
        Assertions.assertEquals(productoDTO.getProductoStockId(), producto.getProductoStock().getId());
        Assertions.assertEquals(productoDTO.getControlaStock(), producto.getProductoStock().getControlaStock());
        Assertions.assertEquals(productoDTO.getStock(), producto.getProductoStock().getStock());
        Assertions.assertEquals(productoDTO.getStockMin(), producto.getProductoStock().getStockMin());
        Assertions.assertEquals(productoDTO.getStockMax(), producto.getProductoStock().getStockMax());
        Assertions.assertEquals(productoDTO.getPuntoPedido(), producto.getProductoStock().getPuntoPedido());
        Assertions.assertEquals(productoDTO.getEquivalenciaCarga(), producto.getProductoStock().getEquivalenciaCarga());
        Assertions.assertEquals(productoDTO.getEquivalenciaDescarga(), producto.getProductoStock().getEquivalenciaDescarga());
        Assertions.assertEquals(productoDTO.getPermiteCargar(), producto.getProductoStock().getPermiteCargar());
        Assertions.assertEquals(productoDTO.getPermiteDescargar(), producto.getProductoStock().getPermiteDescargar());
        Assertions.assertEquals(productoDTO.getImpuestoIva(), producto.getImpuestoIva());
        ProductoListaPrecio plpid1 = producto.getProductoListaPrecios().stream().filter(it -> it.getListaPrecio().getId().equals(1L)).findFirst().get();
        Assertions.assertEquals(productoDTO.getProductoListaPrecios().get(0).getListaPrecioId(), plpid1.getListaPrecio().getId());
        Assertions.assertEquals(productoDTO.getProductoListaPrecios().get(0).getCosto(), plpid1.getCosto());
        Assertions.assertEquals(productoDTO.getProductoListaPrecios().get(0).getPrecioCompra(), plpid1.getPrecioCompra());
        Assertions.assertEquals(productoDTO.getProductoListaPrecios().get(0).getPrecioVenta(), plpid1.getPrecioVenta());
        ProductoListaPrecio plpid2 = producto.getProductoListaPrecios().stream().filter(it -> it.getListaPrecio().getId().equals(2L)).findFirst().get();
        Assertions.assertEquals(productoDTO.getProductoListaPrecios().get(1).getListaPrecioId(), plpid2.getListaPrecio().getId());
        Assertions.assertEquals(productoDTO.getProductoListaPrecios().get(1).getCosto(), plpid2.getCosto());
        Assertions.assertEquals(productoDTO.getProductoListaPrecios().get(1).getPrecioCompra(), plpid2.getPrecioCompra());
        Assertions.assertEquals(productoDTO.getProductoListaPrecios().get(1).getPrecioVenta(), plpid2.getPrecioVenta());
    }
}
