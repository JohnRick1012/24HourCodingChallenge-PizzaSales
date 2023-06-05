package com.HourCodingChallenge.PizzaSales.service;

import com.HourCodingChallenge.PizzaSales.model.OrderDetails;
import com.HourCodingChallenge.PizzaSales.model.PizzaTypes;
import com.HourCodingChallenge.PizzaSales.model.Pizzas;
import com.HourCodingChallenge.PizzaSales.repository.OrderDetailsRepository;
import com.HourCodingChallenge.PizzaSales.repository.PizzaTypesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SalesReportServiceImpl implements SalesReportService{

    private final OrderDetailsRepository orderDetailsRepository;

    private final PizzaTypesRepository pizzaTypesRepository;

    public SalesReportServiceImpl(OrderDetailsRepository orderDetailsRepository, PizzaTypesRepository pizzaTypesRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.pizzaTypesRepository = pizzaTypesRepository;
    }

    @Override
    public double calculateTotalSales() {
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        return orderDetailsList.stream()
                .mapToDouble(orderDetail -> orderDetail.getQuantity() * orderDetail.getPizzas().getPrice())
                .sum();
    }


    @Override
    public Map<String, Double> calculateMonthlySales() {
        Map<String, Double> monthlySales = new LinkedHashMap<>();
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();

        for (OrderDetails orderDetails : orderDetailsList) {
            String monthYear = orderDetails.getOrders().getDate().format(DateTimeFormatter.ofPattern("MMM yyyy"));
            double sales = orderDetails.getQuantity() * orderDetails.getPizzas().getPrice();
            monthlySales.merge(monthYear, sales, Double::sum);
        }
        return monthlySales;
    }

    @Override
    public Map<String, Integer> getPopularPizzaType() {
        Map<String, Integer> popularPizza = new LinkedHashMap<>();
        List<PizzaTypes> pizzaTypesList = pizzaTypesRepository.findAll();

        for (PizzaTypes pizzaTypes : pizzaTypesList) {
            String pizzaName = pizzaTypes.getName();
            Integer quantity =pizzaTypes.getPizzas().getOrderDetails().getQuantity();
            popularPizza.merge(pizzaName, quantity, Integer::sum);
        }
        return popularPizza;
    }

}
