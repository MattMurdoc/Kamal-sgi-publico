package com.ar.sgi.model.dto;

import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Permiso;
import com.ar.sgi.model.Rol;
import com.ar.sgi.model.mappers.ListMapper;
import com.ar.sgi.model.mappers.PaginatorMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class PermisoDTOMapPermiso {

    @Test
    public void testMapPermisoDTOtoPermiso() {
        PermisoDTO permisoDTO = new PermisoDTO();
        permisoDTO.setId(1L);
        permisoDTO.setDescripcion("Bla bla bla");
        permisoDTO.setIdRol(1L);

        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        Permiso permiso = mapper.map(permisoDTO, Permiso.class);

        Assertions.assertEquals(permisoDTO.getId(), permiso.getId());
        Assertions.assertEquals(permisoDTO.getDescripcion(), permiso.getDescripcion());
        Assertions.assertEquals(permisoDTO.getIdRol(), permiso.getRol().getId());
    }

    @Test
    public void testMapListPermisoToListPermisoDTO() {
        Rol rol = new Rol();
        rol.setId(1L);
        rol.setDescripcion("admin");

        Permiso permiso = new Permiso();
        permiso.setId(1L);
        permiso.setDescripcion("Permiso test 1");
        permiso.setRol(rol);

        Permiso permiso2 = new Permiso();
        permiso2.setId(2L);
        permiso2.setDescripcion("Permiso test 2");
        permiso2.setRol(rol);

        List<Permiso> lista = new ArrayList<>();
        lista.add(permiso);
        lista.add(permiso2);

        List<PermisoDTO> permisoDTOList =  ListMapper.mapList(lista, PermisoDTO.class);

        Assertions.assertEquals(permisoDTOList.get(0).getId(), lista.get(0).getId());
        Assertions.assertEquals(permisoDTOList.get(0).getDescripcion(), lista.get(0).getDescripcion());
        Assertions.assertEquals(permisoDTOList.get(0).getIdRol(), lista.get(0).getRol().getId());
    }

    @Test
    public void testMapPaginatorPermisoToPaginatorPermisoDTO() {
        Rol rol = new Rol();
        rol.setId(1L);
        rol.setDescripcion("admin");

        Permiso permiso = new Permiso();
        permiso.setId(1L);
        permiso.setDescripcion("Permiso test 1");
        permiso.setRol(rol);

        Permiso permiso2 = new Permiso();
        permiso2.setId(2L);
        permiso2.setDescripcion("Permiso test 2");
        permiso2.setRol(rol);

        List<Permiso> lista = new ArrayList<>();
        lista.add(permiso);
        lista.add(permiso2);

        Paginator<Permiso> permisoPaginator = new Paginator<>(lista, lista.size());

        Paginator<PermisoDTO> permisoDTOPaginator =  PaginatorMapper.mapPaginator(permisoPaginator, PermisoDTO.class);

        Assertions.assertEquals(permisoPaginator.getEntity().get(0).getId(), permisoDTOPaginator.getEntity().get(0).getId());
        Assertions.assertEquals(permisoPaginator.getEntity().get(0).getDescripcion(), permisoDTOPaginator.getEntity().get(0).getDescripcion());
        Assertions.assertEquals(permisoPaginator.getEntity().get(0).getRol().getId(), permisoDTOPaginator.getEntity().get(0).getIdRol());
        Assertions.assertEquals(permisoPaginator.getCantidadRegistros(), permisoDTOPaginator.getCantidadRegistros());
    }

}
