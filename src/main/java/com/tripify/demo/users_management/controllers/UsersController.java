package com.tripify.demo.users_management.controllers;

import com.tripify.demo.users_management.model.Employee;
import com.tripify.demo.users_management.model.User;
import com.tripify.demo.users_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tripify/users/")
public class UsersController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private EmployeeRepository repository2;

    @GetMapping("/")
    public void addUserTest(){
        User user = new User();
        user.setFirstName("f");
        user.setLastName("l");
        User userResult = repository.save(user);
        Employee employee = new Employee();
        employee.setUserId(userResult);
        repository2.save(employee);
    }

}
