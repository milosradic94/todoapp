package com.losmilos.todoapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="user")
    private List<Todo> todos;
}
