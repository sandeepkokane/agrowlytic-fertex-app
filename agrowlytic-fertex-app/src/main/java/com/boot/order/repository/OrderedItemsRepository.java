package com.boot.order.repository;

import com.boot.order.model.entity.OrderedItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedItemsRepository extends JpaRepository<OrderedItems, Long> {
}
