package com.skilldistillery.concerts.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Concert {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	@Column(name="ticket_url")
	private String ticketUrl;
	
	@Column(name="concert_date")
	private LocalDateTime concertDate;
	
	@Column(name="seat_geek_id")
	private Integer seatGeekId;

	public Concert() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTicketUrl() {
		return ticketUrl;
	}

	public void setTicketUrl(String ticketUrl) {
		this.ticketUrl = ticketUrl;
	}

	public LocalDateTime getConcertDate() {
		return concertDate;
	}

	public void setConcertDate(LocalDateTime concertDate) {
		this.concertDate = concertDate;
	}

	public Integer getSeatGeekId() {
		return seatGeekId;
	}

	public void setSeatGeekId(Integer seatGeekId) {
		this.seatGeekId = seatGeekId;
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
		Concert other = (Concert) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Concert [id=" + id + ", title=" + title + ", ticket_url=" + ticketUrl + ", concertDate=" + concertDate
				+ ", seatGeekId=" + seatGeekId + "]";
	}
	
	
}