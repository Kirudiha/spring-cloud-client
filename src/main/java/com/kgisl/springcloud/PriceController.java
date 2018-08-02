package com.kgisl.springcloud;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1")
public class PriceController {
    private final PriceCalculator priceCalculator;

    public PriceController(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    @RequestMapping(path = "/prices", method = RequestMethod.POST)
    public ResponseEntity<PriceResponse> getPriceAmount(@RequestBody PriceRequest priceRequest) {

        return new ResponseEntity<>(
                new PriceResponse(priceCalculator.calculatePrice(priceRequest.getStart(), priceRequest.getEnd(), priceRequest.getPlan())),
                HttpStatus.OK);
    }

}