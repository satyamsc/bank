package com.whitbox.bank.repository;

import com.whitbox.bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountId(UUID id);
    // Query methods go here, for example
}
