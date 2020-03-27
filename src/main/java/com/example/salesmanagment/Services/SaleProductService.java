package com.example.salesmanagment.Services;

import com.example.salesmanagment.Entity.SaleProduct;
import com.example.salesmanagment.Repository.SaleProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class SaleProductService  {

    @Autowired
    private SaleProductRepository saleProductRepository;

    public List<SaleProduct> getAll() {
        return saleProductRepository.findAll();
    }

    public void save(SaleProduct saleProduct) {
        saleProductRepository.save(saleProduct);
    }


    public SaleProduct get(long id) {
        return saleProductRepository.findById(id).get();
    }

    public void delete(long id) {
        saleProductRepository.deleteById(id);
    }


}
