package com.whitbox.bank.repository;

import com.whitbox.bank.model.Account;
import com.whitbox.bank.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
    //List<Transactions> findByAccountAndTransactionDate(Account account, LocalDate date);
    // Query methods go here, for example
}
