package com.chancelu.ecommercewebsite.service;

import com.chancelu.ecommercewebsite.constant.ProductCategory;
import com.chancelu.ecommercewebsite.dto.ProductRequest;
import com.chancelu.ecommercewebsite.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);


}
