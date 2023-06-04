package com.HourCodingChallenge.PizzaSales.repository;

import com.HourCodingChallenge.PizzaSales.model.Pizzas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzasRepository extends JpaRepository<Pizzas, Long> {
}
