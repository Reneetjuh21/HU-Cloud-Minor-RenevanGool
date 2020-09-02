package com.hu.spring.SpringBankApp.repository;

import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IAccountHolderRepository {
    AccountHolder create(AccountHolder accountHolder);
    Map<String, AccountHolder> findAll();
    AccountHolder findById(String id);
    List<AccountHolder> findAllByIds(List<String> ids);
    boolean delete(String id);
}
