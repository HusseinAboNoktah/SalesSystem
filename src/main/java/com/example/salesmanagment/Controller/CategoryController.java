package com.example.salesmanagment.Controller;


import com.example.salesmanagment.Entity.Category;

import com.example.salesmanagment.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import java.util.List;


@Controller
@RequestMapping("category")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/index")
    public String view(Model model) {
        List<Category> allCategories = categoryService.getAll();
        model.addAttribute("categories", allCategories);
        return  "category/index";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "category/create";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute("category") @Valid Category category) {
        categoryService.save(category);
        return "redirect:/category/index";
    }
    @PostMapping(value = "/update")
    public String update(@ModelAttribute("category") @Valid Category category) {
        categoryService.update(category);
        return "redirect:/category/index";
    }
    @RequestMapping("/edit/{id}")
    public ModelAndView EditProduct(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("category/edit");
        Category category = categoryService.get(id);
        modelAndView.addObject("category", category);
        return modelAndView;
    }


}
