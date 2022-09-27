package com.losmilos.todoapp.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Getter @Setter
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private LocalDateTime datetime;

    @Column
    private Boolean finished = false;
}
