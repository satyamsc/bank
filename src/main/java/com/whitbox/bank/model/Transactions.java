package com.whitbox.bank.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    @Column(nullable = false)
    private BigDecimal transactionAmount;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;
    @Column(nullable = false)
    private LocalDate transactionDate;
    @Column(nullable = false)
    private UUID fromAccount;
    @Column(nullable = false)
    private UUID toAccount;
    @ManyToOne
    @JoinColumn(name = "account-id")
    private Account account;
}
