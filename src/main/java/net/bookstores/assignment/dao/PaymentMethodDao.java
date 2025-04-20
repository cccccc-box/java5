package net.bookstores.assignment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bookstores.assignment.entities.PaymentMethod;

public interface PaymentMethodDao extends JpaRepository<PaymentMethod, Integer> {
    List<PaymentMethod> findByActiveTrue();
}
