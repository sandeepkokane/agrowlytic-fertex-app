package com.boot.order.controller;

import com.boot.order.model.dto.OrderDTO;
import com.boot.order.service.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static com.boot.order.service.IOrderService.ORDER;
import static com.boot.order.service.IOrderService.ORDER_LIST;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final IOrderService orderService;

    @GetMapping
    public String getAllOrders(Map<String, Object> map) {
        map.put(ORDER_LIST, orderService.getAllOrders());
        return "orders/orders";
    }

    @GetMapping("/new")
    public String addNewOrderView(Map<String, Object> map) {
        map.put(ORDER, new OrderDTO());
        return "orders/new-order";
    }



}
