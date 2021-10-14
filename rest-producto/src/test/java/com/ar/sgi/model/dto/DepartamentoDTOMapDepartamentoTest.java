package com.ar.sgi.model.dto;

import com.ar.sgi.model.Departamento;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.mappers.ListMapper;
import com.ar.sgi.model.mappers.PaginatorMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class DepartamentoDTOMapDepartamentoTest {

    @Test
    public void testMapDepartamentoDTOtoDepartamento() {
        DepartamentoDTO departamentoDTO = new DepartamentoDTO();
        departamentoDTO.setId(1L);
        departamentoDTO.setDescripcion("Test 1");
        departamentoDTO.setEstado(true);

        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        Departamento departamento = mapper.map(departamentoDTO, Departamento.class);

        Assertions.assertEquals(departamentoDTO.getId(), departamento.getId());
        Assertions.assertEquals(departamentoDTO.getDescripcion(), departamento.getDescripcion());
        Assertions.assertEquals(departamentoDTO.getEstado(), departamento.getEstado());
    }

    @Test
    public void testMapPaginatorDepartamentoToPaginatorDepartamentoDTO() {
        Departamento departamento = new Departamento();
        departamento.setDescripcion("ASD");
        departamento.setId(1L);
        departamento.setEstado(true);

        Departamento departamento2 = new Departamento();
        departamento2.setDescripcion("ASD2");
        departamento2.setId(2L);
        departamento2.setEstado(true);

        List<Departamento> lista = new ArrayList<>();
        lista.add(departamento);
        lista.add(departamento2);

        Paginator<Departamento> departamentoPag = new Paginator<>(lista, lista.size());

        Paginator<DepartamentoDTO> departamentoDTOPaginator =  PaginatorMapper.mapPaginator(departamentoPag, DepartamentoDTO.class);

        Assertions.assertEquals(departamentoDTOPaginator.getEntity().get(0).getId(), departamentoPag.getEntity().get(0).getId());
        Assertions.assertEquals(departamentoDTOPaginator.getEntity().get(0).getDescripcion(), departamentoPag.getEntity().get(0).getDescripcion());
        Assertions.assertEquals(departamentoDTOPaginator.getEntity().get(0).getEstado(), departamentoPag.getEntity().get(0).getEstado());
        Assertions.assertEquals(departamentoDTOPaginator.getCantidadRegistros(), departamentoPag.getCantidadRegistros());
    }

    @Test
    public void testMapListDepartamentoToListDepartamentoDTO() {
        Departamento departamento = new Departamento();
        departamento.setDescripcion("ASD");
        departamento.setId(1L);
        departamento.setEstado(true);

        Departamento departamento2 = new Departamento();
        departamento2.setDescripcion("ASD2");
        departamento2.setId(2L);
        departamento2.setEstado(true);

        List<Departamento> lista = new ArrayList<>();
        lista.add(departamento);
        lista.add(departamento2);

        List<DepartamentoDTO> departamentoDTOList = ListMapper.mapList(lista, DepartamentoDTO.class);

        Assertions.assertEquals(departamentoDTOList.get(0).getId(), lista.get(0).getId());
        Assertions.assertEquals(departamentoDTOList.get(0).getDescripcion(), lista.get(0).getDescripcion());
        Assertions.assertEquals(departamentoDTOList.get(0).getEstado(), lista.get(0).getEstado());
    }

}
