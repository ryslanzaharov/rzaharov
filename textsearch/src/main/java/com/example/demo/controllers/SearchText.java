package com.example.demo.controllers;

import com.example.demo.search.SearchFiles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class SearchText {




    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showIndexPage() {
        return "index";
    }


    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String searchText(@RequestParam(value = "search_text") String searchText,
                             @RequestParam(value = "type_file") String type_file,
                             @RequestParam(value = "path") String path,
                             ModelMap model) {
        SearchFiles searchFiles = new SearchFiles();
        HashMap<Path, List<Integer>> foundFiles = new HashMap<>();
        if (type_file == null) {
            type_file = "." + "log";
        }
        else
            type_file = "." + type_file;
        List<Path> pathList = new ArrayList<>();

        List<Integer> lineCount = new ArrayList<>();
        try {
            foundFiles.putAll(searchFiles.searchFor(searchText, searchFiles.displayIt(new File(path), type_file, pathList)));
        } catch (IOException e) {
            e.getMessage();
        }
        model.addAttribute("paths", foundFiles.keySet());
        model.addAttribute("lines", lineCount);
        return "index";
    }



}
