package com.hu.spring.SpringBankApp.domain.bankaccount;

import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolderDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BankAccountDto implements Serializable {
    private static final long serialVersionId = 5313493413345394403L;

    private long id;
    @NotNull(message = "IBAN cannot be null")
    private String IBAN;
    @NotNull(message = "Balance cannot be null")
    private float balance;
    @NotNull(message = "Status cannot be null")
    private BankAccountStatus status;
    private List<AccountHolderDto> accountHolders;
}
