package com.skilldistillery.concerts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.concerts.entities.Concert;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Concert update(int concertId, Concert concert) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int concertId) {
		// TODO Auto-generated method stub
		return false;
	}

}
