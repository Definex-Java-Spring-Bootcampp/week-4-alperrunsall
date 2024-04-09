package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.exceptions.KredinbizdeException;
import com.patika.kredinbizdeservice.model.Bank;
import com.patika.kredinbizdeservice.model.Campaign;
import com.patika.kredinbizdeservice.model.CreditCard;
import com.patika.kredinbizdeservice.repository.BankRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankService {

    private final BankRepository bankRepository;

    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = KredinbizdeException.class)
    public Bank save(Bank bank) {

        Bank saved = bankRepository.save(bank);

        return saved;
    }

    @Cacheable(value = "banks", key = "#name")
    public Bank getByName(String name) {
        Optional<Bank> foundBank = bankRepository.findByName(name);

        Bank bank = null;

        if (foundBank.isPresent()) {
            bank = foundBank.get();
        }

        return bank;
    }

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }


    public List<CreditCard> getCreditCards(Bank bank) {
        return bank.getCreditCards();
    }

    public List<Campaign> getCampaignByCreditCard() {
        return null;
    }

    public void delete(Bank bank) {

    }
}
