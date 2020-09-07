package com.hu.spring.SpringBankApp.domain.bankaccount;

import com.hu.spring.SpringBankApp.domain.accountholder.AccountHolder;
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
public class BankAccount implements Serializable {
    private static final long serialVersionId = 5313493413859894403L;

    @Id
    @GeneratedValue
    private long id;

    @CreatedDate
    private Date createsd;

    @NotNull(message = "IBAN cannot be null")
    @Column(length=50, nullable=false)
    private String IBAN;

    @NotNull(message = "Balance cannot be null")
    @Column(nullable=false)
    private float balance;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status cannot be null")
    @Column(length=50, nullable=false)
    private BankAccountStatus status;

    @ManyToMany
    @JoinTable(
            name = "bankaccount_accountholder",
            joinColumns = @JoinColumn(name = "bankaccount_id"),
            inverseJoinColumns = @JoinColumn(name = "accountholder_id"))
    private List<AccountHolder> accountHolders = new ArrayList<>();
}
