package com.ecom.services.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.constant.CommonUtil;
import com.ecom.constant.OrderStatus;
import com.ecom.entities.Cart;
import com.ecom.entities.OrderAddress;
import com.ecom.entities.OrderRequest;
import com.ecom.entities.ProductOrder;
import com.ecom.repository.CartRepo;
import com.ecom.repository.ProductOrderRepo;
import com.ecom.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductOrderRepo productOrderRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CommonUtil commonUtil;

    @Override
    public void saveOrder(Integer userid, OrderRequest orderRequest) throws Exception {

        List<Cart> carts = cartRepo.findByUserId(userid);

        for (Cart cart : carts) {

            ProductOrder order = new ProductOrder();

            order.setOrderId(UUID.randomUUID().toString());
            order.setOrderDate(LocalDate.now());

            order.setProduct(cart.getProduct());
            order.setPrice(cart.getProduct().getDiscountPrice());

            order.setTotalPrice(cart.getProduct().getDiscountPrice() * cart.getQuantity());

            order.setQuantity(cart.getQuantity());
            order.setUser(cart.getUser());

            order.setStatus(OrderStatus.IN_PROGRESS.getName());
            
            order.setPaymentType(orderRequest.getPaymentType());

            OrderAddress address = new OrderAddress();

            address.setFirstName(orderRequest.getFirstName());
            address.setLastName(orderRequest.getLastName());
            address.setEmail(orderRequest.getEmail());
            address.setMobileNo(orderRequest.getMobileNo());
            address.setAddress(orderRequest.getAddress());
            address.setCity(orderRequest.getCity());
            address.setState(orderRequest.getState());
            address.setPincode(orderRequest.getPincode());

            order.setOrderAddress(address);

            ProductOrder saveOrder=productOrderRepo.save(order);
            commonUtil.sendMailForProductOrder(saveOrder, "Successfully Placed");
        }
    }

    @Override
	public List<ProductOrder> getOrdersByUser(Integer userId) {
		List<ProductOrder> orders = productOrderRepo.findByUserId(userId);
		return orders;
	}

    @Override
	public ProductOrder updateOrderStatus(Integer id, String status) {
		Optional<ProductOrder> findById = productOrderRepo.findById(id);
		if (findById.isPresent()) {
			ProductOrder productOrder = findById.get();
			productOrder.setStatus(status);
			ProductOrder updateOrder=productOrderRepo.save(productOrder);
			return updateOrder;
		}
		return null;
	}

    @Override
	public List<ProductOrder> getAllOrders() {
		return productOrderRepo.findAll();
	}

    @Override
	public ProductOrder getOrdersByOrderId(String orderId) {
		return productOrderRepo.findByOrderId(orderId);
	}

}