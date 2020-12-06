package com.springAdvanced.Telephonedirectory.repository;

import com.springAdvanced.Telephonedirectory.model.AccountId;
import com.springAdvanced.Telephonedirectory.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccount, AccountId> {

}
