package com.hu.spring.SpringBankApp.domain.bankaccount;

import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolder;
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
    @NotNull(message = "balance cannot be null")
    private float balance;
    @NotEmpty
    @Positive
    private List<String> accountHolders;
}
