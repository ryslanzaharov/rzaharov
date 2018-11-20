package ru.job4j.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.job4j.domain.Person;
import ru.job4j.service.PersonService;

@Controller
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/persons")
    public ModelAndView list() {
        ModelAndView model = new ModelAndView("persons");
        model.addObject("persons", this.service.getAll());
        return model;
    }

    @PostMapping("/persons")
    public String add(@ModelAttribute Person person) {
        this.service.add(person);
        return "redirect:persons";
    }
}
