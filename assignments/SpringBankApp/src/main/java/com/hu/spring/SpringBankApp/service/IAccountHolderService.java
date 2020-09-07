package com.hu.spring.SpringBankApp.service;

import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolderDto;
import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolderRequestBody;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAccountHolderService {
    List<AccountHolderDto> getAllAccountHolders();
    AccountHolderDto getAccountHolderById(long id);
    AccountHolderDto createAccountHolder(AccountHolderRequestBody accountHolderDto);
    boolean deleteAccountHolder(long id);
}
