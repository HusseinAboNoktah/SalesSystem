package com.example.salesmanagment.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="products")
public class Product  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;
    private String name ;

    @ManyToOne(fetch = FetchType.EAGER  , optional = false)
    @JsonIgnoreProperties("products")
    @JoinColumn(nullable = false)
    private Category category;
    private String description ;
    @CreationTimestamp
    private LocalDateTime creationDate;


    @JsonBackReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<SaleProduct> saleproduct;
    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Set<SaleProduct> getSaleproduct() {
        return saleproduct;
    }

    public void setSaleproduct(Set<SaleProduct> saleproduct) {
        this.saleproduct = saleproduct;
    }
}
