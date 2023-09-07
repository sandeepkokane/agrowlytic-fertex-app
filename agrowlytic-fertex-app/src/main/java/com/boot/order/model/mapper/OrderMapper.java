package com.boot.order.model.mapper;

import com.boot.order.model.dto.OrderDTO;
import com.boot.order.model.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order getOrder(OrderDTO orderDTO) {
        final Order order = new Order();
        order.setCustomer(orderDTO.getCustomer());
        order.setOrderedDate(orderDTO.getOrderedDate());
        order.setOrderedItems(orderDTO.getOrderedItems());
        order.setPaymentType(orderDTO.getPaymentType().getType());
        order.setTotalAmount(orderDTO.getTotalAmount());
        return order;
    }

    public OrderDTO getDTO(Order order) {
        final OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomer(order.getCustomer());
        orderDTO.setOrderedDate(order.getOrderedDate());
        orderDTO.setOrderedItems(order.getOrderedItems());
//        orderDTO.setPaymentType(PaymentType.valueOf(order.getPaymentType()));
        return orderDTO;
    }

}
