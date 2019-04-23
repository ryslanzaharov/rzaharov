package com.example.urlshortener.controllers;

import com.example.urlshortener.models.Url;
import com.example.urlshortener.models.User;
import com.example.urlshortener.repository.UrlDataRepository;
import com.example.urlshortener.repository.UserDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Statistic {

    private final UrlDataRepository urlDataRepository;
    private final UserDataRepository userDataRepository;

    @Autowired
    public Statistic(UrlDataRepository urlDataRepository, UserDataRepository userDataRepository) {
        this.urlDataRepository = urlDataRepository;
        this.userDataRepository = userDataRepository;
    }

    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public String statisticPage(ModelMap model, HttpServletResponse resp) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDataRepository.getUserByLogin(auth.getName()).orElseThrow(() -> new EntityNotFoundException(auth.getName()));
        List<Url> urls = urlDataRepository.getUrlsById(user.getId());
        model.addAttribute("urls", urls);
        return "statistic";
    }


}
