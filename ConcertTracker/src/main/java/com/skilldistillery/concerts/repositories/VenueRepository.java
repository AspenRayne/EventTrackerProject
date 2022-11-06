package com.skilldistillery.concerts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.concerts.entities.Venue;

public interface VenueRepository extends JpaRepository<Venue, Integer> {

}
