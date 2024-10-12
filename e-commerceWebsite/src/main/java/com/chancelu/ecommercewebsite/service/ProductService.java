package com.chancelu.ecommercewebsite.service;

import com.chancelu.ecommercewebsite.dto.ProductRequest;
import com.chancelu.ecommercewebsite.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
