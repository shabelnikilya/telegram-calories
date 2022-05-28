package org.example.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Модель данных продуктов
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Название продуктаю
     */
    private String name;
    /**
     * Белки.
     */
    private float proteins;
    /**
     * Жиры продукта.
     */
    private float fats;
    /**
     * Углеводы.
     */
    private float carbohydrates;
    /**
     * Калории на 100 грамм.
     */
    private int kcal;

    @ManyToMany(mappedBy = "products")
    private List<Food> foods = new ArrayList<>();
}

