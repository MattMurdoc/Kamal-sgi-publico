package com.ar.sgi.model.dto;

import com.ar.sgi.model.Familia;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class FamiliaDTOMapToFamiliaTest {

    @Test
    public void testMapFamiliaDTOtoFamilia() {
        FamiliaDTO familiaDTO = new FamiliaDTO();
        familiaDTO.setId(1L);
        familiaDTO.setDescripcion("Test 1");
        familiaDTO.setEstado(true);
        familiaDTO.setSeccionId(1L);

        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        Familia familia = mapper.map(familiaDTO, Familia.class);

        Assertions.assertEquals(familiaDTO.getId(), familia.getId());
        Assertions.assertEquals(familiaDTO.getDescripcion(), familia.getDescripcion());
        Assertions.assertEquals(familiaDTO.getSeccionId(), familia.getSeccion().getId());
    }

}
