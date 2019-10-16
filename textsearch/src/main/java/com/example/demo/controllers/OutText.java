package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OutText {

    @RequestMapping(value = "/outtext", method = RequestMethod.POST)
    public void outputText(@RequestParam(value = "path") String path, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain;charset=UTF-8");

        System.out.println(path);
        InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(path)));
        BufferedReader reader = new BufferedReader(isr);
        PrintWriter writer = response.getWriter();
        String text;

        writer.println();
        while ((text = reader.readLine()) != null) {
            writer.println(text + "</br>");

        }
    }


}
