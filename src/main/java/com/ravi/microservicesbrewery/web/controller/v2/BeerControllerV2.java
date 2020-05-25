package com.ravi.microservicesbrewery.web.controller.v2;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/24/2020 */

import com.ravi.microservicesbrewery.web.model.v2.BeerDtoV2;
import com.ravi.microservicesbrewery.web.services.v2.BeerServiceV2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(BeerControllerV2.BASE_URL)
public class BeerControllerV2 {

    private final BeerServiceV2 beerService;
    public static final String BASE_URL = "/api/v2/beer";

    public BeerControllerV2(BeerServiceV2 beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDtoV2> getBeer(@PathVariable("beerId") UUID beerId){
        return new ResponseEntity<>( beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createBeer(@RequestBody BeerDtoV2 beerDto){

        BeerDtoV2 savedDto = beerService.createBeer(beerDto);

        HttpHeaders headers = new HttpHeaders();
        // TODO: Add hostname to url
        headers.add("Location", "/api/v1/beer/" + savedDto.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity handleUpdate(@PathVariable("beerId") UUID beerId, @RequestBody BeerDtoV2 beerDto){
        beerService.updateBeer(beerId, beerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("beerId") UUID beerId){
        beerService.deleteById(beerId);
    }
}

