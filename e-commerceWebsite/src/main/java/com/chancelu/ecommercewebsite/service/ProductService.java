package com.chancelu.ecommercewebsite.service;

import com.chancelu.ecommercewebsite.dto.ProductQueryParams;
import com.chancelu.ecommercewebsite.dto.ProductRequest;
import com.chancelu.ecommercewebsite.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductQueryParams params);

    Integer countProducts(ProductQueryParams params);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);


}
