package com.HourCodingChallenge.PizzaSales.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @Column(name = "order_id", nullable = false, unique = true)
    private Long orderId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    public Orders() {

    }

    public Orders(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(orderId, orders.orderId) && Objects.equals(date, orders.date) && Objects.equals(time, orders.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, date, time);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
