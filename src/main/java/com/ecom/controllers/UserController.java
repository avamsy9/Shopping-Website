package com.ecom.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom.entities.Category;
import com.ecom.entities.User;
import com.ecom.services.CategoryService;
import com.ecom.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;
	
	@ModelAttribute
    public void getUserDetails(Principal p,Model model){

        if(p!=null){
            String email=p.getName();
            User user=userService.getUserByEmail(email);
            model.addAttribute("user", user);
        }

        List<Category> allActiveCategory = categoryService.getAllActiveCategory();
        model.addAttribute("categorys", allActiveCategory);
    }


    @GetMapping("/")
	public String home() {
		return "user/home";
	}

}
