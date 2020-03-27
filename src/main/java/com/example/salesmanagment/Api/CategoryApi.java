package com.example.salesmanagment.Api;

import com.example.salesmanagment.Entity.Category;
import com.example.salesmanagment.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("Api/V1/categories")
public class CategoryApi {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @PostMapping(value = "/save" ,  consumes = { MediaType.APPLICATION_JSON_VALUE})
    public void save( @RequestBody Category category) {
        categoryService.save(category);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable(value = "id") Long id) {
        return categoryService.get(id);
    }

    @PutMapping(value = "/update")
    public Category update(@RequestBody Category category) {
        try {

            return categoryService.update(category);

        } catch (NoSuchElementException ignored) {
          return  null;
        }
    }
}
