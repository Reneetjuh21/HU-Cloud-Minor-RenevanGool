package com.hu.spring.SpringBankApp.repository;

import com.hu.spring.SpringBankApp.domain.bankaccount.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

}
