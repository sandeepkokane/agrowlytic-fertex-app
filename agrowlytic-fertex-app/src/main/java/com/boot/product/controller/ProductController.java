package com.boot.product.controller;

import com.boot.product.model.dto.ProductDTO;
import com.boot.product.service.IProductService;
import com.boot.product.service.ProductExcelGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.boot.product.service.IProductService.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    @GetMapping
    public String getAllProducts(Map<String, Object> map) {
        map.put(PRODUCT_LIST, productService.getAllProducts());
        return "products/products";
    }

    @GetMapping("/details/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute(PRODUCT) ProductDTO productDTO,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "products/new-product";
        }
        productService.saveProduct(productDTO);
        return "redirect:/products";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute(PRODUCT) ProductDTO productDTO) {
        productService.updateProduct(id, productDTO);
        return "redirect:/products";
    }

    @GetMapping("/new")
    public String addNewProductView(Map<String, Object> map) {
        map.put(PRODUCT, new ProductDTO());
        return "products/new-product";
    }

    @GetMapping("/update/{id}")
    public String updateProductView(@PathVariable Long id, Map<String, Object> map) {
        map.put(PRODUCT, productService.getProductById(id));
        return "products/update-product";
    }

    @GetMapping("/download")
    public String downloadAllProduct(HttpServletResponse response) throws IOException {
        List<ProductDTO> productDTOList = productService.getAllProducts();
        if (CollectionUtils.isEmpty(productDTOList)) {
            log.error("You dont have any products");
        } else {
            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=" + PRODUCT_FILENAME;
            response.setHeader(headerKey, headerValue);
            final ProductExcelGenerator generator = new ProductExcelGenerator(productDTOList);
            generator.generateExcelFile(response);
        }
        return "redirect:/products";
    }
}
