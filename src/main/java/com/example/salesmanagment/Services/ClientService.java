package com.example.salesmanagment.Services;

import com.example.salesmanagment.Entity.Client;
import com.example.salesmanagment.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public void save(Client client) {
        clientRepository.save(client);
    }

    public Client get(long id) {
        return clientRepository.findById(id).get();
    }

    public void delete(long id) {
        clientRepository.deleteById(id);
    }


    public  Client update(Client client){
        Client clientExist = clientRepository.findById(client.getId()).orElse(null);

        assert clientExist != null;
        clientExist.setName(client.getName());
        clientExist.setLastName(client.getLastName());
        clientExist.setMobile(client.getMobile());
        return clientRepository.save(clientExist) ;
    }
}
