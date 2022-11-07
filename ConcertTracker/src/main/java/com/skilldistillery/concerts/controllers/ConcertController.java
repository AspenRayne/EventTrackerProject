package com.skilldistillery.concerts.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.concerts.entities.Concert;
import com.skilldistillery.concerts.entities.Performer;
import com.skilldistillery.concerts.entities.Venue;
import com.skilldistillery.concerts.seatGeekApi.ApiService;
import com.skilldistillery.concerts.services.ConcertService;
import com.skilldistillery.concerts.services.PerformerService;
import com.skilldistillery.concerts.services.VenueService;

@RestController
@RequestMapping("api")
public class ConcertController {

	@Autowired
	private ConcertService concertService;

	@Autowired
	private VenueService venueService;

	@Autowired
	private PerformerService performerService;

	@Autowired
	ApiService apiService;

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

	@PostMapping("concerts/{sgId}")
	public Concert create(@PathVariable String sgId, HttpServletRequest req, HttpServletResponse res) {
		String jsonData = null;
		Concert concert = null;
		try {
			concert = apiService.getConcertById(sgId);
			Venue venue = concert.getVenue();
			if (venue.getId() == 0) {
				venue.addConcert(concert);
				concert.setVenue(venueService.create(venue));
			} else {
				venue.addConcert(concert);
				venueService.update(venue);
			}
			for (Performer performer : concert.getPerformers()) {
				if (performer.getId() == 0) {
					performer.addConcert(concert);
					performerService.create(performer);
					concert.addPerformer(performer);
				} else {
					performer.addConcert(concert);
					performerService.update(performer);
				}
			}
			if (concert.getId() == 0) {
				concert = concertService.create(concert);

				res.setStatus(201);
				StringBuffer urlSb = req.getRequestURL();
				urlSb.append("/").append(concert.getId());
				String url = urlSb.toString();
				res.setHeader("Location", url);
			} else {
				concertService.update(concert.getId(), concert);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
			res.setStatus(400);
		} catch (IOException e) {
			e.printStackTrace();
			res.setStatus(400);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			concert = null;
		}
		return concert;
	}

	@PutMapping("concerts/{concertId}")
	public Concert updateReview(@PathVariable int concertId, 
			@RequestBody Concert concert, HttpServletResponse res) {
		try {
			concert = concertService.update(concertId, concert);
			if (concert == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			concert = null;
		}
		return concert;
		
	}

	@DeleteMapping("concerts/{concertId}")
	public void delete(@PathVariable int concertId, HttpServletResponse res) {
		try {
			if (concertService.delete(concertId)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}

}
