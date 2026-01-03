package org.restaurant.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.restaurant.restaurant.dtos.ProductDTO;
import org.restaurant.restaurant.dtos.ProductImageDTO;
import org.restaurant.restaurant.exceptions.DataNotFoundException;
import org.restaurant.restaurant.exceptions.InvalidParamException;
import org.restaurant.restaurant.models.Category;
import org.restaurant.restaurant.models.Product;
import org.restaurant.restaurant.models.ProductImage;
import org.restaurant.restaurant.repositories.CategoryRepository;
import org.restaurant.restaurant.repositories.ProductImageRepository;
import org.restaurant.restaurant.repositories.ProductRepository;
import org.restaurant.restaurant.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImageRepository productImageRepository;
    @Override
    public Product createProduct(ProductDTO productDTO) throws DataNotFoundException {
        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(()->
                new DataNotFoundException("Can't find category with id = " + productDTO.getCategoryId()));
        Product product = Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .url(productDTO.getUrl())
                .description(productDTO.getDescription())
                .category(category)
                .build();
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) throws DataNotFoundException {
        return productRepository.findById(id).orElseThrow(()
                -> new DataNotFoundException("Can't find product with id = " + id));
    }

    @Override
    public Page<ProductResponse> getAllProducts(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest)
                .map(ProductResponse::fromProduct);
    }

    @Override
    public Product updateProduct(Long id, ProductDTO productDTO) throws DataNotFoundException {
        Product productCur = getProductById(id);
        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(()->
                new DataNotFoundException("Can't update product category with id = " + productDTO.getCategoryId()));
        productCur.setName(productDTO.getName());
        productCur.setPrice(productDTO.getPrice());
        productCur.setUrl(productDTO.getUrl());
        productCur.setDescription(productDTO.getDescription());
        productCur.setCategory(category);
        return productRepository.save(productCur);
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(productRepository::delete);
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }
    @Override
    public ProductImage createProductImage(Long productId, ProductImageDTO productImageDTO) throws DataNotFoundException, InvalidParamException {
        Product existsProduct = productRepository.findById(productImageDTO.getProductId()).orElseThrow(()
                -> new DataNotFoundException("Can't find product with id = " + productId));
        ProductImage productImage = ProductImage.builder()
                .product(existsProduct)
                .imageUrl(productImageDTO.getImageUrl()).build();
        int images = productImageRepository.findByProduct_Id(productId).size();
        if (images >= ProductImage.MAXIMUM_IMAGES_PER_PRODUCT)
            throw new InvalidParamException("Can't have more than "
                    + ProductImage.MAXIMUM_IMAGES_PER_PRODUCT + " images");
        return productImageRepository.save(productImage);
    }
}
