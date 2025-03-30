package net.bookstores.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bookstores.assignment.entities.Favorite;

public interface FavoriteDao extends JpaRepository<Favorite, Integer> {

}
