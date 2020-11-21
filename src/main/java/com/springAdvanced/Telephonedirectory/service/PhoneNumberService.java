package com.springAdvanced.Telephonedirectory.service;


import com.springAdvanced.Telephonedirectory.model.PhoneNumber;
import com.springAdvanced.Telephonedirectory.repository.PhoneNumberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//defining the business logic
@Service
public class PhoneNumberService {

    final
    PhoneNumberRepository phoneNumberRepository;

    public PhoneNumberService(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    //getting all records
    public List<PhoneNumber> getAllPhoneNumber() {
        List<PhoneNumber> numbers = new ArrayList<PhoneNumber>();
        phoneNumberRepository.findAll().forEach(number -> numbers.add(number));
        return numbers;
    }

    //getting a specific record
    public PhoneNumber getPhoneNumberById(int id) {
        return phoneNumberRepository.findById(id).get();
    }

    public void saveOrUpdate(PhoneNumber phoneNumber) {
        phoneNumberRepository.save(phoneNumber);
    }

    //deleting a specific record
    public void delete(int id) {
        phoneNumberRepository.deleteById(id);
    }
}