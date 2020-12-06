package com.springAdvanced.Telephonedirectory.repository;

import com.springAdvanced.Telephonedirectory.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
}
