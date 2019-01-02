package br.com.alura.forum.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("isAlive")
public class HomeController {
	
	@GetMapping
	@ResponseBody
	public String index() {
		System.out.println("Hello World");
		return "Hello World";
	}

}
