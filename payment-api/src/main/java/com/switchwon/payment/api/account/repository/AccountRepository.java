package com.switchwon.payment.api.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.switchwon.payment.domain.account.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByUserId(long userId);
}
