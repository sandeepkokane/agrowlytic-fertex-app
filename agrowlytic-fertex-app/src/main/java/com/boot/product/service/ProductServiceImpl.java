package com.boot.product.service;

import com.boot.product.model.dto.ProductDTO;
import com.boot.product.model.entity.Product;
import com.boot.product.model.mapper.ProductMapper;
import com.boot.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
//    @Cacheable(value = "cx-ticket-by-feedback-id", key = "#cxFeedbackId")
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::getDTO)
                .sorted(Comparator.comparing(ProductDTO::getName))
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "product", key = "#id")
    public ProductDTO getProductById(Long id) {
        log.debug("ProductServiceImpl.getProductById");
        final Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return productMapper.getDTO(product.get());
        }

        return new ProductDTO();
    }

    @Override
    @CachePut(value = "products", key = "#id")
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        log.debug("ProductServiceImpl.updateProduct");
        final Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product productToSave = product.get();
            productToSave.setName(productDTO.getName());
            productToSave.setPrice(productDTO.getPrice());
            productToSave.setUpdatedDate(LocalDateTime.now());
            return productMapper.getDTO(productRepository.save(productToSave));
        } else {
            log.error("No product found to update with id :: " + id);
            return new ProductDTO();
        }
    }

    @Override
//    @CachePut(value = "product", key = "#productDTO.getId()")
    public ProductDTO saveProduct(ProductDTO productDTO) {
        log.debug("ProductServiceImpl.saveProduct");
        final Product product = productMapper.getProduct(productDTO);
        product.setAddedDate(LocalDateTime.now());
        product.setUpdatedDate(LocalDateTime.now());
        return productMapper.getDTO(productRepository.save(product));
    }

    @Override
    @CacheEvict(value = "product", key = "#id")
    public void deleteProduct(Long id) {
        log.debug("ProductServiceImpl.deleteProduct");
        final Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
        } else {
            log.error("No product found to delete with id :: " + id);
        }
    }
}
