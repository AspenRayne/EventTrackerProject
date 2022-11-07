package com.skilldistillery.concerts.services;

import com.skilldistillery.concerts.entities.Venue;

public interface VenueService {
	
	Venue create(Venue venue);
	Venue update(Venue venue);
}
