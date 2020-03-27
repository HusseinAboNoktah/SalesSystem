package com.example.salesmanagment.Repository;

import com.example.salesmanagment.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product ,Long> {




}
