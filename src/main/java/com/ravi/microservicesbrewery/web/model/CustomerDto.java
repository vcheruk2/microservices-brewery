package com.ravi.microservicesbrewery.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/24/2020 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private UUID id;
    private String name;

}
