package com.skilldistillery.concerts.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Performer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="seat_geek_id")
	private Integer seatGeekId;
	
	private String name;
	
	@Column(name="image_url")
	private String imageUrl;
	
	private String type;
	
	private String genre;

	public Performer() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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
		Performer other = (Performer) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Performer [id=" + id + ", seatGeekId=" + seatGeekId + ", name=" + name + ", imageUrl=" + imageUrl
				+ ", type=" + type + ", genre=" + genre + "]";
	}
	
	
}
