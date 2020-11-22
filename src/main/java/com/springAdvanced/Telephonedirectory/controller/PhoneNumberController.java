package com.springAdvanced.Telephonedirectory.controller;


import com.springAdvanced.Telephonedirectory.model.PhoneNumber;
import com.springAdvanced.Telephonedirectory.repository.PhoneNumberRepository;
import com.springAdvanced.Telephonedirectory.repository.UserRepository;
import com.springAdvanced.Telephonedirectory.service.PhoneNumberService;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//creating RestController
@RestController
public class PhoneNumberController {

    final
    PhoneNumberService phoneNumberService;
    final
    UserRepository userRepository;
    final
    PhoneNumberRepository phoneNumberRepository;

    public PhoneNumberController(PhoneNumberService phoneNumberService, UserRepository userRepository, PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberService = phoneNumberService;
        this.userRepository = userRepository;
        this.phoneNumberRepository = phoneNumberRepository;
    }

    //creating a get mapping that retrieves all the phoneNumber from the database
    @GetMapping("/phoneNumber")
    private List<PhoneNumber> getAllPhoneNumber() {
        return phoneNumberService.getAllPhoneNumber();
    }

    //creating a get mapping that retrieves a specific phoneNumber
    @GetMapping("/phoneNumber/{number}")
    private PhoneNumber getPhoneNumber(@PathVariable("number") String number) {
        return phoneNumberService.getPhoneNumberById(number);
    }

    //creating a delete mapping that deletes a specific phoneNumber
    @DeleteMapping("/phoneNumber/{number}")
    private void deletePhoneNumber(@PathVariable("number") String number) {
        phoneNumberService.delete(number);
    }

    //creating post mapping that post the phoneNumber detail in the database
    @PostMapping("/phoneNumber")
    private String savePhoneNumber(@RequestBody PhoneNumber phoneNumber) {
        phoneNumberService.saveOrUpdate(phoneNumber);
        return phoneNumber.getNumber();
    }

/*    @RequestMapping(value = "/phoneNumbers", method = RequestMethod.GET)
    public String init(@ModelAttribute("model") ModelMap model) {
        List<PhoneNumber> list = phoneNumberService.getAllPhoneNumber();
        System.out.println("SIZE = " + list.size());
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(user));
        System.out.println("SIZE USERS = " + users.size());
        model.addAttribute("numberList", list);
        return "index";
    }*/

    @GetMapping(value = "/users")
    public ModelAndView users(@ModelAttribute("model") ModelMap model) {
        var users = userRepository.findAll();
        var params = new HashMap<String, Object>();
        params.put("users", users);
        return new ModelAndView("users", params);
    }

    @GetMapping(value = "/")
    public ModelAndView phoneNumbers(@ModelAttribute("model") ModelMap model) {
        var list = phoneNumberRepository.findAll();
        //  System.out.println("SIZE = " + list.size());
        var params = new HashMap<String, Object>();
        params.put("phoneNumbers", list);
        return new ModelAndView("phoneNumbers", params);
    }

    @PostMapping("/upload")
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
            try {
                phoneNumberService.saveList(file);
            } catch (Exception e) {
                throw new RuntimeException("Could not store data of the file. Error: " + e.getMessage());
            }
        var list = phoneNumberRepository.findAll();
        //  System.out.println("SIZE = " + list.size());
        var params = new HashMap<String, Object>();
        params.put("phoneNumbers", list);
        return new ModelAndView("phoneNumbers", params);
    }

    @RequestMapping("/upload_file")
    public ModelAndView forwardUpload(Model model) {
        var params = new HashMap<String, Object>();
        return new ModelAndView("upload_file", params);
    }

    @GetMapping(value = "/download", headers = "Accept=application/pdf")
    public ModelAndView downloadForm() {
        var list = phoneNumberRepository.findAll();
        var params = new HashMap<String, Object>();
        params.put("phoneNumbers", list);
        params.put("mode", "pdf");
        // return a view which will be resolved by an excel view resolver
        return new ModelAndView("simpleFormPDF", params);

    }
}
