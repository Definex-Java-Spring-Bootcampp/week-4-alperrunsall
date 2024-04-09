package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.model.Loan;
import com.patika.kredinbizdeservice.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService{

    private final LoanRepository loanRepository;

    public Loan save(Loan loan) {
        return loanRepository.save(loan);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public void delete(Loan loan) {

    }

}
