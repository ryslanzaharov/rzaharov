package com.example.urlshortener.controllers;

import com.example.urlshortener.generate.Generator;
import com.example.urlshortener.models.Role;
import com.example.urlshortener.models.Url;
import com.example.urlshortener.models.User;
import com.example.urlshortener.repository.UrlDataRepository;
import com.example.urlshortener.repository.UserDataRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.nio.charset.Charset;
import java.util.Random;

@Controller
public class Register {

    private final UrlDataRepository urlDataRepository;
    private final UserDataRepository userDataRepository;

    @Autowired
    public Register(UrlDataRepository urlDataRepository, UserDataRepository userDataRepository) {
        this.urlDataRepository = urlDataRepository;
        this.userDataRepository = userDataRepository;
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String registerPage() {
        System.out.println("reg");
        return "register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String registration(
            @RequestBody Url url
    ) {
        String shortUrl = "";
        if (!url.getUrl().isEmpty()) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userDataRepository.getUserByLogin(auth.getName()).orElseThrow(() -> new EntityNotFoundException(auth.getName()));
            shortUrl = "http://short.com/" + Generator.generateString(6);
            url.setShort_url(shortUrl);
            url.setUser(user);
            urlDataRepository.save(url);
        }
        return shortUrl;
    }
}
