package com.ecom.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.entities.Cart;
import com.ecom.entities.Category;
import com.ecom.entities.User;
import com.ecom.services.CartService;
import com.ecom.services.CategoryService;
import com.ecom.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartService cartService;

    @ModelAttribute
    public void getUserDetails(Principal p, Model model) {

        if (p != null) {
            String email = p.getName();
            User user = userService.getUserByEmail(email);
            model.addAttribute("user", user);

            Integer countCart = cartService.getCountCart(user.getId());
            model.addAttribute("countCart", countCart);
        }

        List<Category> allActiveCategory = categoryService.getAllActiveCategory();
        model.addAttribute("categorys", allActiveCategory);
    }

    @GetMapping("/")
    public String home() {
        return "user/home";
    }

    @GetMapping("/addCart")
    public String addToCart(@RequestParam Integer pid, @RequestParam Integer uid, HttpSession session) {
        Cart saveCart = cartService.saveCart(pid, uid);

        if (ObjectUtils.isEmpty(saveCart)) {
            session.setAttribute("errorMsg", "Product add to cart failed");
        } else {
            session.setAttribute("successMsg", "Product added to cart");
        }
        return "redirect:/product/" + pid;
    }

    @GetMapping("/cart")
    public String loadCartPage(Principal p, Model model) {
        User user = getLoggedInUserDetails(p);
        List<Cart> carts = cartService.getCartsByUser(user.getId());

        model.addAttribute("carts", carts);

        if (carts.size() > 0) {
            Double totalOrderPrice = carts.get(carts.size() - 1).getTotalOrderPrice();
            model.addAttribute("totalOrderPrice", totalOrderPrice);
        }
        return "/user/cart";
    }

    private User getLoggedInUserDetails(Principal p) {
		String email = p.getName();
		User user = userService.getUserByEmail(email);
		return user;
	}

    @GetMapping("/cartQuantityUpdate")
	public String updateCartQuantity(@RequestParam String sy, @RequestParam Integer cid) {
		cartService.updateQuantity(sy, cid);
		return "redirect:/user/cart";
	}	

}
