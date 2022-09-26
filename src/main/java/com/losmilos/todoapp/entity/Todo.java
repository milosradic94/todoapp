package com.losmilos.todoapp.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Getter @Setter
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PROTECTED)
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Date datetime;

    @Column
    private Boolean finished = false;
}
