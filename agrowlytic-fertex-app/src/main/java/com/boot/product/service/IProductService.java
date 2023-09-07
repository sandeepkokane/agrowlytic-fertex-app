package com.boot.product.service;

import com.boot.product.model.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    String PRODUCT_LIST = "product_list";
    String PRODUCT = "product";

    String PRODUCT_FILENAME = "AgrowlyticFertex-Products.xlsx";

    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Long id);

    ProductDTO updateProduct(Long id, ProductDTO productDTO);

    ProductDTO saveProduct(ProductDTO productDTO);

    void deleteProduct(Long id);

}
