package com.springAdvanced.Telephonedirectory.controller;

import com.springAdvanced.Telephonedirectory.model.User;
import com.springAdvanced.Telephonedirectory.repository.UserRepository;
import com.springAdvanced.Telephonedirectory.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    final
    UserRepository userRepository;
    final
    UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public ModelAndView users(@ModelAttribute("model") ModelMap model) {
        var users = userRepository.findAll();
        var params = new HashMap<String, Object>();
        params.put("users", users);
        return new ModelAndView("users", params);
    }

    //creating post mapping that post the user detail in the database
    @PostMapping(value = "/user")
    private ModelAndView saveUser(
            //    @RequestBody  //Spring doesn't understand application/x-www-form-urlencoded as a RequestBody
            User user
    ) {
        userRepository.save(user);
        return new ModelAndView("redirect:/users");
    }

    @PostMapping("/user/edit/{id}")
    private ModelAndView editUser(@PathVariable("id") Long id,
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
    private ModelAndView editUser(@PathVariable("id") Long id) {
        var params = new HashMap<String, Object>();
        params.put("user", userRepository.findById(id).get());
        return new ModelAndView("edit_user", params);
    }

    @GetMapping(value = "/user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private User getUser(@PathVariable("id") Long id) {
        return userRepository.findById(id).get();
    }

    @GetMapping(value = "/users/{limit}/{sort}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private List<User> getListUserLimit(@PathVariable("limit") Long limit,
                                        @PathVariable("sort") String sort) {
        return userService.getAllUserLimit(limit, sort);
    }
}

