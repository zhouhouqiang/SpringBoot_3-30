package com.example.springBootDemo.modules.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

	/**
	 * 127.0.0.1:8085/account/login
	 */
	@RequestMapping("/login")
	public String loginPage() {

		return "indexSimple";
	}

	/**
	 * 127.0.0.1:8085/account/dashboard
	 */
	@RequestMapping("/dashboard")
	public String dashboardPage() {
		return "index";
	}

}
