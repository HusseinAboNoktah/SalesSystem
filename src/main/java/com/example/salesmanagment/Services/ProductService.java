package com.example.salesmanagment.Services;

import com.example.salesmanagment.Entity.Product;
import com.example.salesmanagment.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository ;
    public List<Product> getAll(){
        return  productRepository.findAll();
    }

    public void save(Product Product){

       productRepository.save(Product);
    }
    public Product get(long id){
        return productRepository.findById(id).get();
    }
    public Product update(Product product){
        Product existProduct  = productRepository.findById(product.getId()).orElse(null);

        assert existProduct != null;
        existProduct.setName(product.getName());
        existProduct.setDescription(product.getDescription());
        existProduct.setCategory(product.getCategory());
        return productRepository.save(existProduct);
    }

    public void delete(long id){
        productRepository.deleteById(id);
    }
}
