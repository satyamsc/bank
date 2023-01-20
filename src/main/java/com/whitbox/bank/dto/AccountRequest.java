package com.whitbox.bank.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class AccountRequest {
    private UUID accountId;
    private BigDecimal initialDeposit;
    private BigDecimal creditLine;
}