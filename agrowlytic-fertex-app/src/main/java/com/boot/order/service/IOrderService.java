package com.boot.order.service;

import com.boot.order.model.dto.OrderDTO;

import java.util.List;

public interface IOrderService {

    String ORDER_LIST = "order_list";
    String ORDER = "order";

    List<OrderDTO> getAllOrders();

}
