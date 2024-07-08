package com.example.shoppingserver.domain.order.dao;

import com.example.shoppingserver.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
