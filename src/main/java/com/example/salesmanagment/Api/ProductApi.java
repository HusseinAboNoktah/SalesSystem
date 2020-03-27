package com.example.salesmanagment.Api;

import com.example.salesmanagment.Entity.Category;
import com.example.salesmanagment.Entity.Product;
import com.example.salesmanagment.Services.CategoryService;
import com.example.salesmanagment.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("Api/V1/products")
public class ProductApi {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @PostMapping(value = "/save")
    public void save(@Valid @RequestBody Product product) {
        productService.save(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable(value = "id") Long id) {
        return productService.get(id);
    }

    @PutMapping("/update")
    public Product update(@RequestBody Product product) {
        try {

            return productService.update(product);

        } catch (NoSuchElementException ignored) {
            return  null;
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
         productService.delete(id);
    }
}
