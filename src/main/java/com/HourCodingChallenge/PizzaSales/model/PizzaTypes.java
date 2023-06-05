package com.HourCodingChallenge.PizzaSales.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "pizza_types")
public class PizzaTypes {

    @Id
    @Column(name = "pizza_type_id", nullable = false)
    private Long pizzaTypeId;

    @Column(name = "name", length = 300, nullable = false)
    private String name;

    @Column(name = "category", length = 300, nullable = false)
    private String category;

    @Column(name = "ingredients", length = 1000, nullable = false)
    private String ingredients;

    @ManyToOne(cascade =  CascadeType.ALL)
    private Pizzas pizzas;

    public PizzaTypes() {

    }

    public PizzaTypes(String name, String category, String ingredients, Pizzas pizzas) {
        this.name = name;
        this.category = category;
        this.ingredients = ingredients;
        this.pizzas = pizzas;
    }

    public Long getPizzaTypeId() {
        return pizzaTypeId;
    }

    public void setPizzaTypeId(Long pizzaTypeId) {
        this.pizzaTypeId = pizzaTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Pizzas getPizzas() {
        return pizzas;
    }

    public void setPizzas(Pizzas pizzas) {
        this.pizzas = pizzas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PizzaTypes that = (PizzaTypes) o;
        return Objects.equals(pizzaTypeId, that.pizzaTypeId) && Objects.equals(name, that.name) && Objects.equals(category, that.category) && Objects.equals(ingredients, that.ingredients) && Objects.equals(pizzas, that.pizzas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizzaTypeId, name, category, ingredients, pizzas);
    }

    @Override
    public String toString() {
        return "PizzaTypes{" +
                "pizzaTypeId=" + pizzaTypeId +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", pizzas=" + pizzas +
                '}';
    }
}
