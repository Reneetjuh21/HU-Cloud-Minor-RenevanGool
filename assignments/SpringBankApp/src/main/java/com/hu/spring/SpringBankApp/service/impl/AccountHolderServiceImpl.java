package com.hu.spring.SpringBankApp.service.impl;

import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolder;
import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolderDto;
import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolderRequestBody;
import com.hu.spring.SpringBankApp.repository.AccountHolderRepository;
import com.hu.spring.SpringBankApp.service.IAccountHolderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountHolderServiceImpl implements IAccountHolderService {

    private ModelMapper modelMapper;

    private AccountHolderRepository accountHolderRepository;

    @Autowired
    public AccountHolderServiceImpl(AccountHolderRepository accountHolderRepository) {
        this.accountHolderRepository = accountHolderRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<AccountHolderDto> getAllAccountHolders() {
        List<AccountHolder> accountHolders = this.accountHolderRepository.findAll();
        List<AccountHolderDto> returnValue = new ArrayList<>();

        for (AccountHolder accountHolder : accountHolders) {
            AccountHolderDto accountHolderDto = modelMapper.map(accountHolder, AccountHolderDto.class);
            returnValue.add(accountHolderDto);
        }

        return returnValue;
    }

    @Override
    public AccountHolderDto getAccountHolderById(long id) {
        AccountHolder accountHolder = this.accountHolderRepository.findById(id).orElse(null);

        if (accountHolder == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "AccountHolder Not Found");
        }

        return modelMapper.map(accountHolder, AccountHolderDto.class);
    }

    @Override
    @Transactional
    public AccountHolderDto createAccountHolder(AccountHolderRequestBody accountHolderRequestBody) {
        AccountHolder accountHolder = modelMapper.map(accountHolderRequestBody, AccountHolder.class);
        accountHolder = this.accountHolderRepository.save(accountHolder);
        return modelMapper.map(accountHolder, AccountHolderDto.class);
    }

    @Override
    @Transactional
    public boolean deleteAccountHolder(long id) {
        AccountHolder accountHolder = this.accountHolderRepository.findById(id).orElse(null);

        if (accountHolder == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "AccountHolder Not Found");
        }

        this.accountHolderRepository.delete(accountHolder);
        return true;
    }
}
