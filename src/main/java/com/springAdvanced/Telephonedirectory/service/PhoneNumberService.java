package com.springAdvanced.Telephonedirectory.service;


import com.springAdvanced.Telephonedirectory.model.Company;
import com.springAdvanced.Telephonedirectory.model.PhoneNumber;
import com.springAdvanced.Telephonedirectory.model.PhoneNumberDto;
import com.springAdvanced.Telephonedirectory.model.User;
import com.springAdvanced.Telephonedirectory.repository.CompanyRepository;
import com.springAdvanced.Telephonedirectory.repository.PhoneNumberRepository;
import com.springAdvanced.Telephonedirectory.repository.UserRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//defining the business logic
@Service
public class PhoneNumberService {

    final
    PhoneNumberRepository phoneNumberRepository;
    final
    UserRepository userRepository;
    final
    CompanyRepository companyRepository;

    public PhoneNumberService(PhoneNumberRepository phoneNumberRepository, UserRepository userRepository, CompanyRepository companyRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    //getting all records
    public List<PhoneNumber> getAllPhoneNumber() {
        List<PhoneNumber> numbers = new ArrayList<PhoneNumber>();
        phoneNumberRepository.findAll().forEach(number -> numbers.add(number));
        return numbers;
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
            System.out.println("SAVED = " + number.getNumber());
        }
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
        System.out.println("jsonObject = " + jsonObject);
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
                .userId(Integer.parseInt(checkJsonParams(jsonObject.get("user_id"))))
                .companyId(Integer.parseInt(checkJsonParams(jsonObject.get("company_id"))))
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