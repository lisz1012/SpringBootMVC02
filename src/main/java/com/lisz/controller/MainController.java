package com.lisz.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lisz.entity.Account;
import com.lisz.service.AcountService;

@Controller
public class MainController {
	@Autowired
	private AcountService accountService;
	
	@GetMapping("/list")
	@ResponseBody //节省点时间，不搞前端页面了，用@ResponseBody即可
	public Object list(Model model) { //这里由于用了@ResponseBody，返回的accounts会被转化成一个JSON数组在前端显示，这里返回Object即可,List<Account>也行
		List<Account> accounts = accountService.findAll();
		//model.addAttribute("accounts", accounts);
		System.out.println(ToStringBuilder.reflectionToString(accounts, ToStringStyle.MULTI_LINE_STYLE));
		return accounts;
	}
	
	@GetMapping("/list/{id}")
	@ResponseBody //节省点时间，不搞前端页面了，用@ResponseBody即可
	public Object findAccountById(@PathVariable Integer id, Model model) { //这里由于用了@ResponseBody，返回的accounts会被转化成一个JSON数组在前端显示，这里返回Object即可,List<Account>也行
		Account account = accountService.findById(id);
		System.out.println("id = " + id);
		//model.addAttribute("accounts", accounts); //这么写的话要用thymeleaf的<tr th:each="account : ${accounts}">去取
		System.out.println(ToStringBuilder.reflectionToString(account, ToStringStyle.MULTI_LINE_STYLE));
		return account;
	}
	
	@GetMapping("/findByUsernameAndPassword")
	@ResponseBody
	public Object findByUsernameAndPassword() {
		return accountService.findByUsernameAndPassword("wangx", "123");
	}
	
	@GetMapping("/findByHQL")
	@ResponseBody
	public Object findByHQL() {
		return accountService.findByHQL();
	}
	
	@GetMapping("/findByHQL2")
	@ResponseBody
	public Object findByHQL2() {
		return accountService.findByHQL2(2);
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
