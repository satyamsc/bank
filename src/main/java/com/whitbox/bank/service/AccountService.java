package com.whitbox.bank.service;

import com.whitbox.bank.dto.AccountRequest;
import com.whitbox.bank.dto.AccountResponse;
import com.whitbox.bank.model.Account;
import com.whitbox.bank.model.TransactionType;
import com.whitbox.bank.model.Transactions;
import com.whitbox.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountResponse openAccount(AccountRequest request) {
        Account account = new Account();
        account.setAccountId(UUID.randomUUID());
        account.setInitialDeposit(request.getInitialDeposit());
        account.setCreditLine(request.getCreditLine());
        account.setBalance(request.getInitialDeposit());
        account.setCreatedDate(LocalDate.now());
        Transactions transactions = new Transactions();
        transactions.setToAccount(account.getAccountId());
        transactions.setFromAccount(account.getAccountId());
        transactions.setTransactionAmount(request.getInitialDeposit());
        transactions.setTransactionType(TransactionType.CREDIT);
        transactions.setTransactionDate(account.getCreatedDate());
        account.setTransactions(List.of(transactions));
        transactions.setAccount(account);
        account = accountRepository.save(account);
        AccountResponse response = new AccountResponse();
        response.setAccountId(account.getAccountId());
        response.setBalance(account.getBalance());
        response.setCreditLine(account.getCreditLine());
        return response;
    }
    public AccountResponse findByAccountId(UUID id) {
        Optional<Account> byAccountId = accountRepository.findByAccountId(id);
        Account account = byAccountId.orElseThrow();
        AccountResponse response = new AccountResponse();
        response.setAccountId(account.getAccountId());
        response.setBalance(account.getBalance());
        response.setCreditLine(account.getCreditLine());
        return response;
    }
}