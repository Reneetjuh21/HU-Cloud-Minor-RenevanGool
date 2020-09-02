package com.hu.spring.SpringBankApp.service.impl;

import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolder;
import com.hu.spring.SpringBankApp.domain.bankaccount.BankAccount;
import com.hu.spring.SpringBankApp.domain.bankaccount.BankAccountDto;
import com.hu.spring.SpringBankApp.domain.bankaccount.BankAccountRequestBody;
import com.hu.spring.SpringBankApp.repository.IAccountHolderRepository;
import com.hu.spring.SpringBankApp.repository.IBankAccountRepository;
import com.hu.spring.SpringBankApp.service.IBankAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BankAccountServiceImpl implements IBankAccountService {
    private ModelMapper modelMapper;

    private IBankAccountRepository bankAccountRepository;

    private IAccountHolderRepository accountHolderRepository;

    @Autowired
    public BankAccountServiceImpl(IBankAccountRepository bankAccountRepository, IAccountHolderRepository accountHolderRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountHolderRepository = accountHolderRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<BankAccountDto> getAllBankAccounts() {
        Map<BankAccount, List<AccountHolder>> map = this.bankAccountRepository.findAll();
        List<BankAccountDto> returnValue = new ArrayList<>();

        for (Map.Entry<BankAccount, List<AccountHolder>> entry : map.entrySet()) {
            BankAccountDto bankAccountDto = modelMapper.map(entry.getKey(), BankAccountDto.class);
            bankAccountDto.setAccountHolders(bankAccountRepository.findAllAccountHoldersById(bankAccountDto.getId()));

            returnValue.add(bankAccountDto);
        }

        return returnValue;
    }

    @Override
    public BankAccountDto getBankAccountById(String id) {
        BankAccount bankAccount = this.bankAccountRepository.findById(id);

        if (bankAccount == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "BankAccount Not Found");
        }

        BankAccountDto bankAccountDto = modelMapper.map(bankAccount, BankAccountDto.class);
        bankAccountDto.setAccountHolders(bankAccountRepository.findAllAccountHoldersById(bankAccountDto.getId()));

        return bankAccountDto;
    }

    @Override
    public BankAccountDto createBankAccount(BankAccountRequestBody bankAccountRequestBody) {
        List<AccountHolder> accountHolders = accountHolderRepository.findAllByIds(bankAccountRequestBody.getAccountHolders());

        BankAccount bankAccount = modelMapper.map(bankAccountRequestBody, BankAccount.class);
        bankAccount = this.bankAccountRepository.create(bankAccount, accountHolders);

        BankAccountDto bankAccountDto = modelMapper.map(bankAccount, BankAccountDto.class);
        bankAccountDto.setAccountHolders(bankAccountRepository.findAllAccountHoldersById(bankAccountDto.getId()));

        return bankAccountDto;
    }

    @Override
    public boolean deleteBankAccount(String id) {
        BankAccount bankAccount = this.bankAccountRepository.findById(id);

        if (bankAccount == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "BankAccount Not Found"
            );
        }

        this.bankAccountRepository.delete(id);
        return true;
    }

    @Override
    public boolean blockBankAccount(String id) {
        BankAccount bankAccount = this.bankAccountRepository.findById(id);

        if (bankAccount == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "BankAccount Not Found"
            );
        }

        this.bankAccountRepository.block(id);
        return true;
    }
}
