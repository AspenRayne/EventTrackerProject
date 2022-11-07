package com.skilldistillery.concerts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.concerts.entities.Performer;
import com.skilldistillery.concerts.repositories.PerformerRepository;

@Service
public class PerformerServiceImpl implements PerformerService {

	@Autowired
	private PerformerRepository performerRepo;

	@Override
	public Performer create(Performer performer) {
		return performerRepo.saveAndFlush(performer);
	}

	@Override
	public Performer update(Performer performer) {
		return performerRepo.save(performer);
	}

}
