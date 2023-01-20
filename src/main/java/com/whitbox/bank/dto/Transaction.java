package com.whitbox.bank.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.whitbox.bank.model.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
@JsonIgnoreProperties
@Data
public class Transaction {
    private Long transactionId;
    private BigDecimal transactionAmount;
    private TransactionType transactionType;
    private LocalDate transactionDate;
    private UUID fromAccount;
    private UUID toAccount;
    private AccountRequest accountRequest;
}
