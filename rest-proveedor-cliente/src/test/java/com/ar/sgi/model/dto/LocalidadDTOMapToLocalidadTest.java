package com.ar.sgi.model.dto;

import com.ar.sgi.model.Contacto;
import com.ar.sgi.model.Localidad;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LocalidadDTOMapToLocalidadTest {

    @Test
    public void testMapLocalidadDTOtoLocalidad() {
        LocalidadDTO localidadDTO = new LocalidadDTO();
        localidadDTO.setId(1L);
        localidadDTO.setDescripcion("descripcion");
        localidadDTO.setProvinciaId(1L);

        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        Localidad localidad = mapper.map(localidadDTO, Localidad.class);

        Assertions.assertEquals(localidadDTO.getId(), localidad.getId() );
        Assertions.assertEquals(localidadDTO.getDescripcion(), localidad.getDescripcion());
        Assertions.assertEquals(localidadDTO.getProvinciaId(), localidad.getProvincia().getId());
    }
}
