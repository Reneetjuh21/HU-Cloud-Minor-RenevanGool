package com.hu.spring.SpringBankApp.controller;

import com.hu.spring.SpringBankApp.domain.bankaccount.BankAccountDto;
import com.hu.spring.SpringBankApp.domain.bankaccount.BankAccountRequestBody;
import com.hu.spring.SpringBankApp.service.IBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bankaccounts")
public class BankAccountController {
    private final IBankAccountService IBankAccountService;

    @Autowired
    public BankAccountController(
            IBankAccountService IBankAccountService
    ) {
        this.IBankAccountService = IBankAccountService;
    }

    @GetMapping
    public List<BankAccountDto> getAllBankAccounts() {
        return IBankAccountService.getAllBankAccounts();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BankAccountDto> getBankAccountById(@PathVariable("id") long id) {
        return new ResponseEntity<>(IBankAccountService.getBankAccountById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BankAccountDto> createBankAccount(@RequestBody BankAccountRequestBody bankAccountRequestBody) {
        return new ResponseEntity<>(IBankAccountService.createBankAccount(bankAccountRequestBody), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}/block")
    public ResponseEntity<String> blockBankAccount(@PathVariable("id") long id) {
        IBankAccountService.blockBankAccount(id);
        return new ResponseEntity<>("Updated a BankAccount", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteBankAccount(@PathVariable("id") long id) {
        IBankAccountService.deleteBankAccount(id);
        return new ResponseEntity<>("Deleted a BankAccount", HttpStatus.OK);
    }
}
