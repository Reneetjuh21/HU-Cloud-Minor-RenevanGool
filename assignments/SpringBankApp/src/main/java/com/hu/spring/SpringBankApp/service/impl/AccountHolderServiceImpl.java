package com.hu.spring.SpringBankApp.service.impl;

import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolder;
import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolderDto;
import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolderRequestBody;
import com.hu.spring.SpringBankApp.repository.IAccountHolderRepository;
import com.hu.spring.SpringBankApp.repository.IBankAccountRepository;
import com.hu.spring.SpringBankApp.service.IAccountHolderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AccountHolderServiceImpl implements IAccountHolderService {

    private ModelMapper modelMapper;

    private IAccountHolderRepository accountHolderRepository;

    private IBankAccountRepository bankAccountRepository;

    @Autowired
    public AccountHolderServiceImpl(IAccountHolderRepository accountHolderRepository, IBankAccountRepository bankAccountRepository) {
        this.accountHolderRepository = accountHolderRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<AccountHolderDto> getAllAccountHolders() {
        Map<String, AccountHolder> map = this.accountHolderRepository.findAll();
        List<AccountHolderDto> returnValue = new ArrayList<>();

        for (Map.Entry<String, AccountHolder> entry : map.entrySet()) {
            AccountHolderDto accountHolderDto = modelMapper.map(entry.getValue(), AccountHolderDto.class);
            accountHolderDto.setBankAccountList(
                    bankAccountRepository.findAllBankAccountsOfAccountHolderId(
                            accountHolderDto.getId()
                    )
            );
            returnValue.add(accountHolderDto);
        }

        return returnValue;
    }

    @Override
    public AccountHolderDto getAccountHolderById(String id) {
        AccountHolder accountHolder = this.accountHolderRepository.findById(id);

        if (accountHolder == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "AccountHolder Not Found");
        }

        AccountHolderDto accountHolderDto = modelMapper.map(accountHolder, AccountHolderDto.class);
        accountHolderDto.setBankAccountList(
                bankAccountRepository.findAllBankAccountsOfAccountHolderId(
                        accountHolderDto.getId()
                )
        );

        return accountHolderDto;
    }

    @Override
    public AccountHolderDto createAccountHolder(AccountHolderRequestBody accountHolderRequestBody) {
        AccountHolder accountHolder = modelMapper.map(accountHolderRequestBody, AccountHolder.class);
        accountHolder = this.accountHolderRepository.create(accountHolder);
        return modelMapper.map(accountHolder, AccountHolderDto.class);
    }

    @Override
    public boolean deleteAccountHolder(String id) {
        AccountHolder accountHolder = this.accountHolderRepository.findById(id);

        if (accountHolder == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "AccountHolder Not Found");
        }

        this.accountHolderRepository.delete(id);
        return true;
    }
}
