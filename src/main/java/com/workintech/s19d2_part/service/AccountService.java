package com.workintech.s19d2_part.service;

import com.workintech.s19d2_part.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account save(Account account);


}
