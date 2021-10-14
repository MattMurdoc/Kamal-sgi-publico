package com.ar.sgi.model.dto;

import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Rol;
import com.ar.sgi.model.Usuario;
import com.ar.sgi.model.mappers.PaginatorMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class UsuarioDTOMapUsuario {

    @Test
    public void testMapUsuarioDTOtoUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        usuarioDTO.setUsuario("admin");
        usuarioDTO.setPassword("123qwe");
        usuarioDTO.setNombre("Nombre Test");
        usuarioDTO.setApellido("Apellido Test");
        usuarioDTO.setRolId(1L);
        usuarioDTO.setEstado(true);

        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        Usuario usuario = mapper.map(usuarioDTO, Usuario.class);

        Assertions.assertEquals(usuarioDTO.getId(), usuario.getId());
        Assertions.assertEquals(usuarioDTO.getUsuario(), usuario.getUsuario());
        Assertions.assertEquals(usuarioDTO.getPassword(), usuario.getPassword());
        Assertions.assertEquals(usuarioDTO.getNombre(), usuario.getNombre());
        Assertions.assertEquals(usuarioDTO.getApellido(), usuario.getApellido());
        Assertions.assertEquals(usuarioDTO.getRolId(), usuario.getRol().getId());
        Assertions.assertEquals(usuarioDTO.getEstado(), usuario.getEstado());
    }

    @Test
    public void testMapPaginatorDepartamentoToPaginatorDepartamentoDTO() {
        Rol rol = new Rol();
        rol.setId(1L);
        rol.setDescripcion("admin");

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsuario("admin");
        usuario.setPassword("123qwe");
        usuario.setNombre("Nombre Test");
        usuario.setApellido("Apellido Test");
        usuario.setRol(rol);
        usuario.setEstado(true);

        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setUsuario("admin");
        usuario2.setPassword("123qwe");
        usuario2.setNombre("Nombre Test");
        usuario2.setApellido("Apellido Test");
        usuario2.setRol(rol);
        usuario2.setEstado(true);

        List<Usuario> lista = new ArrayList<>();
        lista.add(usuario);
        lista.add(usuario2);

        Paginator<Usuario> usuarioPaginator = new Paginator<>(lista, lista.size());

        Paginator<UsuarioDTO> usuarioDTOPaginator =  PaginatorMapper.mapPaginator(usuarioPaginator, UsuarioDTO.class);

        Assertions.assertEquals(usuarioDTOPaginator.getEntity().get(0).getId(), usuarioPaginator.getEntity().get(0).getId());
        Assertions.assertEquals(usuarioDTOPaginator.getEntity().get(0).getUsuario(), usuarioPaginator.getEntity().get(0).getUsuario());
        Assertions.assertEquals(usuarioDTOPaginator.getEntity().get(0).getPassword(), usuarioPaginator.getEntity().get(0).getPassword());
        Assertions.assertEquals(usuarioDTOPaginator.getEntity().get(0).getNombre(), usuarioPaginator.getEntity().get(0).getNombre());
        Assertions.assertEquals(usuarioDTOPaginator.getEntity().get(0).getApellido(), usuarioPaginator.getEntity().get(0).getApellido());
        Assertions.assertEquals(usuarioDTOPaginator.getEntity().get(0).getRolId(), usuarioPaginator.getEntity().get(0).getRol().getId());
        Assertions.assertEquals(usuarioDTOPaginator.getEntity().get(0).getEstado(), usuarioPaginator.getEntity().get(0).getEstado());
    }

}
