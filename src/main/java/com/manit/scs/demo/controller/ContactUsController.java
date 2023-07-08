package com.manit.scs.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ContactUsController {
	@GetMapping("/contactUs")
	public String adminLogin()
	{
		return "contactUs.html";
	}
}
