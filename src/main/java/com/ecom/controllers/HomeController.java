package com.ecom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.entities.Category;
import com.ecom.entities.Product;
import com.ecom.services.CategoryService;
import com.ecom.services.ProductService;


@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String indexPage(){
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
    
    @GetMapping("/products")
	public String productsPage(Model model) {
        List<Category> categories = categoryService.getAllActiveCategory();
		List<Product> products = productService.getAllActiveProducts();
		model.addAttribute("categories", categories);
		model.addAttribute("products", products);
		return "product";
	}

    @GetMapping("/product")
	public String productDetailsPage() {
		return "view_product";
	}
    
}
