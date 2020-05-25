package com.ravi.microservicesbrewery.web.services.v2;

import com.ravi.microservicesbrewery.web.model.v2.BeerDtoV2;

import java.util.UUID;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/24/2020 */
public interface BeerServiceV2 {
    BeerDtoV2 getBeerById(UUID id);
    BeerDtoV2 createBeer(BeerDtoV2 beerDto);
    void updateBeer(UUID beerId, BeerDtoV2 beerDto);
    void deleteById(UUID beerId);
}
