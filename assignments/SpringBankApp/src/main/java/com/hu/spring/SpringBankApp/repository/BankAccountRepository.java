package com.hu.spring.SpringBankApp.repository;

import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolder;
import com.hu.spring.SpringBankApp.domain.bankaccount.BankAccount;
import com.hu.spring.SpringBankApp.domain.bankaccount.BankAccountStatus;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BankAccountRepository implements IBankAccountRepository {
    private Map<BankAccount, List<AccountHolder>> bankAccounts;

    public BankAccountRepository() {
        this.bankAccounts = new HashMap<>();
    }

    @Override
    public BankAccount create(BankAccount bankAccount, List<AccountHolder> accountHolders) {
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setStatus(BankAccountStatus.OPEN);
        bankAccounts.put(bankAccount, accountHolders);
        return bankAccount;
    }

    @Override
    public Map<BankAccount, List<AccountHolder>> findAll() {
        return bankAccounts;
    }

    @Override
    public BankAccount findById(String id) {
        for (Map.Entry<BankAccount, List<AccountHolder>> entry: bankAccounts.entrySet()) {
            if(entry.getKey().getId().equals(id)) {
                return entry.getKey();
            }
        }

        return null;
    }

    @Override
    public List<AccountHolder> findAllAccountHoldersById(String id) {
        for (Map.Entry<BankAccount, List<AccountHolder>> entry: bankAccounts.entrySet()) {
            if(entry.getKey().getId().equals(id)) {
                return entry.getValue();
            }
        }

        return null;
    }

    @Override
    public List<BankAccount> findAllBankAccountsOfAccountHolderId(String id) {
        List<BankAccount> returnValue = new ArrayList<>();

        for (Map.Entry<BankAccount, List<AccountHolder>> entry: bankAccounts.entrySet()) {
            for (AccountHolder accountHolder:entry.getValue()) {
                if (accountHolder.getId().equals(id)) {
                    returnValue.add(entry.getKey());
                }
            }
        }

        return returnValue;
    }

    @Override
    public boolean delete(String id) {
        for (Map.Entry<BankAccount, List<AccountHolder>> entry: bankAccounts.entrySet()) {
            if(entry.getKey().getId().equals(id)) {
                bankAccounts.remove(entry.getKey());
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean block(String id) {
        for (Map.Entry<BankAccount, List<AccountHolder>> entry: bankAccounts.entrySet()) {
            if(entry.getKey().getId().equals(id)) {
                entry.getKey().setStatus(BankAccountStatus.BLOCKED);
                return true;
            }
        }

        return false;
    }
}
