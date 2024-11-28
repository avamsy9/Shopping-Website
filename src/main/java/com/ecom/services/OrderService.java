package com.ecom.services;

import com.ecom.entities.OrderRequest;

public interface OrderService {

    public void saveOrder(Integer userid,OrderRequest orderRequest);
    
}
