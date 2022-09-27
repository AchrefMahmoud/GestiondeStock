package com.tn.GestiondeStock.controller;

import com.tn.GestiondeStock.controller.api.ClientApi;
import com.tn.GestiondeStock.dto.ClientDto;
import com.tn.GestiondeStock.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController implements ClientApi {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ClientDto saveClient(ClientDto dto) {
        return clientService.save(dto);
    }

    @Override
    public ClientDto findClientById(Integer idClient) {
        return clientService.findById(idClient);
    }

    @Override
    public List<ClientDto> findAllClient() {
        return clientService.findAll();
    }

    @Override
    public void deleteClient(Integer id) {
        clientService.delete(id);
    }
}
