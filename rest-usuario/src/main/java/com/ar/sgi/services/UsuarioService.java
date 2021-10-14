package com.ar.sgi.services;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Usuario;
import com.ar.sgi.model.dto.PermisoDTO;
import com.ar.sgi.model.dto.PrincipalDTO;
import com.ar.sgi.model.dto.UsuarioDTO;
import com.ar.sgi.model.dto.UsuarioFiltroDTO;
import com.ar.sgi.model.mappers.ListMapper;
import com.ar.sgi.repository.PermisoRepository;
import com.ar.sgi.repository.UsuarioRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class UsuarioService {

    private static Logger LOGGER = Logger.getLogger(RolService.class.getName());

    @Inject
    UsuarioRepository usuarioRepository;
    @Inject
    PermisoService permisoService;

    public Paginator<Usuario> listarTodo(int pagina, int items) throws Exception {
        try {
            return usuarioRepository.listar(pagina, items);
        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new ServiceLayerException(ServiceLayerException.LISTAR_USUARIO_EXCEPTION);
        }
    }

    public Usuario buscarUsuario(Long rolId) throws Exception {
        try {
            return usuarioRepository.findById(rolId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new ServiceLayerException(ServiceLayerException.BUSCAR_USUARIO_EXCEPTION);
        }
    }

    @Transactional
    public void agregar(Usuario usuario) throws ServiceLayerException {
        try {
            usuario.setId(null);
            usuarioRepository.persist(usuario);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new ServiceLayerException(ServiceLayerException.CREAR_USUARIO_EXCEPTION);
        }
    }

    @Transactional
    public void actualizar(Usuario usuario) throws ServiceLayerException {
        try {
            usuarioRepository.getEntityManager().merge(usuario);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_USUARIO_EXCEPTION);
        }
    }

    @Transactional
    public void cambiarEstado(Long id) throws PersistanceLayerException, ServiceLayerException {
        try {
            usuarioRepository.cambiarEstado(id);
            ;
        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_USUARIO_EXCEPTION);
        }
    }

    public PrincipalDTO validarUsuario(UsuarioDTO usuarioDTO) throws PersistanceLayerException, ServiceLayerException {
        try {

            Usuario usuario = usuarioRepository.validarUsuario(usuarioDTO);
            if(usuario != null) {
                PrincipalDTO principalDTO = new PrincipalDTO();
                principalDTO.setUsuario(usuario.getUsuario());
                principalDTO.setNombreCompleto(usuario.getNombre() + " " + usuario.getApellido());
                principalDTO.setRol(usuario.getRol().getDescripcion());
                //principalDTO.setPermisos(ListMapper.mapList(permisoService.listarByRolId(usuario.getRol()), PermisoDTO.class));
                return principalDTO;
            } else {
                throw new Exception("Usuario incorrecto");
            }
        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            throw new ServiceLayerException(ServiceLayerException.VALIDAR_USUARIO_EXCEPTION);
        }
    }
    public Paginator<Usuario> listar(Integer pagina, Integer items, UsuarioFiltroDTO usuarioFiltroDTO) throws ServiceLayerException {
        Paginator<Usuario> paginator = null;
        try {
            paginator = usuarioRepository.listarFiltro(pagina, items, usuarioFiltroDTO);
        } catch(Exception e) {
            throw new ServiceLayerException(ServiceLayerException.LISTAR_USUARIO_EXCEPTION);
        }
        return paginator;
    }

    public Usuario getById(Long id) throws ServiceLayerException {
        try {
            return usuarioRepository.findById(id);
        } catch (Exception e) {
            throw new ServiceLayerException(ServiceLayerException.BUSCAR_USUARIO_EXCEPTION);
        }
    }
}