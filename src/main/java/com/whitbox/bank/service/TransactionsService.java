package com.whitbox.bank.service;

import com.whitbox.bank.dto.AccountResponse;
import com.whitbox.bank.dto.Transaction;
import com.whitbox.bank.dto.TransactionsRequest;
import com.whitbox.bank.dto.TransactionsResponse;
import com.whitbox.bank.model.Account;
import com.whitbox.bank.model.Transactions;
import com.whitbox.bank.repository.AccountRepository;
import com.whitbox.bank.repository.TransactionsRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class TransactionsService {

    private final AccountRepository accountRepository;

    @Autowired
    public TransactionsService(AccountRepository accountRepository, TransactionsRepository transactionsRepository) {
        this.accountRepository = accountRepository;
    }

    public TransactionsResponse findByTransactions(UUID accountId, LocalDate date) {
        Optional<Account> byAccountId = accountRepository.findByAccountId(accountId);
        Account account = byAccountId.orElseThrow();
        return getTransactionsResponse(date, account);
    }

    private TransactionsResponse getTransactionsResponse(LocalDate date, Account account) {
        List<Transaction> list = new ArrayList<>();
        account.getTransactions().stream()
                .filter(e -> e.getTransactionDate().isEqual(date))
                .forEach(e -> {
                    Transaction transaction = new Transaction();
                    BeanUtils.copyProperties(e, transaction);
                    list.add(transaction);
                });

        TransactionsResponse transactionsResponse = new TransactionsResponse();
        transactionsResponse.setTransactions(list);
        var accountResponse = new AccountResponse();
        accountResponse.setAccountId(account.getAccountId());
        accountResponse.setBalance(account.getBalance());
        accountResponse.setCreditLine(account.getCreditLine());
        transactionsResponse.setAccountResponse(accountResponse);
        return transactionsResponse;
    }

    public void transfer(TransactionsRequest request) {
        Optional<Account> byAccountId = accountRepository.findByAccountId(
                request.getTransaction().getAccountRequest().getAccountId());
        Account account = byAccountId.orElseThrow();
        if (request.getTransaction().getTransactionAmount().compareTo(account.getBalance()) > 0) {
            BigDecimal subtracted = request.getTransaction().getTransactionAmount().subtract(account.getBalance());
            account.setCreditLine(account.getCreditLine().subtract(subtracted));
        } else {
            account.setBalance(request.getTransaction().getTransactionAmount().subtract(account.getBalance()));
        }
        Transactions transactions = new Transactions();
        BeanUtils.copyProperties(request.getTransaction(), transactions);
        transactions.setAccount(account);
        account.getTransactions().add(transactions);
        accountRepository.save(account);

    }

}
