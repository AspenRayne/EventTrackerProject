package com.skilldistillery.concerts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.concerts.entities.Performer;

public interface PerformerRepository extends JpaRepository<Performer, Integer> {

}
