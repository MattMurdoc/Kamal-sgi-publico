package com.ar.sgi.model.dto;

import com.ar.sgi.model.Provincia;
import com.ar.sgi.model.Rubro;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RubroDTOMapToRubroTest {

    @Test
    public void testMapRubroDTOtoRubro() {
        RubroDTO rubroDTO = new RubroDTO();
        rubroDTO.setId(1L);
        rubroDTO.setDescripcion("descripcion");
        rubroDTO.setEstado(true);

        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        Rubro rubro = mapper.map(rubroDTO, Rubro.class);

        Assertions.assertEquals(rubroDTO.getId(), rubro.getId() );
        Assertions.assertEquals(rubroDTO.getDescripcion(), rubro.getDescripcion());
        Assertions.assertEquals(rubroDTO.getEstado(), rubro.getEstado());
    }

}
