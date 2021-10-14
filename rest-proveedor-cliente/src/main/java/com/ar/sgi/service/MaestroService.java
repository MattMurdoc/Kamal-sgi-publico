package com.ar.sgi.service;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.Maestro;
import com.ar.sgi.model.dto.MaestroDTO;
import com.ar.sgi.model.mappers.ListMapper;
import com.ar.sgi.repository.MaestroRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class MaestroService {
    private static final Logger LOGGER = Logger.getLogger(MaestroService.class.getName());

    @Inject
    MaestroRepository maestroRepository;

    public List<MaestroDTO> getTipoDNI() throws PersistanceLayerException, ServiceLayerException {
        try {
            List<Maestro> listMaestro = maestroRepository.listByTipoDato(MaestroRepository.TIPO_DNI);
            List<MaestroDTO> listMaestroDTO = new ArrayList<>();
            for(Maestro maestro: listMaestro) {
                MaestroDTO maestroDTO = new MaestroDTO();
                maestroDTO.setDescripcion(maestro.getDescripcion());
                maestroDTO.setValor(maestro.getValor());
                listMaestroDTO.add(maestroDTO);
            }
            return listMaestroDTO;
        } catch (PersistanceLayerException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceLayerException(ServiceLayerException.LISTAR_TIPO_DNI);
        }
    }

    public List<MaestroDTO> getCategoriaIVA() throws ServiceLayerException, PersistanceLayerException {
        try {
            List<Maestro> listMaestro = maestroRepository.listByTipoDato(MaestroRepository.TIPO_RESPONSABILIDAD_CLIENTE);
            List<MaestroDTO> listMaestroDTO = new ArrayList<>();
            for(Maestro maestro: listMaestro) {
                MaestroDTO maestroDTO = new MaestroDTO();
                maestroDTO.setDescripcion(maestro.getDescripcion());
                maestroDTO.setValor(maestro.getValor());
                listMaestroDTO.add(maestroDTO);
            }
            return listMaestroDTO;
        } catch (PersistanceLayerException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceLayerException(ServiceLayerException.LISTAR_RESPONSABILIDAD_CLIENTE);
        }
    }
}
