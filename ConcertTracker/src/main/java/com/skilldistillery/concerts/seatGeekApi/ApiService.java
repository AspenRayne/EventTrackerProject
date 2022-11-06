package com.skilldistillery.concerts.seatGeekApi;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.skilldistillery.concerts.entities.Concert;

@Service
public class ApiService {
	private static final String apiKey = "MzAxMjgwMzd8MTY2NzU4Nzc2OS4xNDM0NzY3";

	public String getConcerts(String searchType, String searchQuery) throws MalformedURLException, IOException {
		String url = null;
		if (searchType.equals("performer")) {
			url = this.performerQuery(searchQuery);
		} else if (searchType.equals("city") || searchType.equals("state")) {
			url = venueQuery(searchQuery, searchType);

		} else if (searchType == "taxonomy") {
			url = taxonomyQuery(searchQuery);
		} else {
			return null;
		}

		InputStream in = new URL(url).openStream();
		String json = IOUtils.toString(in, "UTF-8");
		JSONObject jsonObject = new JSONObject(json);
		System.out.println(jsonObject.toString());
		return jsonObject.toString(4);
	}

	public List<Concert> unpackData() {
		return null;
	}

	public String performerQuery(String searchQuery) {
		searchQuery = this.slugify(searchQuery);
		String url = "https://api.seatgeek.com/2/events?performers.slug=" +
						searchQuery + "&client_id=" + apiKey;
		return url;
	}

	public String venueQuery(String searchQuery, String searchType) {
		try {
			searchQuery = URLEncoder.encode(searchQuery, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String url = "https://api.seatgeek.com/2/events?venue." + 
				searchType + "=" + searchQuery + "&client_id=" + apiKey;
		return url;
	}

	public String taxonomyQuery(String searchQuery) {
		return null;
	}

	private String slugify(final String s) {
		final String intermediateResult = Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")
				.replaceAll("[^-_a-zA-Z0-9]", "-").replaceAll("\\s+", "-").replaceAll("[-]+", "-").replaceAll("^-", "")
				.replaceAll("-$", "").toLowerCase();
		return intermediateResult.substring(0, Math.min(500, intermediateResult.length()));
	}
}
