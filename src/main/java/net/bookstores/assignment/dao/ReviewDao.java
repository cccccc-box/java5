package net.bookstores.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bookstores.assignment.entities.Review;

public interface ReviewDao extends JpaRepository<Review, Integer> {

}
