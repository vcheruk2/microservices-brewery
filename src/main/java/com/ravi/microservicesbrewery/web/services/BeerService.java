package com.ravi.microservicesbrewery.web.services;

import com.ravi.microservicesbrewery.web.model.BeerDto;

import java.util.UUID;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/24/2020 */
public interface BeerService {
    BeerDto getBeerById(UUID id);
    BeerDto createBeer(BeerDto beerDto);
    void updateBeer(UUID beerId, BeerDto beerDto);
}
