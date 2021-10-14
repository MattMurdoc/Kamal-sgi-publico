package com.ar.sgi.model.dto;

import com.ar.sgi.model.Marca;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MarcaDTOMapToMarcaTest {

    @Test
    public void testMapMarcaDTOtoMarca() {
        MarcaDTO marcaDTO = new MarcaDTO();
        marcaDTO.setId(1L);
        marcaDTO.setDescripcion("Test 1");
        marcaDTO.setEstado(true);

        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        Marca marca = mapper.map(marcaDTO, Marca.class);

        Assertions.assertEquals(marcaDTO.getId(), marca.getId());
        Assertions.assertEquals(marcaDTO.getDescripcion(), marca.getDescripcion());
        Assertions.assertEquals(marcaDTO.getEstado(), marca.getEstado());
    }

}
