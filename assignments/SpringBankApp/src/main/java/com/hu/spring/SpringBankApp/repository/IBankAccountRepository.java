package com.hu.spring.SpringBankApp.repository;

import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolder;
import com.hu.spring.SpringBankApp.domain.bankaccount.BankAccount;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IBankAccountRepository {
    BankAccount create(BankAccount bankAccount, List<AccountHolder> accountHolders);
    Map<BankAccount, List<AccountHolder>> findAll();
    BankAccount findById(String id);
    List<AccountHolder> findAllAccountHoldersById(String id);
    List<BankAccount> findAllBankAccountsOfAccountHolderId(String id);
    boolean delete(String id);
    boolean block(String id);
}
