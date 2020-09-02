package com.hu.spring.SpringBankApp.domain.bankaccount;

import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolder;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BankAccountDto {
    private String id;
    private String IBAN;
    private float balance;
    private BankAccountStatus status;
    private List<AccountHolder> accountHolders;
}
