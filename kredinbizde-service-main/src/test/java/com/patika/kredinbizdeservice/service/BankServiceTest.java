package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.model.Bank;
import com.patika.kredinbizdeservice.model.CreditCard;
import com.patika.kredinbizdeservice.repository.BankRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankServiceTest {

    @Mock
    private BankRepository bankRepository;

    @InjectMocks
    private BankService bankService;

    @Test
    void should_create_bank_successfully() {
        Bank bank = prepareBank1();
        when(bankRepository.save(any(Bank.class))).thenReturn(bank);

        Bank savedBank = bankService.save(bank);

        assertNotNull(savedBank);
        assertEquals(bank, savedBank);
    }

    @Test
    void should_return_by_bank_name() {
        String bankName = "banka1";
        Bank bank = prepareBank1();
        when(bankRepository.findByName(bankName)).thenReturn(Optional.of(bank));

        Bank foundBank = bankService.getByName(bankName);

        assertNotNull(foundBank);
        assertEquals(bank, foundBank);
    }

    @Test
    void getByName_BankNotFound() {
        String bankName = "Nonexistent Bank";
        when(bankRepository.findByName(bankName)).thenReturn(Optional.empty());

        Bank foundBank = bankService.getByName(bankName);

        assertNull(foundBank);
    }

    @Test
    void getAllBanks() {
        Bank bank1 = prepareBank1();
        Bank bank2 = prepareBank2();

        when(bankRepository.findAll()).thenReturn(Arrays.asList(bank1, bank2));

        List<Bank> banks = bankService.getAllBanks();

        assertNotNull(banks);
        assertEquals(2, banks.size());
        assertTrue(banks.contains(bank1));
        assertTrue(banks.contains(bank2));
    }

    @Test
    void getCreditCards() {
        Bank bank = prepareBank1();
        List<CreditCard> creditCards = Arrays.asList(prepareCreditCard1(),prepareCreditCard2());
        bank.setCreditCards(creditCards);

        List<CreditCard> retrievedCreditCards = bankService.getCreditCards(bank);

        assertNotNull(retrievedCreditCards);
        assertEquals(creditCards, retrievedCreditCards);
    }

    private CreditCard prepareCreditCard1(){
        CreditCard creditCard = new CreditCard();
        creditCard.setBank(prepareBank1());
        creditCard.setFee(BigDecimal.valueOf(50));
        return creditCard;
    }
    private CreditCard prepareCreditCard2(){
        CreditCard creditCard = new CreditCard();
        creditCard.setBank(prepareBank2());
        creditCard.setFee(BigDecimal.valueOf(50));
        return creditCard;
    }
    private Bank prepareBank1(){
        Bank bank = new Bank();
        bank.setName("test1");
        return bank;
    }
    private Bank prepareBank2(){
        Bank bank = new Bank();
        bank.setName("test2");
        return bank;
    }
}
