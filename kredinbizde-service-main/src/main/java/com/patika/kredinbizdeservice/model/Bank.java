package com.patika.kredinbizdeservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "banks")
public class Bank{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
    private List<CreditCard> creditCards;
}
