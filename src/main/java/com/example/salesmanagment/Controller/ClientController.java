package com.example.salesmanagment.Controller;

import com.example.salesmanagment.Entity.Category;
import com.example.salesmanagment.Entity.Client;
import com.example.salesmanagment.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("client")
@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;


    @RequestMapping(value = "/index")
    String view( Model model) {
        List<Client> allClients = clientService.getAll();
        model.addAttribute("clients", allClients);
        return "client/index";

    }

    @GetMapping("/create")
    public String create(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);

        return "client/create";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute("client") @Valid Client client) {


        clientService.save(client);

        return "redirect:/client/index";
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute("category") @Valid Client client) {
        clientService.update(client);
        return "redirect:/client/index";
    }
    @RequestMapping("/edit/{id}")
    public ModelAndView Edit(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("client/edit");
        Client client = clientService.get(id);
        modelAndView.addObject("client", client);


        return modelAndView;
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        clientService.delete(id);
        return "redirect:/client/index";
    }
}
