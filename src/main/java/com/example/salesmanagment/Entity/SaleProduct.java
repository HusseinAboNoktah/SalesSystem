package com.example.salesmanagment.Entity;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name = "sale_product")
public class SaleProduct implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @ManyToOne
        @JoinColumn(name="product_id")
        private Product product;

        @ManyToOne
        @JoinColumn(name="sale_id")
        private Sale sale;
        private  double quantity;
        private  double price;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public Sale getSale() {
            return sale;
        }

        public void setSale(Sale sale) {
            this.sale = sale;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
}
