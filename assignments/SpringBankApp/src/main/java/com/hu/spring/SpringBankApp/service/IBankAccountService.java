package com.hu.spring.SpringBankApp.service;

import com.hu.spring.SpringBankApp.domain.bankaccount.BankAccountDto;
import com.hu.spring.SpringBankApp.domain.bankaccount.BankAccountRequestBody;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBankAccountService {
    List<BankAccountDto> getAllBankAccounts();
    BankAccountDto getBankAccountById(long id);
    BankAccountDto createBankAccount(BankAccountRequestBody bankAccountRequestBody);
    boolean deleteBankAccount(long id);
    boolean blockBankAccount(long id);
}
