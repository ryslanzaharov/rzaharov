//package com.example.springboot.web;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class LoginController {
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(
//            @RequestParam(value = "error", required = false) String error,
//            @RequestParam(value = "logout", required = false) String logout,
//            Model model) {
//        System.out.println("gregrth");
//        if (error != null) {
//            model.addAttribute("error", "Invalid username and password!");
//        }
//        if (logout != null) {
//            model.addAttribute("msg", "You've been logged out successfully.");
//        }
//        return "login";
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login() {
//        System.out.println("post");
//        return "persons";
//    }
//}
