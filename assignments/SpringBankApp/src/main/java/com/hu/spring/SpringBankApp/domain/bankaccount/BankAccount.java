package com.hu.spring.SpringBankApp.domain.bankaccount;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BankAccount {
    private String id;
    private String IBAN;
    private float balance;
    private BankAccountStatus status;
}
