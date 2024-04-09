package com.patika.kredinbizdeservice.controller;

import com.patika.kredinbizdeservice.model.CreditCard;
import com.patika.kredinbizdeservice.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/credit-cards")
@RequiredArgsConstructor
public class CreditCardController {
    private final CreditCardService creditCardService;

    @GetMapping
    public List<CreditCard> getAll() {
        return creditCardService.getAllCards();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard create(@RequestBody CreditCard creditCard) {
        return creditCardService.save(creditCard);
    }
}
