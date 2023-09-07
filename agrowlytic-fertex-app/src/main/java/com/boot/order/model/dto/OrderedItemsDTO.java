package com.boot.order.model.dto;

import com.boot.product.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderedItemsDTO {

    private Long id;
    private Integer qty;
    private Product product;
    private Double amount;

}
