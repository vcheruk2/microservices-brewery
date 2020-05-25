package com.ravi.microservicesbrewery.web.services;

import com.ravi.microservicesbrewery.web.model.CustomerDto;

import java.util.UUID;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/24/2020 */
public interface CustomerService {
    CustomerDto getCustomerById(UUID id);
    CustomerDto createCustomer(CustomerDto customerDto);
    void updateCustomerById(UUID id, CustomerDto customerDto);
    void deleteById(UUID customerId);
}
