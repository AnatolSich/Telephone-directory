package com.springAdvanced.Telephonedirectory.service;


import com.springAdvanced.Telephonedirectory.model.User;
import com.springAdvanced.Telephonedirectory.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {


    final
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //getting all records
    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }

    public List<User> getAllUserLimit(Long limit, String sort) {
        Sort.Direction direction = setDirection(sort);
        Page<User> page = userRepository
                .findAll(PageRequest.of(0, Math.toIntExact(limit), Sort.by(direction, "id")));
        return page.get().collect(Collectors.toList());
    }

    private Sort.Direction setDirection(String sort) {
        if ("asc".equalsIgnoreCase(sort)) {
            return Sort.Direction.ASC;
        } else return Sort.Direction.DESC;
    }
}