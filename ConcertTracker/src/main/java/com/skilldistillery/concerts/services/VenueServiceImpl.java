package com.skilldistillery.concerts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.concerts.entities.Venue;
import com.skilldistillery.concerts.repositories.VenueRepository;

@Service
public class VenueServiceImpl implements VenueService {
	
	@Autowired
	private VenueRepository venueRepo;

	@Override
	public Venue create(Venue venue) {
		return venueRepo.saveAndFlush(venue);
	}

	@Override
	public Venue update(Venue venue) {
		return venueRepo.save(venue);
	}
}
