package com.tools.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tools.web.annotation.Controller;
import com.tools.web.annotation.RequestMapping;

/**
 * @author liujian
 *
 */
@Controller
@RequestMapping(name="HelloWorldController", path={"/hello"})
public class HelloWorldController{

	@RequestMapping(name="Hello", path={""})
	public String Hello(HttpServletRequest req, HttpServletResponse resp){
		
		return "Hello World!";
	}
	
}
