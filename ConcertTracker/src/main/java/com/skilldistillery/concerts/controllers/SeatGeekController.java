package com.skilldistillery.concerts.controllers;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.concerts.seatGeekApi.ApiService;


@RestController
@RequestMapping("api/searchSG")
public class SeatGeekController {

	@Autowired
	private ApiService sgService;
	
	@GetMapping("concerts/{searchType}/{searchQuery}")
	public String getSGConcerts(@PathVariable String searchType, @PathVariable String searchQuery) throws MalformedURLException, IOException{
		System.out.println(searchType + searchQuery);
		return sgService.getConcerts(searchType, searchQuery);
		
		
	}
}
