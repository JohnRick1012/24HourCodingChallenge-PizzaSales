package com.HourCodingChallenge.PizzaSales.repository;

import com.HourCodingChallenge.PizzaSales.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    
}
