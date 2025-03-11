package net.bookstores.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bookstores.assignment.entities.Publisher;

public interface PublisherDao extends JpaRepository<Publisher, Integer> {

}
