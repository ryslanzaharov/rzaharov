package com.example.demo.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class GetUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "/ajax", method = RequestMethod.GET)
	protected String showAj(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "ajax";
	}

	@RequestMapping(value = "/ajax", method = RequestMethod.POST)
	protected void processRequest(HttpServletRequest request, HttpServletResponse response,
								  @ModelAttribute("userName") String userName) throws ServletException, IOException {

		if(userName == null || "".equals(userName)){
			userName = "Guest";
		}

		response.setContentType("text/plain;charset=UTF-8");

		InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(userName)));
		BufferedReader reader = new BufferedReader(isr);
		PrintWriter writer = response.getWriter();
		String text;
		while ((text = reader.readLine()) != null) {
			writer.println(text);

		}
		
	}

}
