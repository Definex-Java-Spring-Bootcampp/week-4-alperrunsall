package com.patika.kredinbizdeservice.controller;

import com.patika.kredinbizdeservice.model.Bank;
import com.patika.kredinbizdeservice.model.CreditCard;
import com.patika.kredinbizdeservice.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/banks")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bank create(Bank bank){
        return bankService.save(bank);
    }

    @GetMapping
    public List<Bank> getAllBank() {
        return bankService.getAllBanks();
    }

    @GetMapping("/{name}")
    public Bank getBankByName(@PathVariable String name) {
        return bankService.getByName(name);
    }

    @GetMapping("/{name}/creditCards")
    public List<CreditCard> getCreditCardsByBankName(@PathVariable String name) {
        return bankService.getByName(name).getCreditCards();
    }

}
