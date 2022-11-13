package com.skilldistillery.concerts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.concerts.entities.Performer;

public interface PerformerRepository extends JpaRepository<Performer, Integer> {
	Performer findByseatGeekId(Long seatGeekId);
	
	List<Performer> findByConcerts_seatGeekId(Long seatGeekId);
}
