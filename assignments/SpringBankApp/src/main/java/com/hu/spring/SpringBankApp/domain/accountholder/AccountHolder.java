package com.hu.spring.SpringBankApp.domain.accountholder;

import com.hu.spring.SpringBankApp.domain.bankaccount.BankAccount;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class AccountHolder implements Serializable {
    private static final long serialVersionId = 5313853413859894403L;

    @Id
    @GeneratedValue
    private long id;

    @CreatedDate
    private Date created;

    @NotNull(message = "Name cannot be null")
    @Column(length=50, nullable=false)
    private String name;

    @NotNull(message = "Email cannot be null")
    @Column(length=50, nullable=false)
    private String email;

    @ManyToMany(mappedBy = "accountHolders")
    private List<BankAccount> bankAccounts = new ArrayList<>();
}
