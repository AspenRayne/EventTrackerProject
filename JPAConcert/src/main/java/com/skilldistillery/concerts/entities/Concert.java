package com.skilldistillery.concerts.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	private Long seatGeekId;
	
	private String review;
	
	@JsonIgnoreProperties(value= {"concerts"})
	@ManyToOne
	@JoinColumn(name="venue_id")
	private Venue venue;
	
	@JsonIgnoreProperties(value= {"concerts"})
	@ManyToMany
	@JoinTable(name="concert_has_performer", joinColumns = @JoinColumn(name="concert_id"), inverseJoinColumns = @JoinColumn(name="performer_id"))
	private List<Performer> performers;

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

	public Long getSeatGeekId() {
		return seatGeekId;
	}

	public void setSeatGeekId(Long seatGeekId) {
		this.seatGeekId = seatGeekId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public List<Performer> getPerformers() {
		return performers;
	}

	public void setPerformers(List<Performer> performers) {
		this.performers = performers;
	}

	public void addPerformer(Performer performer) {
		if (performers == null)
			performers = new ArrayList<>();

		if (!performers.contains(performer)) {
			performers.add(performer);
			performer.addConcert(this);
		}
	}

	public void removePerformer(Performer performer) {
		if (performers != null && performers.contains(performer)) {
			performers.remove(performer);
			performer.removeConcert(this);
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
		Concert other = (Concert) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Concert [id=" + id + ", title=" + title + ", ticketUrl=" + ticketUrl + ", concertDate=" + concertDate
				+ ", seatGeekId=" + seatGeekId + ", review=" + review + "]";
	}
	
	
}