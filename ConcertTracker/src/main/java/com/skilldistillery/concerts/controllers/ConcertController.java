package com.skilldistillery.concerts.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.concerts.entities.Concert;
import com.skilldistillery.concerts.seatGeekApi.ApiService;
import com.skilldistillery.concerts.services.ConcertService;

@RestController
@RequestMapping("api")
public class ConcertController {

	@Autowired
	private ConcertService concertService;
	
	@Autowired ApiService apiService;
	
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
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		concert = apiService.unpackData(jsonData);
//		try {
//			String jsonData = apiService.getConcerts("id", sgId);
//			concert = apiService.unpackData(jsonData);
////			concert = concertService.create(concert);
//			res.setStatus(201);
//			StringBuffer urlSb = req.getRequestURL();
//			urlSb.append("/").append(concert.getId());
//			String url = urlSb.toString();
//			res.setHeader("Location", url);
//		} catch (Exception e) {
//			res.setStatus(400);
//			concert = null;
//		}
		return concert;
	}
	
}
