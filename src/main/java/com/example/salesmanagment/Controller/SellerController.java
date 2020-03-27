package com.example.salesmanagment.Controller;

import com.example.salesmanagment.Entity.Product;
import com.example.salesmanagment.Entity.Seller;
import com.example.salesmanagment.Services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
@RequestMapping("seller")
@Controller
public class SellerController {
    @Autowired
    private SellerService sellerService;


    @RequestMapping(value = "/index")
    String view( Model model) {
        List<Seller> allSellers = sellerService.getAll();
        model.addAttribute("sellers", allSellers);
        return "seller/index";

    }

    @GetMapping("/create")
    public String create(Model model) {
        Seller seller = new Seller();
        model.addAttribute("seller", seller);

        return "seller/create";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute("seller") @Valid Seller seller) {


        sellerService.save(seller);

        return "redirect:/seller/index";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView Edit(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("seller/edit");
        Seller seller = sellerService.get(id);
        modelAndView.addObject("seller", seller);


        return modelAndView;
    }
    @PostMapping(value = "/update")
    public String update(@ModelAttribute("category") @Valid Seller seller) {
        sellerService.update(seller);
        return "redirect:/seller/index";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        sellerService.delete(id);
        return "redirect:/seller/index";
    }
}
