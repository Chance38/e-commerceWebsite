package com.chancelu.ecommercewebsite.dao.impl;

import com.chancelu.ecommercewebsite.dao.ProductDao;
import com.chancelu.ecommercewebsite.dto.ProductQueryParams;
import com.chancelu.ecommercewebsite.dto.ProductRequest;
import com.chancelu.ecommercewebsite.model.Product;
import com.chancelu.ecommercewebsite.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Product> getProducts(ProductQueryParams params) {
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description, " +
                "created_date, last_modified_date " +
                "FROM product WHERE 1=1";
//        WHERE 1=1 對查詢結果沒任何幫助，這裡加這句是為了讓下面的查詢條件可以自由地拼接在sql語法後面

        Map<String, Object> map = new HashMap<>();

        if (params.getCategory() != null) {
//            記得AND前一點要預留空白鍵
            sql += " AND category = :category";
//            category類型為Enum, 先用name method將Enum類型轉成字串, 再加進map裡
            map.put("category", params.getCategory().name());
        }

        if (params.getSearch() != null) {
//            記得AND前一點要預留空白鍵, LIKE -> 模糊查詢, 在要查詢的"search"前後加%, 會搜尋名字內有包含"search"的字串
            sql += " AND product_name LIKE :search";
            map.put("search", "%" + params.getSearch() + "%");
        }

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        return productList;
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "INSERT INTO product(product_name, category, image_url, price, stock, " +
                "description, created_date, last_modified_date) " +
                "VALUES (:productName, :category, :imageUrl, :price, :stock, :description, " +
                ":createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int productId = keyHolder.getKey().intValue();

        return productId;
    }

    @Override
    public Product getProductById(Integer productId) {

        //      從Product Table中查詢商品數據
        String sql = "SELECT product_id, product_name, category, image_url," +
                " price, stock, description, created_date, last_modified_date" +
                " FROM product WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if (productList.size() > 0){
            return productList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
//      根據前端所傳的 productId 來去db對應的各欄位進行更新，除了創建日期不進行更新
        String sql = "UPDATE product SET product_name = :productName, category = :category, image_url = :imageUrl, price = :price, " +
                "stock = :stock, description = :description, last_modified_date = :lastModifiedDate" +
                " Where product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        map.put("lastModifiedDate", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
