package br.com.alura.forum.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.forum.annotation.Get;
import br.com.alura.forum.annotation.Rest;

@Rest("isAlive")
public class HomeController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Get
	public String index() {
		logger.info("Hello World");
		return "Hello World";
	}

}
