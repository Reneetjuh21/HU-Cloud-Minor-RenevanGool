package com.hu.spring.SpringBankApp.service.impl;

import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolder;
import com.hu.spring.SpringBankApp.domain.bankaccount.BankAccount;
import com.hu.spring.SpringBankApp.domain.bankaccount.BankAccountDto;
import com.hu.spring.SpringBankApp.domain.bankaccount.BankAccountRequestBody;
import com.hu.spring.SpringBankApp.domain.bankaccount.BankAccountStatus;
import com.hu.spring.SpringBankApp.repository.AccountHolderRepository;
import com.hu.spring.SpringBankApp.repository.BankAccountRepository;
import com.hu.spring.SpringBankApp.service.IBankAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BankAccountServiceImpl implements IBankAccountService {
    private ModelMapper modelMapper;

    private BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<BankAccountDto> getAllBankAccounts() {
        List<BankAccount> bankAccounts = this.bankAccountRepository.findAll();
        List<BankAccountDto> returnValue = new ArrayList<>();

        for (BankAccount bankaccount: bankAccounts) {
            BankAccountDto bankAccountDto = modelMapper.map(bankaccount, BankAccountDto.class);
            returnValue.add(bankAccountDto);
        }

        return returnValue;
    }

    @Override
    public BankAccountDto getBankAccountById(long id) {
        BankAccount bankAccount = this.bankAccountRepository.findById(id).orElse(null);

        if (bankAccount == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "BankAccount Not Found");
        }

        return modelMapper.map(bankAccount, BankAccountDto.class);
    }

    @Override
    @Transactional
    public BankAccountDto createBankAccount(BankAccountRequestBody bankAccountRequestBody) {
        BankAccount bankAccount = modelMapper.map(bankAccountRequestBody, BankAccount.class);
        bankAccount = this.bankAccountRepository.save(bankAccount);

        return modelMapper.map(bankAccount, BankAccountDto.class);
    }

    @Override
    @Transactional
    public boolean deleteBankAccount(long id) {
        BankAccount bankAccount = this.bankAccountRepository.findById(id).orElse(null);

        if (bankAccount == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "BankAccount Not Found"
            );
        }

        this.bankAccountRepository.delete(bankAccount);
        return true;
    }

    @Override
    @Transactional
    public boolean blockBankAccount(long id) {
        BankAccount bankAccount = this.bankAccountRepository.findById(id).orElse(null);

        if (bankAccount == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "BankAccount Not Found"
            );
        }

        bankAccount.setStatus(BankAccountStatus.BLOCKED);

        this.bankAccountRepository.save(bankAccount);
        return true;
    }
}
