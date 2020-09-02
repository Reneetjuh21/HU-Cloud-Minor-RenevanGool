package com.hu.spring.SpringBankApp.repository;

import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AccountHolderRepository implements IAccountHolderRepository {
    private Map<String, AccountHolder> accountHolderMap;

    public AccountHolderRepository() {
        this.accountHolderMap = new HashMap<>();
    }

    @Override
    public AccountHolder create(AccountHolder accountHolder) {
        accountHolder.setId(UUID.randomUUID().toString());
        accountHolderMap.put(accountHolder.getId(), accountHolder);
        return accountHolder;
    }

    @Override
    public Map<String, AccountHolder> findAll() {
        return accountHolderMap;
    }

    @Override
    public AccountHolder findById(String id) {
        return accountHolderMap.get(id);
    }

    @Override
    public List<AccountHolder> findAllByIds(List<String> ids) {
        List<AccountHolder> accountHolders = new ArrayList<>();

        for (Map.Entry<String, AccountHolder> entry : accountHolderMap.entrySet()) {
            for (String id: ids) {
                if (entry.getKey().equals(id)) {
                    accountHolders.add(entry.getValue());
                }
            }
        }

        return accountHolders;
    }

    @Override
    public boolean delete(String id) {
        accountHolderMap.remove(id);
        return true;
    }
}
