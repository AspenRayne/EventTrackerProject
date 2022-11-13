package com.skilldistillery.concerts.services;

import java.util.List;

import com.skilldistillery.concerts.entities.Concert;
import com.skilldistillery.concerts.entities.Performer;

public interface ConcertService {

	List<Concert> listAllConcerts();
	Concert showConcert(int concertId);
	Concert create(Concert concert);
	Concert update(int concertId, Concert concert);
	boolean delete(int concertId);
	
	List<Performer> listOfPerformers(Long seatGeekId);
	
}
