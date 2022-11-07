package com.skilldistillery.concerts.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.concerts.entities.Concert;
import com.skilldistillery.concerts.seatGeekApi.ApiService;

@RestController
@RequestMapping("api/searchSG")
public class SeatGeekController {

	@Autowired
	private ApiService sgService;

	@GetMapping("concerts")
	public List<Concert> getSGConcerts(@RequestBody String searchArray, HttpServletResponse res) throws MalformedURLException, IOException {
		List<Concert> concerts = null;
		try{
			JSONArray jsonArray = (JSONArray) JSONValue.parse(searchArray);
			concerts = sgService.getConcerts(jsonArray);
		} catch (ClassCastException e){
			res.setStatus(400);
		}
		if(concerts == null) {
			res.setStatus(400);
		} else if (concerts.isEmpty()) {
			res.setStatus(404);
		}
		return concerts;

	}
}
