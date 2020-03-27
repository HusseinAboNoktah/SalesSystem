package com.example.salesmanagment.Repository;

import com.example.salesmanagment.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client , Long> {
}
