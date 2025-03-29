package net.bookstores.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bookstores.assignment.entities.ShippingProvider;

public interface ShippingProviderDao extends JpaRepository<ShippingProvider, Integer> {

}
