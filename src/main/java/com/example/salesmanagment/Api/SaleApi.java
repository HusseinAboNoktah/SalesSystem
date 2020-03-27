package com.example.salesmanagment.Api;

import com.example.salesmanagment.Entity.Sale;
import com.example.salesmanagment.Entity.SaleOrder;
import com.example.salesmanagment.Entity.SaleProduct;
import com.example.salesmanagment.Entity.Seller;
import com.example.salesmanagment.SalesManagmentApplication;
import com.example.salesmanagment.Services.SaleProductService;
import com.example.salesmanagment.Services.SaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("Api/V1/sales")
public class SaleApi {
    Logger logger = LoggerFactory.getLogger(SalesManagmentApplication.class);
    @Autowired
    private SaleService saleService;

    @Autowired
    SaleProductService saleProductService;

    @GetMapping()
    public List<SaleOrder> getAll() {
        return saleService.getAllSalesOrder();
    }

    @PostMapping("/save")
    public String save(@Valid @RequestBody Sale sale) {

        try {
            logger.info("Create  Sale With ID" + sale.getId() + " at " + LocalDateTime.now() + " Using Api ");
            saleService.save(sale);
            return " Success Created";
        } catch (Exception e) {
            return " Success Created";
        }
    }

    @GetMapping("/{id}")
    public SaleOrder getSaleById(@PathVariable(value = "id") Long id) {
        return saleService.getSaleOrder(id);
    }

    @PutMapping("/update")
    public Sale update(@RequestBody Sale sale) {
        try {
            logger.info("Create  Sale With ID" + sale.getId() + " at " + LocalDateTime.now() + " Using Api ");
            return saleService.update(sale);


        } catch (NoSuchElementException ignored) {
            return null;
        }
    }

    @GetMapping("{id}/products")
    public Set<SaleProduct> getAllSaleProduct(@PathVariable(value = "id") Long id) {
        Sale sale = saleService.get(id);
        return sale.getSaleProducts();
    }

    @PostMapping("/products")
    public String saveSaleProduct(@Valid @RequestBody SaleProduct saleProduct) {
        LocalDateTime localDateTime = LocalDateTime.now();

        try {
            logger.info("CReate SaleProduct Sale With ID" + saleProduct.getId() + " at " + localDateTime + " Using Api ");

            saleProductService.save(saleProduct);
            return "Success Created";

        } catch (Exception e) {
            return "Failed Created";
        }
    }

    @GetMapping("/product/{id}")
    public SaleProduct getSaleProductById(@PathVariable(value = "id") Long id) {
        return saleProductService.get(id);
    }

    @PutMapping("/product/update")
    public String saleProductUpdate(@RequestBody SaleProduct saleProduct) {
        try {

            saleProductService.save(saleProduct);
            logger.info("Save Sale Product Sale With ID" + saleProduct.getId() + " at " + LocalDateTime.now() + " Using Api ");
            return "Save OK";

        } catch (NoSuchElementException ignored) {
            return "Failed Save";
        }
    }

}
