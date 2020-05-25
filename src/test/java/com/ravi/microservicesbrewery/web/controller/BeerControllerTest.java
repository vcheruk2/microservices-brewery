package com.ravi.microservicesbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ravi.microservicesbrewery.web.model.BeerDto;
import com.ravi.microservicesbrewery.web.services.BeerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @MockBean
    BeerService beerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    BeerDto beerDto;

    String uuid = "ba970b42-91d0-4cee-9e07-b921ff29ce4e";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        beerDto = BeerDto.builder()
                    .beerName("test beer")
                    .beerStyle("test beer style")
                    .id(UUID.fromString(uuid))
                    .build();
    }

    @Test
    void getBeer() throws Exception {
        // given
        when(beerService.getBeerById(any())).thenReturn(beerDto);

        // when
        mockMvc.perform(get(BeerController.BASE_URL + "/" + uuid)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(beerDto.getId().toString())))
                .andExpect(jsonPath("$.beerName", is(beerDto.getBeerName())))
                .andExpect(jsonPath("$.beerStyle", is(beerDto.getBeerStyle())));
    }

    @Test
    void createBeer() throws Exception {
        given(beerService.createBeer(any(BeerDto.class))).willReturn(beerDto);

        BeerDto postDto = BeerDto.builder()
                                    .id(null)
                                    .beerName(beerDto.getBeerName())
                                    .beerStyle(beerDto.getBeerStyle())
                                    .upc(beerDto.getUpc())
                                    .build();

        BeerDto savedDto = beerService.createBeer(postDto);

        String beerDTOJson = objectMapper.writeValueAsString(savedDto);

        mockMvc.perform(post(BeerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(beerDTOJson))
                .andExpect(status().isCreated());
    }

    @Test
    void handleUpdate() throws Exception {
        String beerDTOJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put(BeerController.BASE_URL + "/" + beerDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(beerDTOJson))
                .andExpect(status().isNoContent());

        then(beerService).should().updateBeer(any(), any());
    }
}