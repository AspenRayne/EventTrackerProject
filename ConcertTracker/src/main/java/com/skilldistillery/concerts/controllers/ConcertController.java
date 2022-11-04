package com.skilldistillery.concerts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.concerts.entities.Concert;
import com.skilldistillery.concerts.services.ConcertService;

@RestController
@RequestMapping("api")
public class ConcertController {

	@Autowired
	private ConcertService concertService;
	
	@GetMapping("concerts")
	public List<Concert> listAllConcerts() {
		return concertService.listAllConcerts();
	}
}
