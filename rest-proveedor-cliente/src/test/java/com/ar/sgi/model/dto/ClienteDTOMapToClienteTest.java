package com.ar.sgi.model.dto;

import com.ar.sgi.model.Cliente;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClienteDTOMapToClienteTest {

    @Test
    public void testMapClienteDTOtoCliente() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(1L);
        clienteDTO.setRazonSocial("RazonSocial");
        clienteDTO.setDomicilio("Domicilio");
        clienteDTO.setCuit("11111111");
        clienteDTO.setCategoriaIVA(2L);

        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        Cliente cliente = mapper.map(clienteDTO, Cliente.class);

        Assertions.assertEquals(clienteDTO.getId(), cliente.getId());
        Assertions.assertEquals(clienteDTO.getRazonSocial(), cliente.getRazonSocial());
        Assertions.assertEquals(clienteDTO.getDomicilio(), cliente.getDomicilio());
        Assertions.assertEquals(clienteDTO.getCuit(), cliente.getCuit());
        Assertions.assertEquals(clienteDTO.getCategoriaIVA(), cliente.getCategoriaIVA());
    }
}
