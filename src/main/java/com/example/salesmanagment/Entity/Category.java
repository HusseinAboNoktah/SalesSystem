package com.example.salesmanagment.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
@Setter @Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;
    private String name ;

    private String description ;

    @JsonIgnoreProperties("category")
    @OneToMany(mappedBy = "category" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private Set<Product> products ;

    public Category() {
    }

    public Category(String title, String description) {
        this.name = title;
        this.description = description;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
