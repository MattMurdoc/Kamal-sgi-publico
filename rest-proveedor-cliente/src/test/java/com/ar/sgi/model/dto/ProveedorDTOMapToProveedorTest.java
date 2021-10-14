package com.ar.sgi.model.dto;

import com.ar.sgi.model.Contacto;
import com.ar.sgi.model.Localidad;
import com.ar.sgi.model.Proveedor;
import com.ar.sgi.model.Provincia;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDTOMapToProveedorTest {

    @Test
    public void testMapProveedorDTOtoProveedor() {

        ContactoDTO contactoDTO = new ContactoDTO();
        contactoDTO.setId(1L);
        contactoDTO.setNombre("NombreProveedor");
        contactoDTO.setCelular("351522222");
        contactoDTO.setEmail("Mail@mail.com");
        contactoDTO.setTelefono("47777777");
        contactoDTO.setProveedor(1L);
        List<ContactoDTO> contactoList = new ArrayList<>();
        contactoList.add(contactoDTO);

        ProveedorDTO proveedorDTO = new ProveedorDTO();
        proveedorDTO.setId(1L);
        proveedorDTO.setCelular("123456789");
        proveedorDTO.setCodigo("11111");
        proveedorDTO.setContactos(contactoList);
        proveedorDTO.setCUIT("20-2222222-8");
        proveedorDTO.setDomicilio("Domicilio");
        proveedorDTO.setEmail("email@email.com");
        proveedorDTO.setLocalidad(1L);
        proveedorDTO.setNombreFantasia("NombreFantasia");
        proveedorDTO.setPagina("paginaweb.com.ar");
        proveedorDTO.setRazonSocial("RazonSocual");
        proveedorDTO.setRubro(1L);
        proveedorDTO.setTelefono("4700000");

        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        Proveedor proveedor = mapper.map(proveedorDTO, Proveedor.class);

        Assertions.assertEquals(proveedorDTO.getId(), proveedor.getId() );
        Assertions.assertEquals(proveedorDTO.getCelular(), proveedor.getCelular());
        Assertions.assertEquals(proveedorDTO.getCodigo(), proveedor.getCodigo());
        Contacto contacto = proveedor.getContactos().stream().filter(it -> it.getId().equals(1L)).findFirst().get();
        Assertions.assertEquals(proveedorDTO.getContactos().get(0).getId(), contacto.getId());
        Assertions.assertEquals(proveedorDTO.getCUIT(), proveedor.getCuit());
        Assertions.assertEquals(proveedorDTO.getDomicilio(), proveedor.getDomicilio());
        Assertions.assertEquals(proveedorDTO.getEmail(), proveedor.getEmail());
        Assertions.assertEquals(proveedorDTO.getLocalidad(), proveedor.getLocalidad().getId());
        Assertions.assertEquals(proveedorDTO.getNombreFantasia(), proveedor.getNombreFantasia());
        Assertions.assertEquals(proveedorDTO.getPagina(), proveedor.getPagina());
        Assertions.assertEquals(proveedorDTO.getRazonSocial(), proveedor.getRazonSocial());
        Assertions.assertEquals(proveedorDTO.getRubro(), proveedor.getRubro().getId());
        Assertions.assertEquals(proveedorDTO.getTelefono(), proveedor.getTelefono());
    }

}
