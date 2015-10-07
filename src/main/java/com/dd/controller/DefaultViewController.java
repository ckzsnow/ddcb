package com.dd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultViewController {
	
	@RequestMapping("/")
	public String getDefaultViewController() {
		return "redirect:/views/test.html";
	}	
}