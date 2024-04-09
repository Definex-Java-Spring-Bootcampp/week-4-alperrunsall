package com.patika.kredinbizdeservice.dto.request;

import com.patika.kredinbizdeservice.enums.BankType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequest {

    private String email;
    private BankType bank;
}
