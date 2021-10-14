package com.ar.sgi.services;

import com.ar.sgi.exceptions.PersistanceLayerException;
import com.ar.sgi.exceptions.ServiceLayerException;
import com.ar.sgi.model.dto.MaestroDTO;
import com.ar.sgi.model.mappers.ListMapper;
import com.ar.sgi.repository.MaestroRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class MaestroService {
    private static final Logger LOGGER = Logger.getLogger(DepartamentoService.class.getName());

    @Inject
    MaestroRepository maestroRepository;

    public List<MaestroDTO> getTipoMovimientos() throws PersistanceLayerException, ServiceLayerException {
        try {
            return ListMapper.mapList(maestroRepository.listByTipoDato(MaestroRepository.TIPO_DATO_TIPO_MOVIMIENTO), MaestroDTO.class);
        } catch (PersistanceLayerException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceLayerException(ServiceLayerException.LISTAR_TIPO_MOVIMIENTOS);
        }
    }

    public List<MaestroDTO> getMotivoByTipoMovimiento(Long tipoMovimiento) throws ServiceLayerException, PersistanceLayerException {
        try {
            return ListMapper.mapList(maestroRepository.listByTipoDatoYFiltro(MaestroRepository.TIPO_DATO_MOTIVO, tipoMovimiento), MaestroDTO.class);
        } catch (PersistanceLayerException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceLayerException(ServiceLayerException.LISTAR_MOTIVOS);
        }
    }
}
