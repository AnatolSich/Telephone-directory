package com.springAdvanced.Telephonedirectory.controller;

import com.springAdvanced.Telephonedirectory.model.PhoneNumber;
import com.springAdvanced.Telephonedirectory.model.User;
import com.springAdvanced.Telephonedirectory.repository.UserRepository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@RestController
public class UserController {

    final
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/users")
    public ModelAndView users(@ModelAttribute("model") ModelMap model) {
        var users = userRepository.findAll();
        var params = new HashMap<String, Object>();
        params.put("users", users);
        return new ModelAndView("users", params);
    }

    //creating post mapping that post the user detail in the database
    @PostMapping("/user")
    private ModelAndView saveUser(@RequestParam(value = "first_name") String firstName,
                                  @RequestParam(value = "last_name") String lastName) {
        System.out.println("Start saveUser");
        User user = User.builder()
                .first_name(firstName)
                .last_name(lastName)
                .build();
        User userSaved = userRepository.save(user);
        System.out.println("userSaved = " + userSaved.getId() + userSaved.getFirst_name());
        return new ModelAndView("redirect:/users");
    }

    @PostMapping("/user/edit/{id}")
    private ModelAndView updatePhoneNumber(@PathVariable("id") Long id,
                                           @RequestParam(value = "first_name") String firstName,
                                           @RequestParam(value = "last_name") String lastName) {
        User userDB = userRepository.findById(id).get();
        userDB.setFirst_name(firstName);
        userDB.setLast_name(lastName);
        userRepository.save(userDB);
        return new ModelAndView("redirect:/users");
    }

    //update a specific user
    @GetMapping("/user/edit/{id}")
    private ModelAndView updatePhoneNumber(@PathVariable("id") Long id) {
        var params = new HashMap<String, Object>();
        params.put("user", userRepository.findById(id).get());
        return new ModelAndView("edit_user", params);
    }

}
