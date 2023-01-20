package com.whitbox.bank.dto;

import lombok.Data;

import java.util.List;

@Data
public class TransactionsResponse {
    List<Transaction> transactions;
    AccountResponse accountResponse;
}
