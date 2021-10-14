package com.ar.sgi.model.dto;

import com.ar.sgi.model.SubFamilia;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class SubfamiliaDTOMapToSubfamiliaTest {

    @Test
    public void testMapSubFamiliaDTOtoSubFamilia() {
        SubFamiliaDTO subFamiliaDTO = new SubFamiliaDTO();
        subFamiliaDTO.setId(1L);
        subFamiliaDTO.setDescripcion("Test 1");
        subFamiliaDTO.setEstado(true);
        subFamiliaDTO.setFamiliaId(1L);

        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        SubFamilia subFamilia = mapper.map(subFamiliaDTO, SubFamilia.class);

        Assertions.assertEquals(subFamiliaDTO.getId(), subFamilia.getId());
        Assertions.assertEquals(subFamiliaDTO.getDescripcion(), subFamilia.getDescripcion());
        Assertions.assertEquals(subFamiliaDTO.getEstado(), subFamilia.getEstado());
        Assertions.assertEquals(subFamiliaDTO.getFamiliaId(), subFamilia.getFamilia().getId());
    }

}
