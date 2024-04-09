package com.patika.kredinbizdeservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "credit_cards")
public class CreditCard extends Audit implements Serializable, Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fee")
    private BigDecimal fee;

    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

}
