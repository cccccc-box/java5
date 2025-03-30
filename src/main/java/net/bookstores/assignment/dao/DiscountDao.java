package net.bookstores.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bookstores.assignment.entities.Discount;

public interface DiscountDao extends JpaRepository<Discount, Integer> {

}
