package com.whitbox.bank.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
@Data
@JsonIgnoreProperties
public class AccountResponse {
    private UUID accountId;
    private BigDecimal initialDeposit;
    private BigDecimal creditLine;
    private BigDecimal balance;

}
