package com.example.salesmanagment.Entity;

public class SaleOrder {

    private Sale sale;
    double total;


    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }


}
