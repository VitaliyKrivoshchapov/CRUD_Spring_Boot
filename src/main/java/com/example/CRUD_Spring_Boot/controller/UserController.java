package com.example.CRUD_Spring_Boot.controller;

import com.example.CRUD_Spring_Boot.model.User;
import com.example.CRUD_Spring_Boot.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model, Principal principal) {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(a);//получаем информацию о пользователе

        //System.out.println(principal.getName());

        model.addAttribute("user", userServices.findById(id));
        return "user/index";
    }

    /*    @GetMapping("/updateUser/{id}")
    public String updateUserForm (@PathVariable("id") Long id, Model model){
        User user = userServices.findById(id);
        System.out.println(" GetMapping from usercontroller");
        model.addAttribute("user",user);
        return "/user/updateUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(User user){
        System.out.println(" Post mapping from usercontroll");
        userServices.saveUser(user);
    return "redirect:/user/{id}";
    }*/
}
