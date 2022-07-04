package com.dragos.pharmacy.service;

import com.dragos.pharmacy.exception.ClientNotFoundException;
import com.dragos.pharmacy.model.Client;
import com.dragos.pharmacy.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    /*
    1.Find one - pe id (foloseste ClientRepository)
    2.Find all
    3.Save
     */

    public Client findOne(Long id) throws ClientNotFoundException{
        Optional<Client> res = clientRepository.findById(id);
        if (res.isPresent()) {
            return res.get();
        }
        throw new ClientNotFoundException("Client not found.");
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client save(Client client) {
         return clientRepository.save(client);
    }

    public boolean delete(Long id) throws ClientNotFoundException {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Client> ageGreaterThan(Integer age){
        return clientRepository.ageGreaterThan(age);
    }

    public void emptyTable() {
        clientRepository.deleteAll();
    }

    public Client updateClient(Client client) throws ClientNotFoundException{
        if (client.getId() != null){
            Optional<Client> dbOptClient = clientRepository.findById(client.getId());
            if (dbOptClient.isPresent()) {
                Client dbClient = dbOptClient.get();
                dbClient.setName(client.getName());
                dbClient.setGender(client.getGender());
                clientRepository.save(dbClient);
                return dbClient;
            }
        }
        throw new ClientNotFoundException("Invalid client ID!");
    }

}
