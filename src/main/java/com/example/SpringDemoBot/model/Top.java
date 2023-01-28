package com.example.SpringDemoBot.model;

import javax.persistence.*;

@Entity
@Table(name = "top")
public class Top {

	private long id;
	private String name;
	private int year;
	private float rating;
	private String place;


	public Top() {

	}


	public Top(String name, int year, float rating, String place) {
		this.name = name;
		this.year = year;
		this.rating = rating;
		this.place = place;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	@Column(name = "rating", nullable = false)
	public float getRating() {return rating;}
	public void setRating(float rating) {this.rating = rating;}

	@Column(name = "year", nullable = false)
	public int getYear() {return year;}
	public void setYear(int year) {this.year = year;}

	@Column(name = "place", nullable = false)
	public String getPlace() {return place;}
	public void setPlace(String place) {this.place = place;}


	@Override
	public String toString() {
		return "Top{" +
				"id=" + id +
				", name='" + name + '\'' +
				", year=" + year +
				", rating=" + rating +
				", place='" + place + '\'' +
				'}';
	}

}
