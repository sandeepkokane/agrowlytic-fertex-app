package com.boot.order.model.dto;

import com.boot.customer.model.entity.Customer;
import com.boot.order.model.entity.OrderedItems;
import com.boot.order.model.entity.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

    private Long id;
    private Customer customer;
    private List<OrderedItems> orderedItems;
    private Double totalAmount;
    private LocalDateTime orderedDate;
    private PaymentType paymentType;

}
