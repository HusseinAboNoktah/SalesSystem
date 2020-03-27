package com.example.salesmanagment.Controller;

import com.example.salesmanagment.Entity.Client;
import com.example.salesmanagment.Entity.Product;
import com.example.salesmanagment.Services.CategoryService;
import com.example.salesmanagment.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("product")
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/index")
    String view( Model model) {
        List<Product> allProducts = productService.getAll();
        model.addAttribute("products", allProducts);
        return "product/index";

    }

    @GetMapping("/create")
    public String create(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAll());
        return "product/create";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute("product") @Valid Product product) {
        LocalDateTime now = LocalDateTime.now();
        product.setCreationDate(now);
        productService.save(product);

        return "redirect:/product/index";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView Edit(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("product/edit");
        Product product = productService.get(id);
        modelAndView.addObject("product", product);
        modelAndView.addObject("categories", categoryService.getAll());

        return modelAndView;
    }
    @PostMapping(value = "/update")
    public String update(@ModelAttribute("category") @Valid Product product) {
        productService.update(product);
        return "redirect:/product/index";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        productService.delete(id);
        return "redirect:/product/index";
    }
}
