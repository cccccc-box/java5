package net.bookstores.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bookstores.assignment.entities.Category;

public interface CategoryDao extends JpaRepository<Category, Integer> {

}
