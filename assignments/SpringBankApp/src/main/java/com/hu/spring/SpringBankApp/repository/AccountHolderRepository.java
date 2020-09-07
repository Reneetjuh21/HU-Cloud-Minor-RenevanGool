package com.hu.spring.SpringBankApp.repository;

import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {
}
