package com.example.salesmanagment.Api;

import com.example.salesmanagment.Entity.Seller;
import com.example.salesmanagment.Services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("Api/V1/sellers")
public class SellerApi {
    @Autowired
    private SellerService sellerService;

    @GetMapping
    public List<Seller> getAll() {
        return sellerService.getAll();
    }

    @PostMapping("/save")
    public void save(@Valid @RequestBody Seller seller) {
        sellerService.save(seller);
    }

    @GetMapping("/{id}")
    public Seller getSellerById(@PathVariable(value = "id") Long id) {
        return sellerService.get(id);
    }

    @PutMapping("update")
    public Seller update(@RequestBody Seller seller) {
        try {

            return sellerService.update(seller);

        } catch (NoSuchElementException ignored) {
            return  null;
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        sellerService.delete(id);
    }
}
