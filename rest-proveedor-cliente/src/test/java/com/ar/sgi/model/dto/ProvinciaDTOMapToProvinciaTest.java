package com.ar.sgi.model.dto;

import com.ar.sgi.model.Localidad;
import com.ar.sgi.model.Provincia;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProvinciaDTOMapToProvinciaTest {

    @Test
    public void testMapProvinciaDTOtoProvincia() {
        ProvinciaDTO provinciaDTO = new ProvinciaDTO();
        provinciaDTO.setId(1L);
        provinciaDTO.setDescripcion("descripcion");


        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        Provincia provincia = mapper.map(provinciaDTO, Provincia.class);

        Assertions.assertEquals(provinciaDTO.getId(), provincia.getId() );
        Assertions.assertEquals(provinciaDTO.getDescripcion(), provincia.getDescripcion());
    }

}
