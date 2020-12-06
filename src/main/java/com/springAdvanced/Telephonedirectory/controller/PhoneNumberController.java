package com.springAdvanced.Telephonedirectory.controller;


import com.springAdvanced.Telephonedirectory.model.PhoneNumber;
import com.springAdvanced.Telephonedirectory.repository.CompanyRepository;
import com.springAdvanced.Telephonedirectory.repository.PhoneNumberRepository;
import com.springAdvanced.Telephonedirectory.repository.UserAccountRepository;
import com.springAdvanced.Telephonedirectory.repository.UserRepository;
import com.springAdvanced.Telephonedirectory.service.PhoneNumberService;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

//creating RestController
@RestController
public class PhoneNumberController {

    final
    PhoneNumberService phoneNumberService;
    final
    UserRepository userRepository;
    final
    CompanyRepository companyRepository;
    final
    PhoneNumberRepository phoneNumberRepository;
    final
    UserAccountRepository userAccountRepository;

    public PhoneNumberController(PhoneNumberService phoneNumberService, UserRepository userRepository, CompanyRepository companyRepository, PhoneNumberRepository phoneNumberRepository, UserAccountRepository userAccountRepository) {
        this.phoneNumberService = phoneNumberService;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.phoneNumberRepository = phoneNumberRepository;
        this.userAccountRepository = userAccountRepository;
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

    //update a specific phoneNumber
    @GetMapping("/phoneNumber/update/{number}")
    private ModelAndView updatePhoneNumber(@PathVariable("number") String number) {
        var params = new HashMap<String, Object>();
        params.put("phoneNumber", phoneNumberService.getPhoneNumberById(number));
        params.put("companies", phoneNumberService.getAllCompany());
        params.put("users", phoneNumberService.getAllUser());
        return new ModelAndView("update_number", params);
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

    @PostMapping("/phoneNumber/update/{number}")
    private ModelAndView updatePhoneNumber(@PathVariable("number") String number,
                                     @RequestParam(value = "userId") String userId,
                                     @RequestParam(value = "companyId") String companyId) {
        PhoneNumber phoneNumber = phoneNumberService.getPhoneNumberById(number);
        phoneNumber.setUser(userRepository.findById(Integer.parseInt(userId)).get());
        phoneNumber.setCompany(companyRepository.findById(Integer.parseInt(companyId)).get());
        phoneNumberService.saveOrUpdate(phoneNumber);
        var phoneNumbers = phoneNumberRepository.findAll();
        var accounts = userAccountRepository.findAll();
        var params = new HashMap<String, Object>();
        params.put("phoneNumbers", phoneNumbers);
        params.put("accounts", accounts);
        return new ModelAndView("index", params);
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
        var phoneNumbers = phoneNumberRepository.findAll();
        var accounts = userAccountRepository.findAll();
        var params = new HashMap<String, Object>();
        params.put("phoneNumbers", phoneNumbers);
        params.put("accounts", accounts);
        return new ModelAndView("index", params);
    }

    @PostMapping("/upload")
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            phoneNumberService.saveList(file);
        } catch (Exception e) {
            throw new RuntimeException("Could not store data of the file. Error: " + e.getMessage());
        }
        var phoneNumbers = phoneNumberRepository.findAll();
        var accounts = userAccountRepository.findAll();
        var params = new HashMap<String, Object>();
        params.put("phoneNumbers", phoneNumbers);
        params.put("accounts", accounts);
        return new ModelAndView("index", params);
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
