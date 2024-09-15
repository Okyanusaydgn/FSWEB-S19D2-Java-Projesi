package com.workintech.s19d2_part.repository;

import com.workintech.s19d2_part.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
