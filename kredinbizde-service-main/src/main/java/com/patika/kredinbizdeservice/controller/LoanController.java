package com.patika.kredinbizdeservice.controller;

import com.patika.kredinbizdeservice.model.ConsumerLoan;
import com.patika.kredinbizdeservice.model.HouseLoan;
import com.patika.kredinbizdeservice.model.Loan;
import com.patika.kredinbizdeservice.model.VehicleLoan;
import com.patika.kredinbizdeservice.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @GetMapping
    public List<Loan> getAll() {
        return loanService.getAllLoans();
    }

    @PostMapping("/house-loan")
    @ResponseStatus(HttpStatus.CREATED)
    public Loan create(@RequestBody HouseLoan loan) {
        return loanService.save(loan);
    }

    @PostMapping("/vehicle-loan")
    public Loan create(@RequestBody VehicleLoan loan) {
        return loanService.save(loan);
    }

    @PostMapping("/consumer-loan")
    public Loan create(@RequestBody ConsumerLoan loan) {
        return loanService.save(loan);
    }
}
