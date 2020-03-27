package com.example.salesmanagment.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;
    private double total ;

    @ManyToOne
    @JoinColumn

    private Client client ;
    @ManyToOne
    @JoinColumn
    private Seller seller ;
    private LocalDateTime creationDate;
    @JsonIgnoreProperties("saleProducts")
    @JsonBackReference
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private  Set<SaleProduct> saleProducts;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
    public Set<SaleProduct> getSaleProducts() {
        return saleProducts;
    }

    public void setSaleProducts(Set<SaleProduct> saleProducts) {
        this.saleProducts = saleProducts;
    }






}
