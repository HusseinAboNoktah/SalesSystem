package com.example.salesmanagment.Services;

import com.example.salesmanagment.Entity.Product;
import com.example.salesmanagment.Entity.Sale;
import com.example.salesmanagment.Entity.SaleOrder;
import com.example.salesmanagment.Repository.ProductRepository;
import com.example.salesmanagment.Repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SaleService {

    @Autowired
    private SaleRepository saleRepository ;

    public List<Sale> getAll(){
        return  saleRepository.findAll();
    }

    public void save(Sale sale){

        saleRepository.save(sale);
    }
    public Sale get(long id){
        return saleRepository.findById(id).get();
    }


    public Sale update(Sale sale){
        Sale existSale  = saleRepository.findById(sale.getId()).orElse(null);

        assert existSale != null;
        existSale.setClient(sale.getClient());
        existSale.setSeller(sale.getSeller());

        return saleRepository.save(existSale);
    }
    public double sumPrices(Long saleId) {
        Double total =saleRepository.total(saleId);
        if(total !=null)
        {
            return  total;
        }
        return (double) 0;
    }
    public List<SaleOrder> getAllSalesOrder() {
        List<Sale> allSales = getAll();
        List <SaleOrder> allSaleOrder= new ArrayList<SaleOrder>() ;

        for (Sale s: allSales ) {
            SaleOrder saleOrder = new SaleOrder() ;
            saleOrder.setSale(s);
            saleOrder.setTotal(sumPrices(s.getId()));
            allSaleOrder.add(saleOrder);
        }
        return allSaleOrder;
    }


    public SaleOrder getSaleOrder(long id){
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setSale(get(id));
        saleOrder.setTotal(sumPrices(id));
        return saleOrder;
    }
}
