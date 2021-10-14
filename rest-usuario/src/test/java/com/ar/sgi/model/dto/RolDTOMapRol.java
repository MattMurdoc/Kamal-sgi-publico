package com.ar.sgi.model.dto;

import com.ar.sgi.model.Rol;
import com.ar.sgi.model.mappers.ListMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class RolDTOMapRol {

    @Test
    public void testMapRolDTOtoRol() {
        RolDTO rolDTO = new RolDTO();
        rolDTO.setId(1L);
        rolDTO.setDescripcion("Rol Test");

        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        Rol rol = mapper.map(rolDTO, Rol.class);

        Assertions.assertEquals(rolDTO.getId(), rol.getId());
        Assertions.assertEquals(rolDTO.getDescripcion(), rol.getDescripcion());
    }

    @Test
    public void testMapPaginatorDepartamentoToPaginatorDepartamentoDTO() {
        Rol rol = new Rol();
        rol.setId(1L);
        rol.setDescripcion("admin");

        Rol rol2 = new Rol();
        rol2.setId(1L);
        rol2.setDescripcion("usuario");

        List<Rol> lista = new ArrayList<>();
        lista.add(rol);
        lista.add(rol2);

        List<RolDTO> rolDTOList =  ListMapper.mapList(lista, RolDTO.class);

        Assertions.assertEquals(rolDTOList.get(0).getId(), lista.get(0).getId());
        Assertions.assertEquals(rolDTOList.get(0).getDescripcion(), lista.get(0).getDescripcion());
    }

}
