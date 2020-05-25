package com.ravi.microservicesbrewery.web.model.v2;

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
public class BeerDtoV2 {

    private UUID id;
    private String beerName;
    private BeerStyleEnum beerStyle;
    private Long upc;

    private String date;
}
