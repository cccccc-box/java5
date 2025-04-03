package net.bookstores.assignment.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bookstores.assignment.entities.Discount;

public interface DiscountDao extends JpaRepository<Discount, Integer> {
    Optional<Discount> findByCode(String code);
}
