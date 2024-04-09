package com.patika.kredinbizdeservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "loans")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "loan_type", discriminatorType = DiscriminatorType.INTEGER)
public class Loan extends Audit implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "installment", nullable = false)
    private Integer installment;

    @ManyToOne
    @JoinColumn(name = "bank", nullable = false)
    private Bank bank;

    @Column(name = "interestRate", nullable = false)
    private Double interestRate;

    public Loan(BigDecimal amount, Integer installment, Double interestRate) {
        this.amount = amount;
        this.installment = installment;
        this.interestRate = interestRate;
    }

    public Loan() {

    }
}
