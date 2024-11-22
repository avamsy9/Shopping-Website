package com.ecom.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ecom.entities.Cart;
import com.ecom.entities.Product;
import com.ecom.entities.User;
import com.ecom.repository.CartRepo;
import com.ecom.repository.ProductRepo;
import com.ecom.repository.UserRepo;
import com.ecom.services.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Cart saveCart(Integer productId, Integer userId) {

        User user = userRepo.findById(userId).get();
        Product product = productRepo.findById(productId).get();
        Cart cartStatus = cartRepo.findByProductIdAndUserId(productId, userId);

        Cart cart = null;

        if (ObjectUtils.isEmpty(cartStatus)) {
            cart = new Cart();
            cart.setProduct(product);
            cart.setUser(user);
            cart.setQuantity(1);
            cart.setTotalPrice(1 * product.getDiscountPrice());
        } else {
            cart = cartStatus;
            cart.setQuantity(cart.getQuantity() + 1);
            cart.setTotalPrice(cart.getQuantity() * cart.getProduct().getDiscountPrice());
        }
        Cart saveCart = cartRepo.save(cart);
        return saveCart;
    }

    @Override
    public List<Cart> getCartsByUser(Integer userId) {
        // TODO Auto-generated method stub
        return null;
    }
}
