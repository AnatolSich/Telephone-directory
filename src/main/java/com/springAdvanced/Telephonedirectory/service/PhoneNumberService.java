package com.springAdvanced.Telephonedirectory.service;


import com.springAdvanced.Telephonedirectory.model.*;
import com.springAdvanced.Telephonedirectory.repository.CompanyRepository;
import com.springAdvanced.Telephonedirectory.repository.PhoneNumberRepository;
import com.springAdvanced.Telephonedirectory.repository.UserAccountRepository;
import com.springAdvanced.Telephonedirectory.repository.UserRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class PhoneNumberService {

    public static BigDecimal CHANGE_OPERATOR_FEE = new BigDecimal("250.00");


    final
    PhoneNumberRepository phoneNumberRepository;
    final
    UserRepository userRepository;
    final
    CompanyRepository companyRepository;
    final
    UserAccountRepository userAccountRepository;

    public PhoneNumberService(PhoneNumberRepository phoneNumberRepository, UserRepository userRepository, CompanyRepository companyRepository, UserAccountRepository userAccountRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.userAccountRepository = userAccountRepository;
    }

    public void changeMobileOperator(PhoneNumber phoneNumber, UserAccount userAccount) {

    }

    //getting all records
    public List<PhoneNumber> getAllPhoneNumber() {
        List<PhoneNumber> numbers = new ArrayList<>();
        phoneNumberRepository.findAll().forEach(number -> numbers.add(number));
        return numbers;
    }

    //getting all records
    public List<Company> getAllCompany() {
        List<Company> companies = new ArrayList<>();
        companyRepository.findAll().forEach(company -> companies.add(company));
        return companies;
    }


    //getting a specific record
    public PhoneNumber getPhoneNumberById(String number) {
        return phoneNumberRepository.findById(number).get();
    }

    public void saveOrUpdate(PhoneNumber phoneNumber) {
        phoneNumberRepository.save(phoneNumber);
    }

    //deleting a specific record
    public void delete(String number) {
        phoneNumberRepository.deleteById(number);
    }

    public void saveList(MultipartFile file) {
        List<PhoneNumber> list = parseJsonDataToList(parseToJson(file));
        for (PhoneNumber phoneNumber : list
        ) {
            PhoneNumber number = phoneNumberRepository.save(phoneNumber);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void changeMobileOperator(PhoneNumber phoneNumber) {
        AccountId accountId = new AccountId(phoneNumber.getUser().getId(), phoneNumber.getCompany().getId());
        UserAccount userAccount = userAccountRepository.findById(accountId).get();
        BigDecimal accountBalance = userAccount.getAmount().subtract(CHANGE_OPERATOR_FEE);
        if (accountBalance.compareTo(new BigDecimal("0.0")) < 0) {
            throw new RuntimeException("Insufficient funds in the account");
        }
        UserAccount newUserAccount = UserAccount.builder()
                .userId(phoneNumber.getUser().getId())
                .companyId(phoneNumber.getCompany().getId())
                .user(phoneNumber.getUser())
                .company(phoneNumber.getCompany())
                .amount(accountBalance)
                .build();
        userAccountRepository.save(newUserAccount);
        saveOrUpdate(phoneNumber);
    }


    public JSONObject parseToJson(MultipartFile file) {
        JSONObject jsonObject = new JSONObject();
        try {
            String payload = new String(file.getBytes(), StandardCharsets.UTF_8);
            JSONParser parser = new JSONParser();
            jsonObject = (JSONObject) parser.parse(payload);
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return jsonObject;
    }

    public List<PhoneNumber> parseJsonDataToList(JSONObject jsonObject) {
        JSONArray jsonArray = (JSONArray) jsonObject.get("data");
        List<PhoneNumber> list = new ArrayList<>();
        for (Object object : jsonArray) {
            JSONObject jsonObjectTemp = (JSONObject) object;
            PhoneNumber phoneNumber = this.mapJsonToPhonenumber(jsonObjectTemp);
            System.out.println("phoneNumber = " + phoneNumber);
            list.add(phoneNumber);
        }
        return list;
    }

    private PhoneNumber mapJsonToPhonenumber(JSONObject jsonObject) {
        PhoneNumberDto phoneNumberDto = PhoneNumberDto.builder()
                .number(checkJsonParams(jsonObject.get("number")))
                .userId(Long.parseLong(checkJsonParams(jsonObject.get("user_id"))))
                .companyId(Long.parseLong(checkJsonParams(jsonObject.get("company_id"))))
                .build();
        System.out.println("phoneNumberDto = " + phoneNumberDto);
        User user = userRepository.findById(phoneNumberDto.getUserId()).get();
        Company company = companyRepository.findById(phoneNumberDto.getCompanyId()).get();
        System.out.println("user = " + user.getLast_name());
        System.out.println("company = " + company.getName());
        return PhoneNumber.builder()
                .number(phoneNumberDto.getNumber())
                .user(user)
                .company(company)
                .build();
    }

    private String checkJsonParams(Object object) {
        if (object == null || object.toString().isBlank()) {
            throw new RuntimeException("Can't parse JSON");
        }
        return object.toString().replaceAll("[^0-9]", "");
    }
}