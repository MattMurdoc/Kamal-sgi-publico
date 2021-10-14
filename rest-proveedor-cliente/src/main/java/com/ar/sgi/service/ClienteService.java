package com.ar.sgi.service;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Cliente;
import com.ar.sgi.model.Paginator;
import com.ar.sgi.model.dto.ClienteDTO;
import com.ar.sgi.model.dto.ClienteFiltroDTO;
import com.ar.sgi.repository.ClienteRepository;
import com.ar.sgi.repository.MaestroRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class ClienteService {

    private static final Logger LOGGER = Logger.getLogger(ClienteService.class.getName());

    @Inject
    ClienteRepository clienteRepository;
    @Inject
    MaestroRepository maestroRepository;

    public Paginator<ClienteDTO> listar(Integer pagina, Integer items, ClienteFiltroDTO clienteFiltroDTO) throws ServiceLayerException, PersistanceLayerException {
        try {
            Paginator<Cliente> clientePaginator = clienteRepository.listarFiltro(pagina, items, clienteFiltroDTO);
            Paginator<ClienteDTO> clienteDTOPaginator = null;
            if(clientePaginator != null && clientePaginator.validar()) {
                List<ClienteDTO> listClienteDTO = new ArrayList<>();
                for (Cliente cliente : clientePaginator.getEntity()) {
                    ClienteDTO dto = new ClienteDTO();
                    dto.setId(cliente.getId());
                    dto.setRazonSocial(cliente.getRazonSocial());
                    dto.setCategoriaIVAstr(maestroRepository.getDescripcionByValorYTipoDato(cliente.getCategoriaIVA(), MaestroRepository.TIPO_RESPONSABILIDAD_CLIENTE));
                    dto.setCategoriaIVA(cliente.getCategoriaIVA());
                    dto.setTipoDNIstr(maestroRepository.getDescripcionByValorYTipoDato(cliente.getTipoDNI(), MaestroRepository.TIPO_DNI));
                    dto.setTipoDNI(cliente.getTipoDNI());
                    dto.setCuit(cliente.getCuit());
                    dto.setDomicilio(cliente.getDomicilio());
                    listClienteDTO.add(dto);
                }
                clienteDTOPaginator = new Paginator<>(listClienteDTO, clientePaginator.getCantidadRegistros());
            }
            return clienteDTOPaginator;
        } catch (PersistanceLayerException pe) {
            throw pe;
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.LISTAR_CLIENTE_EXCEPTION);
        }
    }

    public Cliente getById(Long id) throws ServiceLayerException {
        try {
            return clienteRepository.findById(id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.BUSCAR_CLIENTE_EXCEPTION);
        }
    }

    @Transactional
    public void agregar(Cliente cliente) throws ServiceLayerException {
        try {
            cliente.setId(null);
            clienteRepository.persist(cliente);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.CREAR_CLIENTE_EXCEPTION);
        }
    }

    @Transactional
    public void actualizar(Cliente cliente) throws ServiceLayerException {
        try {
            clienteRepository.getEntityManager().merge(cliente);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new ServiceLayerException(ServiceLayerException.ACTUALIZAR_CLIENTE_EXCEPTION);
        }
    }
}