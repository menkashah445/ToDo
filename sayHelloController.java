package com.example.ToDoApp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class sayHelloController {
	
	@RequestMapping("say-helloo")
	@ResponseBody
	public String syhello()
	{
		return "Hello spring bOOT";
	}
	
	@RequestMapping("say-hello-jsp")
	
	public String hello()
	{
		return "sayHello";
	}
	

}
