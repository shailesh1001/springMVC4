package com.websystique.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.websystique.springmvc.model.Pizza;
 
@Controller
@RequestMapping("/")
public class HelloWorldController {
 
    @RequestMapping(method = RequestMethod.GET)
    public String sayHello(ModelMap model) {
      
        return "welcome";
    }
 
    /*
    @RequestMapping(value = "/helloagain", method = RequestMethod.GET)
    public String sayHelloAgain(ModelMap model) {
        model.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
        return "welcome";
    }
 */
	@RequestMapping(value="alok/{pizzaName}", method = RequestMethod.GET)
	public String getPizza(@PathVariable String pizzaName, ModelMap model) {
 
		Pizza pizza = new Pizza(pizzaName);
		model.addAttribute("pizza", pizza);
 
		return "pizza";
 
	}
   
    
}