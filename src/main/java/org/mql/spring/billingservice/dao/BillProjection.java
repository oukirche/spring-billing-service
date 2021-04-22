package org.mql.spring.billingservice.dao;

import org.mql.spring.billingservice.entities.Bill;
import org.mql.spring.billingservice.entities.ProductItem;
import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;


@Projection(name = "fullBill", types = {Bill.class})
public interface BillProjection {
    Long getId();
    Date getBillDate();
    Long getCustomerId();
    Collection<ProductItem> getProductItems();
}
