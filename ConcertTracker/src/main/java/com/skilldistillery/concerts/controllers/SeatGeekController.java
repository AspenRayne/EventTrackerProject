package com.skilldistillery.concerts.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.concerts.entities.Concert;
import com.skilldistillery.concerts.seatGeekApi.ApiService;

@RestController
@RequestMapping("api/searchSG")
public class SeatGeekController {

	@Autowired
	private ApiService sgService;

	@GetMapping("concerts/{searchType}/{searchQuery}")
	public List<Concert> getSGConcerts(@PathVariable String searchType, @PathVariable String searchQuery)
			throws MalformedURLException, IOException {
		return sgService.getConcerts(searchType, searchQuery);

	}
}
