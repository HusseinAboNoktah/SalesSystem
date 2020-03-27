package com.example.salesmanagment.Api;

import com.example.salesmanagment.Entity.Client;
import com.example.salesmanagment.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("Api/V1/clients")
public class ClientApi {

    @Autowired
        private ClientService clientService;

        @GetMapping
        public List<Client> getAll() {
            return clientService.getAll();
        }

        @PostMapping("/save")
        public void save(@Valid @RequestBody Client client) {
            clientService.save(client);
        }

        @GetMapping("/{id}")
        public Client getClientById(@PathVariable(value = "id") Long id)
        {

            return clientService.get(id);
        }

        @PutMapping("/update")
        public Client update(@RequestBody Client client) {
            try {

                return clientService.update(client);

            } catch (NoSuchElementException ignored) {
                return  null;
            }
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable(value = "id") Long id) {
            clientService.delete(id);
        }
}
