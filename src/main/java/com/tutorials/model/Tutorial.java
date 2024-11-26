package com.tutorials.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

/**
 * The @Entity annotation in JPA (Java Persistence API) is used to specify that a Java class
 * represents a table in a relational database. It tells JPA to map the class to a database table
 * and treat instances of the class as rows in that table.
 */
@Entity
@Table(name = "tutorials")
@Data
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "published")
    private boolean published;
}
