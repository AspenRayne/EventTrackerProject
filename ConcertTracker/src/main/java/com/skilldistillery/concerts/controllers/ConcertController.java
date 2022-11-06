package com.skilldistillery.concerts.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("concerts/{concertId}")
	public Concert show(@PathVariable int concertId, HttpServletResponse res) {
		Concert concert = concertService.showConcert(concertId);
		if (concert == null) {
			res.setStatus(404);
		}
		return concert;
	}
	
}
