package com.hu.spring.SpringBankApp.domain.bankaccount;

import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolder;
import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolderDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BankAccountRequestBody {

    @NotNull(message = "IBAN cannot be null")
    private String IBAN;
    @NotNull(message = "Balance cannot be null")
    private float balance;
    @NotNull(message = "Status cannot be null")
    private BankAccountStatus status;
    @NotEmpty
    @Positive
    private List<AccountHolderDto> accountHolders;
}
