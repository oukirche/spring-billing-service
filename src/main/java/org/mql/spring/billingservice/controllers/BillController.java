package org.mql.spring.billingservice.controllers;

import org.mql.spring.billingservice.dao.BillRepository;
import org.mql.spring.billingservice.dao.ProductItemRepository;
import org.mql.spring.billingservice.entities.Bill;
import org.mql.spring.billingservice.entities.Customer;
import org.mql.spring.billingservice.entities.ProductItem;
import org.mql.spring.billingservice.service.CustomerService;
import org.mql.spring.billingservice.service.InventaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class BillController {

    @Autowired
    CustomerService customerService;
    @Autowired
    InventaryService inventaryService;
    @Autowired
    ProductItemRepository productItemRepository;
    @Autowired
    BillRepository billRepository;

    @GetMapping("bills/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id){
        Bill bill = billRepository.findById(id).get();
        Customer customer = customerService.findCustomerById(bill.getCustomerId());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(inventaryService.findProductById(productItem.getProductId()));
        });
        return bill;

    }
}
