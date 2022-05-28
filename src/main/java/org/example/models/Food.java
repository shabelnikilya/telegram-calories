package org.example.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToMany
    @JoinTable(
            name = "food_product",
            joinColumns = @JoinColumn(name = "food_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();
    @ManyToMany(mappedBy = "foods")
    private List<User> users = new ArrayList<>();
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_eat")
    private Date dateEat = new Date(System.currentTimeMillis());
}
