package com.dragos.pharmacy.controller;

import com.dragos.pharmacy.exception.ClientNotFoundException;
import com.dragos.pharmacy.model.Client;
import com.dragos.pharmacy.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("client", new Client());
        return "client";
    }

    @GetMapping("/{id}")
    public Client findOne(@PathVariable Long id) {
        try {
            return clientService.findOne(id);
        } catch (
                ClientNotFoundException exp
        ) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, exp.getMessage(), exp
            );
        }

    }

    @PostMapping
    public String save(Client client, BindingResult result, Model model) {
        clientService.save(client);
        return "redirect:/clients";
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) throws ClientNotFoundException {
        return clientService.delete(id);
    }

    @GetMapping("/clients/{age}")
    public List<Client> sortByAge(@PathVariable Integer age) {
        return clientService.ageGreaterThan(age);
    }

    @DeleteMapping
    public void emptyTable() {
        clientService.emptyTable();
    }

    @PutMapping
    public Client updateClient(final @RequestBody Client client) throws ClientNotFoundException {
        return clientService.updateClient(client);
    }

}
