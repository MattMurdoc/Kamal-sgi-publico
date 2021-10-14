package com.ar.sgi.model.dto;

import com.ar.sgi.model.Cliente;
import com.ar.sgi.model.Contacto;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactoDTOMapToContactoTest {

    @Test
    public void testMapContactoDTOtoContacto() {
        ContactoDTO contactoDTO = new ContactoDTO();
        contactoDTO.setId(1L);
        contactoDTO.setNombre("NombreProveedor");
        contactoDTO.setCelular("351522222");
        contactoDTO.setEmail("Mail@mail.com");
        contactoDTO.setTelefono("47777777");
        contactoDTO.setProveedor(1L);

        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        Contacto contacto = mapper.map(contactoDTO, Contacto.class);

        Assertions.assertEquals(contactoDTO.getId(), contacto.getId() );
        Assertions.assertEquals(contactoDTO.getNombre(), contacto.getNombre());
        Assertions.assertEquals(contactoDTO.getCelular(), contacto.getCelular());
        Assertions.assertEquals(contactoDTO.getEmail(), contacto.getEmail());
        Assertions.assertEquals(contactoDTO.getTelefono(), contacto.getTelefono());
    }

}
