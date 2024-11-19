package com.ecom.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.entities.Product;
import com.ecom.repository.ProductRepo;
import com.ecom.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
	private ProductRepo productRepo;

	@Override
	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
	public Boolean deleteProduct(Integer id) {
		Product product = productRepo.findById(id).orElse(null);

		if (!ObjectUtils.isEmpty(product)) {
			productRepo.delete(product);
			return true;
		}
		return false;
	}

    @Override
    public Product getProductById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductById'");
    }

    @Override
    public Product updateProduct(Product product, MultipartFile file) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

}
