package com.boot.order.service;

import com.boot.order.model.dto.OrderDTO;
import com.boot.order.model.mapper.OrderMapper;
import com.boot.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::getDTO)
                .sorted(Comparator.comparing(OrderDTO::getId).reversed())
                .collect(Collectors.toList());
    }
}
