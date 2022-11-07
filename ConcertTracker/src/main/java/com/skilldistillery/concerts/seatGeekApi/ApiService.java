package com.skilldistillery.concerts.seatGeekApi;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.concerts.entities.Concert;
import com.skilldistillery.concerts.entities.Performer;
import com.skilldistillery.concerts.entities.Venue;
import com.skilldistillery.concerts.repositories.PerformerRepository;
import com.skilldistillery.concerts.repositories.VenueRepository;

import utils.Utils;

@Service
public class ApiService {
	private static final String apiKey = "MzAxMjgwMzd8MTY2NzU4Nzc2OS4xNDM0NzY3";

	@Autowired
	private VenueRepository venueRepo;

	@Autowired
	private PerformerRepository performerRepo;

	public List<Concert> getConcerts(String searchType, String searchQuery) throws MalformedURLException, IOException {
		String url = null;
		if (searchType.equals("performer")) {
			url = this.performerQuery(searchQuery);
		} else if (searchType.equals("city") || searchType.equals("state")) {
			url = this.venueQuery(searchQuery, searchType);

		} else if (searchType.equals("id")) {
			url = this.idQuery(searchQuery);
		} else {
			return null;
		}

		InputStream in = new URL(url).openStream();
		String json = IOUtils.toString(in, "UTF-8");
		Object response = JSONValue.parse(json);
		List<Concert> concerts = new ArrayList<>();

		if (response instanceof JSONObject) {
			JSONObject jsonResponse = (JSONObject) response;
			// Handle singular concert response
			if (jsonResponse.get("events") != null) {
				JSONArray jsonArray = (JSONArray) jsonResponse.get("events");
				for (Object jsonObject : jsonArray) {
					concerts.add(
							this.unpackData(jsonObject.toString()));
				}
			} else {
				concerts.add(
						this.unpackData(jsonResponse.toString()));
			}
		}
		return concerts;
	}


	private Concert unpackData(String jsonString) {
		JSONObject obj = (JSONObject) JSONValue.parse(jsonString);
		System.out.println(obj.toJSONString());
		Concert concert = new Concert();
		if(obj.get("title") != null) {
			concert.setTitle(obj.get("title").toString());
		}
		concert.setTicketUrl(obj.get("url").toString());
		concert.setSeatGeekId((Long) obj.get("id"));
		concert.setConcertDate(
				Utils.stringToDate(
						obj.get("datetime_local").toString()
						));
		// Parse Venue
		JSONObject venueObj = (JSONObject) obj.get("venue");
		Venue venue = venueRepo.findByseatGeekId((Long) venueObj.get("id"));
		if (venue == null) {
			Venue newVenue = new Venue();
			newVenue.setSeatGeekId((Long) venueObj.get("id"));
			newVenue.setCity(venueObj.get("city").toString());
			newVenue.setCountry(venueObj.get("country").toString());
			newVenue.setState(venueObj.get("state").toString());
			newVenue.setName(venueObj.get("name").toString());
			newVenue.setPostalCode(venueObj.get("postal_code").toString());
			newVenue.setTicketUrl(venueObj.get("url").toString());
			concert.setVenue(newVenue);
		}else {
			concert.setVenue(venue);
		}
		
		// Parse Performers List
		JSONArray performerArray = (JSONArray)obj.get("performers");
		List<Performer> performerList = new ArrayList<>();
		for(Object performerObj : performerArray) {
			JSONObject pObj = (JSONObject) performerObj;
			
			Performer performer = performerRepo.findByseatGeekId(
					(Long) pObj.get("id"));
			if(performer == null) {
				Performer newPerformer = new Performer();
				newPerformer.setName(pObj.get("name").toString());
				newPerformer.setSeatGeekId((Long) pObj.get("id"));
				newPerformer.setImageUrl(pObj.get("image").toString());
				newPerformer.setType(pObj.get("type").toString());
				newPerformer.setName(pObj.get("name").toString());
				performerList.add(newPerformer);
			}else {
				performerList.add(performer);
			}
		}
		concert.setPerformers(performerList);
		
		System.out.println(concert);
		return concert;
	}

	public String performerQuery(String searchQuery) {
		searchQuery = Utils.slugify(searchQuery);
		String url = "https://api.seatgeek.com/2/events?performers.slug=" + searchQuery + "&client_id=" + apiKey;
		return url;
	}

	public String venueQuery(String searchQuery, String searchType) {
		try {
			searchQuery = URLEncoder.encode(searchQuery, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String url = "https://api.seatgeek.com/2/events?venue." + searchType + "=" + searchQuery + "&client_id="
				+ apiKey;
		return url;
	}

	public String idQuery(String searchQuery) {
		String url = "https://api.seatgeek.com/2/events/" + searchQuery + "?client_id=" + apiKey;
		return url;
	}

}
