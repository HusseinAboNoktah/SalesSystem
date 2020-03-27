package com.example.salesmanagment.Controller;

import com.example.salesmanagment.Entity.Product;
import com.example.salesmanagment.Entity.Sale;
import com.example.salesmanagment.Entity.SaleOrder;
import com.example.salesmanagment.Entity.SaleProduct;
import com.example.salesmanagment.SalesManagmentApplication;
import com.example.salesmanagment.Services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(value = "sale")
public class SaleController {
    Logger logger = LoggerFactory.getLogger(SalesManagmentApplication.class);
    @Autowired
    private SaleService saleService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private SaleProductService saleProductService;
    @Autowired
    private ProductService productService;


    @GetMapping("/index")
    String index(Model model) {
        List<SaleOrder> allSales = saleService.getAllSalesOrder();
        model.addAttribute("sales", allSales);
        return "sale/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Sale sale = new Sale();
        model.addAttribute("sale", sale);
        model.addAttribute("sellers", sellerService.getAll());
        model.addAttribute("clients", clientService.getAll());
        LocalDateTime localDateTime = LocalDateTime.now() ;
        sale.setCreationDate(localDateTime);

        return "sale/create";
    }
    @PostMapping(value = "/save")
    public String save(@ModelAttribute("sale") Sale sale) {
        LocalDateTime localDateTime = LocalDateTime.now() ;
        sale.setCreationDate(localDateTime);
        saleService.save(sale);
        logger.info("Save Sale With ID "+ sale.getId()+" With Seller ID"+ sale.getSeller().getId()
                +" By Client ID "+ sale.getClient().getId()  +"at: " + localDateTime.toString() );

        return "redirect:/sale/index";
    }


    @PostMapping(value = "/update")
    public String update(@ModelAttribute("sale") @Valid Sale sale) {
        saleService.update(sale);
        return "redirect:/sale/index";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("sale/edit");
        Sale sale = saleService.get(id);
        modelAndView.addObject("sale", sale);
        modelAndView.addObject("sellers", sellerService.getAll());
        modelAndView.addObject("clients", clientService.getAll());
        LocalDateTime localDateTime = LocalDateTime.now() ;

        logger.info("Edit Sale With ID "+ sale.getId()+" With Seller ID"+ sale.getSeller().getId()
                +" By Client ID "+ sale.getClient().getId()  +"at  " + localDateTime.toString() );
        return modelAndView;
    }

    @GetMapping("/{id}/product/index")
    String saleProductIndex(@PathVariable(name = "id") int id,Model model) {

        Sale sale =  saleService.get(id);
        model.addAttribute("allSaleProduct", sale.getSaleProducts());
        model.addAttribute("saleId", id);

        return  "sale/product/index";

    }
    @GetMapping("/{id}/product/create")
    public String saleProductCreate(@PathVariable(name = "id") int id,Model model) {
        SaleProduct saleProduct = new SaleProduct();
        saleProduct.setSale(saleService.get(id));
        model.addAttribute("saleProduct", saleProduct);
        model.addAttribute("products", productService.getAll());
        logger.info("Add Sale Product  "+ id+"at " +LocalDateTime.now() );
        return "sale/product/create";
    }
    @PostMapping(value = "/product/save")
    public String saleProductSave(@ModelAttribute("saleProduct") SaleProduct saleProduct) {

        LocalDateTime localDateTime =LocalDateTime.now() ;

        logger.info("Save Product Sale With ID" + saleProduct.getId() + " at " +localDateTime + " Using thymeleaf ");
        saleProductService.save(saleProduct);

        return  "redirect:/sale/"+saleProduct.getSale().getId()+"/product/index";

    }
    @GetMapping("/{id}/product/edit/{saleId}")
    public ModelAndView saleProductSave(@PathVariable(name = "id") int id ,@PathVariable(name = "saleId") int saleId) {
        ModelAndView modelAndView = new ModelAndView("sale/product/edit");
        SaleProduct saleProduct = saleProductService.get(saleId);
        modelAndView.addObject("saleProduct", saleProduct);
        modelAndView.addObject("products", productService.getAll());

        return modelAndView;
    }
    @PostMapping(value = "/product/update")
    public String saleProductUpdate(@ModelAttribute("saleProduct") SaleProduct saleProduct) {

        saleProductService.save(saleProduct);
        logger.info("Update Sale Product With ID " + saleProduct.getId()+ " at" +LocalDateTime.now() );
        return  "redirect:/sale/"+saleProduct.getSale().getId()+"/product";
    }

}

