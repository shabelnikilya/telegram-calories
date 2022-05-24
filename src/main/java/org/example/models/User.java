package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
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
    @Column(name = "isBot")
    private boolean isBot;
    @Column(name = "enable")
    private boolean enable;
}
