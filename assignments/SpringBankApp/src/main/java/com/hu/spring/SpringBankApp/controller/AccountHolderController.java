package com.hu.spring.SpringBankApp.controller;

import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolderDto;
import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolderRequestBody;
import com.hu.spring.SpringBankApp.service.IAccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountholders")
public class AccountHolderController {
    private IAccountHolderService accountHolderService;

    @Autowired
    public AccountHolderController(
            IAccountHolderService accountHolderService
    ) {
        this.accountHolderService = accountHolderService;
    }

    @GetMapping
    public List<AccountHolderDto> getAllAccountHolders() {
        return accountHolderService.getAllAccountHolders();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountHolderDto> getAccountHolderById(@PathVariable("id") long id) {
        AccountHolderDto accountHolderDto = accountHolderService.getAccountHolderById(id);
        return new ResponseEntity<>(accountHolderDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountHolderDto> createAccountHolder(@RequestBody AccountHolderRequestBody requestBody) {
        AccountHolderDto accountHolderDto = accountHolderService.createAccountHolder(requestBody);
        return new ResponseEntity<>(accountHolderDto, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAccountHolder(@PathVariable("id") long id) {
        accountHolderService.deleteAccountHolder(id);
        return new ResponseEntity<>("Deleted a AccountHolder", HttpStatus.OK);
    }
}
