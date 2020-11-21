package com.springAdvanced.Telephonedirectory.controller;


import com.springAdvanced.Telephonedirectory.model.PhoneNumber;
import com.springAdvanced.Telephonedirectory.service.PhoneNumberService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//creating RestController
@RestController
public class PhoneNumberController {
    final
    PhoneNumberService phoneNumberService;

    public PhoneNumberController(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    //creating a get mapping that retrieves all the phoneNumber from the database
    @GetMapping("/phoneNumber")
    private List<PhoneNumber> getAllPhoneNumber() {
        return phoneNumberService.getAllPhoneNumber();
    }

    //creating a get mapping that retrieves a specific phoneNumber
    @GetMapping("/phoneNumber/{id}")
    private PhoneNumber getPhoneNumber(@PathVariable("id") int id) {
        return phoneNumberService.getPhoneNumberById(id);
    }

    //creating a delete mapping that deletes a specific phoneNumber
    @DeleteMapping("/phoneNumber/{id}")
    private void deletePhoneNumber(@PathVariable("id") int id) {
        phoneNumberService.delete(id);
    }

    //creating post mapping that post the phoneNumber detail in the database
    @PostMapping("/phoneNumber")
    private int savePhoneNumber(@RequestBody PhoneNumber phoneNumber) {
        phoneNumberService.saveOrUpdate(phoneNumber);
        return phoneNumber.getId();
    }

    @RequestMapping(value = "/phoneNumbers", method = RequestMethod.GET)
    public String init(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("numberList", phoneNumberService.getAllPhoneNumber());
        return "index";
    }
}
