package com.ravi.microservicesbrewery.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/24/2020 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BeerDto {

    private UUID id;
    private String beerName;
    private String beerStyle;
    private Long upc;

    private String date;
}
