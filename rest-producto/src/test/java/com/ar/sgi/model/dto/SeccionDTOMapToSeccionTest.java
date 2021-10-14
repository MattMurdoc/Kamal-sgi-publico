package com.ar.sgi.model.dto;

import com.ar.sgi.model.Seccion;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class SeccionDTOMapToSeccionTest {

    @Test
    public void testMapSeccionDTOtoSeccion() {
        SeccionDTO seccionDTO = new SeccionDTO();
        seccionDTO.setId(1L);
        seccionDTO.setDescripcion("Test 1");
        seccionDTO.setEstado(true);
        seccionDTO.setDepartamentoId(1L);

        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        Seccion seccion = mapper.map(seccionDTO, Seccion.class);

        Assertions.assertEquals(seccionDTO.getId(), seccion.getId());
        Assertions.assertEquals(seccionDTO.getDescripcion(), seccion.getDescripcion());
        Assertions.assertEquals(seccionDTO.getDepartamentoId(), seccion.getDepartamento().getId());
    }

}
