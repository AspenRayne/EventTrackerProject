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
import com.skilldistillery.concerts.repositories.ConcertRepository;
import com.skilldistillery.concerts.repositories.PerformerRepository;
import com.skilldistillery.concerts.repositories.VenueRepository;

import utils.Utils;

@Service
public class ApiService {
	private static final String apiKey = "MzAxMjgwMzd8MTY2NzU4Nzc2OS4xNDM0NzY3";
	private static final String baseUrl = "https://api.seatgeek.com/2/events";
	@Autowired
	private VenueRepository venueRepo;

	@Autowired
	private PerformerRepository performerRepo;

	@Autowired
	private ConcertRepository concertRepo;

	public List<Concert> getConcerts(JSONArray searchArray) throws MalformedURLException, IOException {
		String url = this.queryBuilder(searchArray);
		if (url == null) {
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
					concerts.add(this.unpackData(jsonObject.toString()));
				}
			} else {
				concerts.add(this.unpackData(jsonResponse.toString()));
			}
		}
		return concerts;
	}

	public Concert getConcertById(String sgId) throws IOException {
		String url = this.idQuery(sgId);
		InputStream in = new URL(url).openStream();
		String json = IOUtils.toString(in, "UTF-8");
		Object response = JSONValue.parse(json);
		Concert concert = null;
		if (response instanceof JSONObject) {
			JSONObject jsonResponse = (JSONObject) response;
			concert = unpackData(jsonResponse.toString());
		}
		return concert;
	}

	private Concert unpackData(String jsonString) {
		JSONObject obj = (JSONObject) JSONValue.parse(jsonString);
		Concert concert = concertRepo.findByseatGeekId((Long) obj.get("id"));
		if (concert == null) {
			Concert newConcert = new Concert();
			if (obj.get("title") != null) {
				newConcert.setTitle(obj.get("title").toString());
			}
			newConcert.setTicketUrl(obj.get("url").toString());
			newConcert.setSeatGeekId((Long) obj.get("id"));
			newConcert.setConcertDate(Utils.stringToDate(obj.get("datetime_local").toString()));
			concert = newConcert;
		} 
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
		} else {
			concert.setVenue(venue);
		}

		// Parse Performers List
		JSONArray performerArray = (JSONArray) obj.get("performers");
		List<Performer> performerList = new ArrayList<>();
		for (Object performerObj : performerArray) {
			JSONObject pObj = (JSONObject) performerObj;

			Performer performer = performerRepo.findByseatGeekId((Long) pObj.get("id"));
			if (performer == null) {
				Performer newPerformer = new Performer();
				newPerformer.setName(pObj.get("name").toString());
				newPerformer.setSeatGeekId((Long) pObj.get("id"));
				newPerformer.setImageUrl(pObj.get("image").toString());
				newPerformer.setType(pObj.get("type").toString());
				newPerformer.setName(pObj.get("name").toString());
				performerList.add(newPerformer);
			} else {
				performerList.add(performer);
			}
		}
		concert.setPerformers(performerList);

		return concert;
	}

	public String queryBuilder(JSONArray searchArray) {
		String url = baseUrl + "?";
		for (Object query : searchArray) {
			JSONObject queryObj = (JSONObject) query;
			String searchType = queryObj.get("type").toString();
			String searchQuery = queryObj.get("query").toString();
			String urlAddition = null;

			if (searchType.equals("performer")) {
				urlAddition = this.performerQuery(searchQuery);
			} else if (searchType.equals("city") || searchType.equals("state")) {
				urlAddition = this.venueQuery(searchQuery, searchType);
			}

			if (urlAddition == null) {
				return null;
			}

			url += urlAddition + "&";
		}
		url += "client_id=" + apiKey;
		return url;
	}

	public String performerQuery(String searchQuery) {
		searchQuery = Utils.slugify(searchQuery);
		String url = "performers.slug=" + searchQuery;
		return url;
	}

	public String venueQuery(String searchQuery, String searchType) {
		try {
			searchQuery = URLEncoder.encode(searchQuery, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		String url = "venue." + searchType + "=" + searchQuery;
		return url;
	}

	public String idQuery(String searchQuery) {
		String url = "https://api.seatgeek.com/2/events/" + searchQuery + "?client_id=" + apiKey;
		return url;
	}

}
