package com.skilldistillery.concerts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.concerts.entities.Performer;
import com.skilldistillery.concerts.entities.Venue;

public interface PerformerRepository extends JpaRepository<Performer, Integer> {
	Performer findByseatGeekId(Long seatGeekId);
}
