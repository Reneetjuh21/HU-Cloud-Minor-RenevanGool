package com.hu.spring.SpringBankApp.domain.accountholder;

import com.hu.spring.SpringBankApp.domain.bankaccount.BankAccount;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AccountHolderDto {
    private String id;
    private String name;
    private String email;
    private List<BankAccount> bankAccountList;
}
