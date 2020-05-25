package com.ravi.microservicesbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ravi.microservicesbrewery.web.model.CustomerDto;
import com.ravi.microservicesbrewery.web.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    CustomerDto customerDto;

    public static final String uuid = "ba970b42-91d0-4cee-9e07-b921ff29ce4e";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerDto = CustomerDto.builder()
                        .id(UUID.randomUUID())
                        .name("test customer")
                        .build();
    }

    @Test
    void getCustomerById() throws Exception {
        when(customerService.getCustomerById(any())).thenReturn(customerDto);

        // when
        mockMvc.perform(get(CustomerController.BASE_URL + "/" + uuid)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(customerDto.getName())));

        // then
        then(customerService).should().getCustomerById(any());
    }

    @Test
    void createCustomer() throws Exception {
        // given
        CustomerDto givenDto = CustomerDto.builder()
                                .name("Given name")
                                .build();
        CustomerDto savedDto = CustomerDto.builder()
                                .id(UUID.randomUUID())
                                .name(givenDto.getName())
                                .build();

        when(customerService.createCustomer(any())).thenReturn(savedDto);

        String objMapperString = objectMapper.writeValueAsString(givenDto);

        // when
        mockMvc.perform(post(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objMapperString))
                .andExpect(status().isCreated());

        // then
        then(customerService).should().createCustomer(any());
    }

    @Test
    void updateCustomer() throws Exception {
        // given
        CustomerDto givenDto = CustomerDto.builder()
                .name("New name")
                .build();
        CustomerDto savedDto = CustomerDto.builder()
                .id(UUID.fromString(uuid))
                .name(givenDto.getName())
                .build();

        //when(customerService.updateCustomerById(any(), any())).thenReturn(savedDto);

        String objMapperString = objectMapper.writeValueAsString(givenDto);

        // when
        mockMvc.perform(put(CustomerController.BASE_URL + "/" + uuid)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objMapperString))
                .andExpect(status().isNoContent());
                //.andExpect(jsonPath("$.name", is(givenDto.getName())));

        // then
        then(customerService).should().updateCustomerById(any(), any());
    }

    @Test
    void deleteCustomerById() throws Exception {

        // when
        mockMvc.perform(delete(CustomerController.BASE_URL + "/" + uuid)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // then
        then(customerService).should().deleteById(any());
    }
}