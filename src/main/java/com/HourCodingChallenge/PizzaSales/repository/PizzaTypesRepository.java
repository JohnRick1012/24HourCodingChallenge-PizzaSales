package com.HourCodingChallenge.PizzaSales.repository;

import com.HourCodingChallenge.PizzaSales.model.PizzaTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaTypesRepository extends JpaRepository<PizzaTypes, Long> {


}
