package com.example.salesmanagment.Repository;


import com.example.salesmanagment.Entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Long> {
}
