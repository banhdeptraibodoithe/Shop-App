package org.restaurant.restaurant.service;

import org.restaurant.restaurant.dtos.ProductDTO;
import org.restaurant.restaurant.dtos.ProductImageDTO;
import org.restaurant.restaurant.exceptions.DataNotFoundException;
import org.restaurant.restaurant.exceptions.InvalidParamException;
import org.restaurant.restaurant.models.Product;
import org.restaurant.restaurant.models.ProductImage;
import org.restaurant.restaurant.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IProductService {
    Product createProduct(ProductDTO productDTO) throws DataNotFoundException;
    Product getProductById(Long id) throws DataNotFoundException;
    Page<ProductResponse> getAllProducts(PageRequest pageRequest);
    Product updateProduct(Long id, ProductDTO productDTO) throws DataNotFoundException;
    void deleteProduct(Long id);
    boolean existsByName(String name);
    ProductImage createProductImage(Long productId, ProductImageDTO productImageDTO) throws DataNotFoundException, InvalidParamException;
}
