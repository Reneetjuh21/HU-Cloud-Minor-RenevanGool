package com.hu.spring.SpringBankApp.domain.accountholder;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AccountHolderDto implements Serializable {
    private static final long serialVersionId = 5313493413345394403L;

    private long id;
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Email cannot be null")
    private String email;
}
