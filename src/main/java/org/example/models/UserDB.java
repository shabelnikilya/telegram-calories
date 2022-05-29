package org.example.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserDB {
    @Id
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String secondName;
    @Column(name = "age")
    private int age;
    @Column(name = "weight")
    private float weight;
    @Column(name = "isbot")
    private boolean isBot;
    @Column(name = "enable")
    private boolean enable;
    @ManyToMany
    @JoinTable(
            name = "users_food",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private List<Food> foods = new ArrayList<>();
}
