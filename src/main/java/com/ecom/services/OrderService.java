package com.ecom.services;

import java.util.List;

import com.ecom.entities.OrderRequest;
import com.ecom.entities.ProductOrder;

public interface OrderService {

    public void saveOrder(Integer userid,OrderRequest orderRequest);
    
    public List<ProductOrder> getOrdersByUser(Integer userId);

    public Boolean updateOrderStatus(Integer id,String status);
    
    public List<ProductOrder> getAllOrders();
    
}
