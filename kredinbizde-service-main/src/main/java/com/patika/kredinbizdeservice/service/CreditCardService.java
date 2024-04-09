package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.model.CreditCard;
import com.patika.kredinbizdeservice.repository.CampaignRepository;
import com.patika.kredinbizdeservice.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final CampaignRepository campaignRepository;

    public CreditCard save(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    public List<CreditCard> getAllCards() {
        return creditCardRepository.findAll();
    }

    public void delete(CreditCard creditCard) {

    }

}
