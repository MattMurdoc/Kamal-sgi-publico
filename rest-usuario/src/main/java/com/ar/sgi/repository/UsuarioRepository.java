package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.Permiso;
import com.ar.sgi.model.Rol;
import com.ar.sgi.model.Usuario;
import com.ar.sgi.model.dto.UsuarioDTO;
import com.ar.sgi.model.dto.UsuarioFiltroDTO;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;



@ApplicationScoped
public class UsuarioRepository implements PanacheRepositoryBase<Usuario, Long> {

    @Inject
    RolRepository rolRepository;

    private static final Logger LOGGER = Logger.getLogger(RolRepository.class.getName());

    public Paginator<Usuario> listar(Integer pagina, Integer items) throws PersistanceLayerException {
        Paginator<Usuario> paginator = null;

        try {
            PanacheQuery<Usuario> query = this.find("select id, usuario, nombre, apellido, rol from TB_USUARIO");
            query.page(Page.of(pagina, items));
            List<Usuario> usuario = query.list();
            paginator = usuario != null && !usuario.isEmpty() ? new Paginator<Usuario>(usuario, this.count()) : null;
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_USUARIO_EXCEPTION);
        }
        return paginator;
    }

    public void cambiarEstado(Long id) throws PersistanceLayerException {
        try {
            Usuario usuario = this.findById(id);
            this.update("estado = ?1 where id = ?2", !usuario.getEstado(), id);
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new PersistanceLayerException(PersistanceLayerException.BORRAR_USUARIO_EXCEPTION);
        }
    }

    public Usuario validarUsuario(UsuarioDTO usuarioDTO) throws PersistanceLayerException {
        try {
            Usuario usuario = this.find("usuario = ?1 and password = ?2", usuarioDTO.getUsuario(), usuarioDTO.getPassword()).firstResult();
            return usuario;
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new PersistanceLayerException(PersistanceLayerException.BORRAR_USUARIO_EXCEPTION);
        }
    }
    public Paginator<Usuario> listarFiltro(Integer pagina, Integer items, UsuarioFiltroDTO usuarioFiltroDTO) throws PersistanceLayerException {
        Paginator<Usuario> paginator = null;
        try {
            PanacheQuery<Usuario> query = null;

            if (usuarioFiltroDTO != null) {

                Map<String, Object> params = new HashMap<>();
                String consulta = "";

                if (StringUtils.isNotBlank(usuarioFiltroDTO.getUsuario())) {
                    params.put("usuario",usuarioFiltroDTO.getUsuario().trim().toUpperCase());
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "upper(usuario) LIKE :usuario ";
                }

                if (StringUtils.isNotBlank(usuarioFiltroDTO.getNombre())) {
                    params.put("nombre", usuarioFiltroDTO.getNombre().trim().toUpperCase());
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "upper(nombre) LIKE :nombre ";
                }

                if (StringUtils.isNotBlank(usuarioFiltroDTO.getApellido())) {
                    params.put("apellido", usuarioFiltroDTO.getApellido().trim().toUpperCase());
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "upper(apellido) LIKE :apellido ";
                }

                if (usuarioFiltroDTO.getRol() != null && usuarioFiltroDTO.getRol() > 0) {
                    Rol rol = rolRepository.findById(usuarioFiltroDTO.getRol());
                    params.put("rol", rol);
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "(rol) = :rol ";
                }

                params.put("estado", usuarioFiltroDTO.getEstado());
                consulta += "(estado) = :estado";

                query = this.find(consulta, params);

            } else {
                query = this.findAll();
            }
            query.page(Page.of(pagina, items));
            List<Usuario> clientes = query.list();

            paginator = clientes != null && !clientes.isEmpty() ? new Paginator<Usuario>(clientes, query.count()) : null;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_USUARIO_EXCEPTION);
        }
        return paginator;
    }
}
