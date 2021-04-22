package org.mql.spring.billingservice.entities;

import lombok.Data;

@Data
public class Customer {
    private Long customerId;
    private String name;
    private String email;
}
