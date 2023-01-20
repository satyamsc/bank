package com.whitbox.bank.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "account")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private UUID accountId;
    @Column(nullable = false)
    private BigDecimal initialDeposit;
    @Column(nullable = false)
    private BigDecimal creditLine;
    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private LocalDate createdDate;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transactions> transactions = new ArrayList<>();
}