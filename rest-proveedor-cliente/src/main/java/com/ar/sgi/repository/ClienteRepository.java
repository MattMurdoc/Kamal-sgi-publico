package com.ar.sgi.repository;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.model.Cliente;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.dto.ClienteFiltroDTO;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import org.junit.platform.commons.util.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class ClienteRepository implements PanacheRepositoryBase<Cliente, Long> {

    Logger LOGGER = Logger.getLogger(ClienteRepository.class.getName());

    public Paginator<Cliente> listarFiltro(Integer pagina, Integer items, ClienteFiltroDTO clienteFiltroDTO) throws PersistanceLayerException {
        try {
            PanacheQuery<Cliente> query;

            if (clienteFiltroDTO != null) {

                Map<String, Object> params = new HashMap<>();
                String consulta = "";

                if (StringUtils.isNotBlank(clienteFiltroDTO.getRazonSocial())) {
                    params.put("razonSocial", "%" + clienteFiltroDTO.getRazonSocial().trim().toUpperCase() + "%");
                    consulta += "upper(razonSocial) LIKE :razonSocial ";
                }

                if (StringUtils.isNotBlank(clienteFiltroDTO.getCuit())) {
                    params.put("cuit", "%" + clienteFiltroDTO.getCuit().trim().toUpperCase() + "%");
                    if (!consulta.isEmpty()) {
                        consulta += " and ";
                    }
                    consulta += "upper(cuit) LIKE :cuit ";
                }

                query = this.find(consulta, params);

            } else {
                query = this.findAll();
            }
            query.page(Page.of(pagina, items));
            List<Cliente> clientes = query.list();

            return clientes != null && !clientes.isEmpty() ? new Paginator<>(clientes, query.count()) : null;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new PersistanceLayerException(PersistanceLayerException.LISTAR_CLIENTE_EXCEPTION);
        }
    }
}