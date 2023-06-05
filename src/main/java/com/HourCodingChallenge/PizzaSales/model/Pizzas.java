package com.HourCodingChallenge.PizzaSales.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pizzas")
public class Pizzas {

    @Id
    @Column(name = "pizza_id", nullable = false, unique = true)
    private Long pizzaId;

    @Column(name = "size", nullable = false)
    private String size;

    @Column(name = "price", nullable = false)
    private double price;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pizza_type_id")
    private List<PizzaTypes> pizzaTypes;

    @OneToOne(cascade = CascadeType.ALL)
    private OrderDetails orderDetails;


    public Pizzas(String size, double price, List<PizzaTypes> pizzaTypes, OrderDetails orderDetails) {
        this.size = size;
        this.price = price;
        this.pizzaTypes = pizzaTypes;
        this.orderDetails = orderDetails;
    }

    public Pizzas(Long pizzaId, String size, double price, List<PizzaTypes> pizzaTypes) {
    }

    public Long getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Long pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<PizzaTypes> getPizzaTypes() {
        return pizzaTypes;
    }

    public void setPizzaTypes(List<PizzaTypes> pizzaTypes) {
        this.pizzaTypes = pizzaTypes;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizzas pizzas = (Pizzas) o;
        return Double.compare(pizzas.price, price) == 0 && Objects.equals(pizzaId, pizzas.pizzaId) && Objects.equals(size, pizzas.size) && Objects.equals(pizzaTypes, pizzas.pizzaTypes) && Objects.equals(orderDetails, pizzas.orderDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizzaId, size, price, pizzaTypes, orderDetails);
    }

    @Override
    public String toString() {
        return "Pizzas{" +
                "pizzaId=" + pizzaId +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", pizzaTypes=" + pizzaTypes +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
