package com.hu.spring.SpringBankApp.service;

import com.hu.spring.SpringBankApp.domain.bankaccount.BankAccountDto;
import com.hu.spring.SpringBankApp.domain.bankaccount.BankAccountRequestBody;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBankAccountService {
    List<BankAccountDto> getAllBankAccounts();
    BankAccountDto getBankAccountById(String id);
    BankAccountDto createBankAccount(BankAccountRequestBody bankAccountRequestBody);
    boolean deleteBankAccount(String id);
    boolean blockBankAccount(String id);
}
