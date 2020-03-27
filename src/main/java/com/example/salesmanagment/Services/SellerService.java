package com.example.salesmanagment.Services;

import com.example.salesmanagment.Entity.Client;


import com.example.salesmanagment.Entity.Seller;
import com.example.salesmanagment.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository ;
    public List<Seller> getAll() {
        return sellerRepository.findAll();
    }
    public void save(Seller client) {
        sellerRepository.save(client);
    }
    public Seller get(long id) {
        return sellerRepository.findById(id).get();
    }
    public void delete(long id) {
        sellerRepository.deleteById(id);
    }
    public Seller update(Seller seller){
        Seller sellerExist = sellerRepository.findById(seller.getId()).orElse(null);
        sellerExist.setName(seller.getName());
        sellerExist.setLastName(seller.getLastName());
        sellerExist.setMobile(seller.getMobile());
        return sellerRepository.save(sellerExist) ;
    }
}
