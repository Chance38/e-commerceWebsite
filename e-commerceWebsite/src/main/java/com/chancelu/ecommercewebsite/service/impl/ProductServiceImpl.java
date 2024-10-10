package com.chancelu.ecommercewebsite.service.impl;

import com.chancelu.ecommercewebsite.dao.ProductDao;
import com.chancelu.ecommercewebsite.model.Product;
import com.chancelu.ecommercewebsite.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
