package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.enums.SectorType;
import com.patika.kredinbizdeservice.model.*;
import com.patika.kredinbizdeservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MockDataService implements InitializingBean {

    private final BankRepository bankRepository;
    private final CampaignRepository campaignRepository;
    private final LoanRepository loanRepository;
    private final CreditCardRepository creditCardRepository;
    private final CreditCardCampaignRepository creditCardCampaignRepository;
    private final Random random = new Random();

    @Transactional
    @Override
    public void afterPropertiesSet() throws Exception {
        List<Bank> banks = createBanks();
        List<Campaign> campaigns = createCampaigns();
        List<CreditCard> creditCards = createCreditCards(banks);

        createLoan(new VehicleLoan(),new BigDecimal("50000"), 60, 0.5,random(banks));
        createLoan(new VehicleLoan(),new BigDecimal("70000"), 48, 0.7, random(banks));
        createLoan(new VehicleLoan(),new BigDecimal("50000"), 60, 0.5,random(banks));
        createLoan(new ConsumerLoan(),new BigDecimal("50000"), 60, 0.5,random(banks));
        createLoan(new ConsumerLoan(),new BigDecimal("70000"), 48, 0.7, random(banks));
        createLoan(new ConsumerLoan(),new BigDecimal("50000"), 60, 0.5,random(banks));
        createLoan(new HouseLoan(),new BigDecimal("50000"), 60, 0.5,random(banks));
        createLoan(new HouseLoan(),new BigDecimal("70000"), 48, 0.7, random(banks));
        createLoan(new HouseLoan(),new BigDecimal("50000"), 60, 0.5,random(banks));

        createCreditCardCampaign(creditCards.get(0),campaigns.get(0));
        createCreditCardCampaign(creditCards.get(1),campaigns.get(1));
        createCreditCardCampaign(creditCards.get(2),campaigns.get(2));
    }

    private void createCreditCardCampaign(CreditCard creditCard, Campaign campaign){
        CreditCardCampaign creditCardCampaign = new CreditCardCampaign();
        creditCardCampaign.setCampaign(campaign);
        creditCardCampaign.setCreditCard(creditCard);
        creditCardCampaignRepository.save(creditCardCampaign);
    }

    private void createLoan(Loan loan, BigDecimal amount, Integer installment,Double interestRate, Bank bank){
        loan.setAmount(amount);
        loan.setInstallment(installment);
        loan.setInterestRate(interestRate);
        loan.setBank(bank);
        loanRepository.save(loan);
    }

    private List<Bank> createBanks(){
        List<Bank> bankList = new ArrayList<>(3);

        for (int i = 1; i <= 3; i++) {
            Bank bank = new Bank();
            bank.setName("Banka" + i);
            bankList.add(bank);
        }
        return bankRepository.saveAll(bankList);
    }

    private List<Campaign> createCampaigns(){
        return campaignRepository.saveAll(List.of(
                createCampaign("1","2",SectorType.MARKET),
                createCampaign("10","20",SectorType.FINANCE),
                createCampaign("100","200",SectorType.TRAVELS)
        ));

    }
    private List<CreditCard> createCreditCards(List<Bank> banks){
        return creditCardRepository.saveAll(List.of(
                createCreditCard(BigDecimal.valueOf(50L),random(banks)),
                createCreditCard(BigDecimal.valueOf(10L),random(banks)),
                createCreditCard(BigDecimal.valueOf(20L),random(banks))
        ));

    }
    private CreditCard createCreditCard(BigDecimal fee, Bank bank){
        CreditCard creditCard = new CreditCard();
        creditCard.setFee(fee);
        creditCard.setBank(bank);
        return creditCard;
    }

    private Campaign createCampaign(String title, String content, SectorType sectorType){
        Campaign campaign = new Campaign();
        campaign.setContent(content);
        campaign.setTitle(title);
        campaign.setSector(sectorType);
        campaign.setDueDate(LocalDate.now().plusMonths(2L));
        return campaign;
    }

    private <T> T random(List<T> list){
        return list.get(random.nextInt(list.size()));
    }
}
