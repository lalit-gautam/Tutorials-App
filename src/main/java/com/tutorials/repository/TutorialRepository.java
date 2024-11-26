package com.tutorials.repository;

import com.tutorials.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * In the context of Spring Data and JPA, a repository is a class or interface that provides an abstraction layer for data access, allowing you to perform CRUD (Create, Read, Update, Delete) operations on database entities without having to write boilerplate SQL or JDBC code.
 */
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByPublished(boolean published);
    List<Tutorial> findByNameContaining(String name);
}
