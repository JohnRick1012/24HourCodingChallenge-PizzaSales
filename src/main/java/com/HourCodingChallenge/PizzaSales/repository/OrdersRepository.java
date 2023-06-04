package com.HourCodingChallenge.PizzaSales.repository;

import com.HourCodingChallenge.PizzaSales.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

}
