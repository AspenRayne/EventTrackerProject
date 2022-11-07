package com.skilldistillery.concerts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.concerts.entities.Concert;
import com.skilldistillery.concerts.entities.Performer;

public interface ConcertRepository extends JpaRepository<Concert, Integer> {
	Concert findByseatGeekId(Long seatGeekId);

}
