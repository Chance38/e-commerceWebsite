package com.chancelu.ecommercewebsite.dao;

import com.chancelu.ecommercewebsite.dto.ProductRequest;
import com.chancelu.ecommercewebsite.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
