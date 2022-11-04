package com.skilldistillery.concerts.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Venue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="seat_geek_id")
	private Integer seatGeekId;
	
	private String city;
	
	private String name;
	
	private String country;
	
	private String state;
	
	@Column(name="postal_code")
	private String postalCode;
	
	@Column(name="ticket_url")
	private String ticketUrl;

	public Venue() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getSeatGeekId() {
		return seatGeekId;
	}

	public void setSeatGeekId(Integer seatGeekId) {
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
