package com.HourCodingChallenge.PizzaSales.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @Column(name = "order_details_id", nullable = false, unique = true)
    private Long orderDetailsId;

    @Column(name = "quantity",nullable = false)
    private int quantity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pizza_id")
    private Pizzas pizzas;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Orders orders;

    public OrderDetails() {

    }

    public OrderDetails(int quantity, Pizzas pizzas, Orders orders) {
        this.quantity = quantity;
        this.pizzas = pizzas;
        this.orders = orders;
    }

    public OrderDetails(Long orderDetailsId, int quantity, List<Orders> ordersList, List<Pizzas> pizzasList) {
    }

    public Long getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(Long orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Pizzas getPizzas() {
        return pizzas;
    }

    public void setPizzas(Pizzas pizzas) {
        this.pizzas = pizzas;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return quantity == that.quantity && Objects.equals(orderDetailsId, that.orderDetailsId) && Objects.equals(pizzas, that.pizzas) && Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDetailsId, quantity, pizzas, orders);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderDetailsId=" + orderDetailsId +
                ", quantity=" + quantity +
                ", pizzas=" + pizzas +
                ", orders=" + orders +
                '}';
    }

}
