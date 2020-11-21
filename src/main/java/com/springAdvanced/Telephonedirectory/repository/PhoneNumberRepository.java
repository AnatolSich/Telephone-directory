package com.springAdvanced.Telephonedirectory.repository;

import com.springAdvanced.Telephonedirectory.model.PhoneNumber;
import org.springframework.data.repository.CrudRepository;

public interface PhoneNumberRepository extends CrudRepository<PhoneNumber, Integer>
{
}
