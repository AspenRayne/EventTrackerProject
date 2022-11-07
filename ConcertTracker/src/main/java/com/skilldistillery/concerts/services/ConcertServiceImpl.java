package com.skilldistillery.concerts.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.concerts.entities.Concert;
import com.skilldistillery.concerts.entities.Performer;
import com.skilldistillery.concerts.entities.Venue;
import com.skilldistillery.concerts.repositories.ConcertRepository;

@Service
public class ConcertServiceImpl implements ConcertService {

	@Autowired
	private ConcertRepository concertRepo;
	
	@Override
	public List<Concert> listAllConcerts() {
		return concertRepo.findAll();
	}

	@Override
	public Concert showConcert(int concertId) {
		Optional<Concert> concertOpt = concertRepo.findById(concertId);
		Concert concert = null;
		if (concertOpt.isPresent()) {
			concert = concertOpt.get();
		}
		return concert;
	}

	@Override
	public Concert create(Concert concert) {
		return concertRepo.saveAndFlush(concert);
	}

	@Override
	public Concert update(int concertId, Concert concert) {
		Concert managed = this.showConcert(concertId);
		managed.setReview(concert.getReview());
		managed.setPerformers(concert.getPerformers());
		managed.setVenue(concert.getVenue());
		return concertRepo.save(managed);
	}

	@Override
	public boolean delete(int concertId) {
		Concert deleted = this.showConcert(concertId);
		if (deleted != null) {
			List<Performer> performers = new ArrayList<>(deleted.getPerformers());
			Venue venue = deleted.getVenue();
			for (Performer performer : performers) {
				deleted.removePerformer(performer);
			}
			deleted.setVenue(null);
			venue.removeConcert(deleted);
			concertRepo.deleteById(concertId);
		}
		return !concertRepo.existsById(concertId);
	}

}
