package com.ecom.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ecom.entities.OrderRequest;
import com.ecom.entities.ProductOrder;

public interface OrderService {

    public void saveOrder(Integer userid,OrderRequest orderRequest) throws Exception;
    
    public List<ProductOrder> getOrdersByUser(Integer userId);

    public ProductOrder updateOrderStatus(Integer id,String status);
    
    public List<ProductOrder> getAllOrders();

    public ProductOrder getOrdersByOrderId(String orderId);

    public Page<ProductOrder> getAllOrdersPagination(Integer pageNo,Integer pageSize);
    
}
