package com.ravi.microservicesbrewery.web.services;

import com.ravi.microservicesbrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/24/2020 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto getCustomerById(UUID id) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Customer Name")
                .build();
    }
}
