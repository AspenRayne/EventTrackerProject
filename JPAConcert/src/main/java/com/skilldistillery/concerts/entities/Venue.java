package com.skilldistillery.concerts.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Venue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="seat_geek_id")
	private Long seatGeekId;
	
	private String city;
	
	private String name;
	
	private String country;
	
	private String state;
	
	@Column(name="postal_code")
	private String postalCode;
	
	@Column(name="ticket_url")
	private String ticketUrl;
	
	@OneToMany(mappedBy="venue")
	private List<Concert> concerts;

	public Venue() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getSeatGeekId() {
		return seatGeekId;
	}

	public void setSeatGeekId(Long seatGeekId) {
		this.seatGeekId = seatGeekId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getTicketUrl() {
		return ticketUrl;
	}

	public void setTicketUrl(String ticketUrl) {
		this.ticketUrl = ticketUrl;
	}

	public List<Concert> getConcerts() {
		return concerts;
	}
	
	public void setConcerts(List<Concert> concerts) {
		this.concerts = concerts;
	}
	
	public void addConcert(Concert concert) {
		if (concerts == null)
			concerts = new ArrayList<>();

		if (!concerts.contains(concert)) {
			concerts.add(concert);
			if (concert.getVenue() != null) {
				concert.getVenue().getConcerts().remove(concert);
			}
			concert.setVenue(this);
		}
	}

	public void removeConcert(Concert concert) {
		concert.setVenue(null);
		if (concerts != null) {
			concerts.remove(concert);
		}
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venue other = (Venue) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Venue [id=" + id + ", seatGeekId=" + seatGeekId + ", city=" + city + ", name=" + name + ", country="
				+ country + ", state=" + state + ", postalCode=" + postalCode + ", ticketUrl=" + ticketUrl + "]";
	}
	

}
