package com.ravi.microservicesbrewery.web.services.v2;

import com.ravi.microservicesbrewery.web.model.v2.BeerDtoV2;
import com.ravi.microservicesbrewery.web.model.v2.BeerStyleEnum;
import org.springframework.stereotype.Service;

import java.util.UUID;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/24/2020 */
@Service
public class BeerServiceV2Impl implements BeerServiceV2 {
    @Override
    public BeerDtoV2 getBeerById(UUID id) {
        return BeerDtoV2.builder().id(UUID.randomUUID())
                .beerName("Beer Name")
                .beerStyle(BeerStyleEnum.SOFT)
                .build();
    }

    @Override
    public BeerDtoV2 createBeer(BeerDtoV2 beerDto) {
        return BeerDtoV2.builder()
                .id(UUID.randomUUID())
                .upc(beerDto.getUpc())
                .beerStyle(beerDto.getBeerStyle())
                .beerName(beerDto.getBeerName())
                .date(beerDto.getDate())
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDtoV2 beerDto) {
        // TODO: Implement this function
    }

    @Override
    public void deleteById(UUID beerId) {
        // TODO: Implement this function
    }
}
