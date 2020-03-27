package com.example.salesmanagment.Repository;

import com.example.salesmanagment.Entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SaleRepository extends JpaRepository<Sale , Long> {

    @Query(value = "SELECT sum(quantity * price) FROM SaleProduct where sale.id=:saleId")
    Double total(Long saleId);
}
