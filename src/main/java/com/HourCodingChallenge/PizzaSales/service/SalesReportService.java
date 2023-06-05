package com.HourCodingChallenge.PizzaSales.service;

import com.HourCodingChallenge.PizzaSales.model.PizzaTypes;
import com.HourCodingChallenge.PizzaSales.model.Pizzas;

import java.util.List;
import java.util.Map;

public interface SalesReportService {

    double calculateTotalSales();

    Map<String, Double> calculateMonthlySales();

    Map<String, Integer> getPopularPizzaType();
}
