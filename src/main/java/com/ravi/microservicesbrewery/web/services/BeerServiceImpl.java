package com.ravi.microservicesbrewery.web.services;

import com.ravi.microservicesbrewery.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/24/2020 */
@Service
public class BeerServiceImpl implements BeerService {

    @Override
    public BeerDto getBeerById(UUID id) {
        return BeerDto.builder().id(UUID.randomUUID())
                .beerName("Beer Name")
                .beerStyle("Some Style")
                .build();
    }

    @Override
    public BeerDto createBeer(BeerDto beerDto) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .upc(beerDto.getUpc())
                .beerStyle(beerDto.getBeerStyle())
                .beerName(beerDto.getBeerName())
                .date(beerDto.getDate())
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        // TODO: Implement this function
    }
}
