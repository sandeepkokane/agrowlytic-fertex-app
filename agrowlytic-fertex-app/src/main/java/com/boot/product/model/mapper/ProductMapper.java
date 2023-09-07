package com.boot.product.model.mapper;

import com.boot.product.model.dto.ProductDTO;
import com.boot.product.model.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    public Product getProduct(ProductDTO productDTO) {
        final Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setUpdatedDate(productDTO.getUpdatedDate());
        return product;
    }

    public ProductDTO getDTO(Product product) {
        final ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setAddedDate(product.getAddedDate());
        productDTO.setUpdatedDate(product.getUpdatedDate());
        return productDTO;
    }
}
