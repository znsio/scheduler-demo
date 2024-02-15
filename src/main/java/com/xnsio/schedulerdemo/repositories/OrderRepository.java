package com.xnsio.schedulerdemo.repositories;

import com.xnsio.schedulerdemo.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
