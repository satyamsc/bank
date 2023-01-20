package com.whitbox.bank.controller;

import com.whitbox.bank.dto.AccountRequest;
import com.whitbox.bank.dto.AccountResponse;
import com.whitbox.bank.dto.TransactionsRequest;
import com.whitbox.bank.dto.TransactionsResponse;
import com.whitbox.bank.service.AccountService;
import com.whitbox.bank.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  private final AccountService accountService;
  private final TransactionsService transactionsService;

  @Autowired
  public AccountController(AccountService accountService,TransactionsService transactionsService) {
    this.accountService = accountService;
    this.transactionsService = transactionsService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AccountResponse openAccount(@RequestBody AccountRequest request) {
    return accountService.openAccount(request);
  }

  @GetMapping("/{id}")
  public AccountResponse findByAccount(@PathVariable UUID id) {
    return accountService.findByAccountId(id);
  }


  @GetMapping("/{id}/transaction/{date}")
  public TransactionsResponse findByAccountAndTransactions(@PathVariable UUID id, @PathVariable LocalDate date) {
    return transactionsService.findByTransactions(id, date);
  }

  @PostMapping("/transaction")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void transfer(@RequestBody TransactionsRequest request) {
     transactionsService.transfer(request);
  }
}