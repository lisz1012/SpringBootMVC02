package com.lisz.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lisz.entity.Account;
import com.lisz.service.AcountService;

@Controller
public class MainController {
	@Autowired
	private AcountService accountService;
	
	@GetMapping("/list")
	public String list(Model model) {
		accountService.findAll();
		return "list";
	}
	
	@GetMapping("/register")
	public String registerGet() {
		System.out.println("Get");
		return "register";
	}
	
	@PostMapping("/register")
	public String registerPost(HttpServletRequest request, Account account) { // Spring MVC会自动的把request注入到这里来,然后还能组装成一个Account对象
		ResponseStatus status = accountService.save(account);
		request.setAttribute("status", status);
		System.out.println("Register status: " + ToStringBuilder.reflectionToString(status, ToStringStyle.MULTI_LINE_STYLE));
		return "register";
	} 
	
	@PostMapping("/login")
	public String login() {
		return "login";
	}
}
