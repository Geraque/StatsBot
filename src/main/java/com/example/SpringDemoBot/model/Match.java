package com.example.SpringDemoBot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Таблица с основной информацией
@Entity
@Table(name = "desmond")
public class Match {

	private long id;
	private String name;
	private String data;
	private float rating;
	private int smokeKill;
	private int openKill;
	private int threeKill;
	private int fourKill;
	private int ace;
	private int flash;
	private int trade;
	private int wallbang;
	private int clutchOne;
	private int clutchTwo;
	private int clutchThree;
	private int clutchFour;
	private int clutchFive;
	private String type;

	
	public Match() {
		
	}


	public Match(String name, String data, float rating, int smokeKill, int openKill, int threeKill, int fourKill, int ace, int flash, int trade, int wallbang, int clutchOne, int clutchTwo, int clutchThree, int clutchFour, int clutchFive, String type) {
		this.name = name;
		this.data = data;
		this.rating = rating;
		this.smokeKill = smokeKill;
		this.openKill = openKill;
		this.threeKill = threeKill;
		this.fourKill = fourKill;
		this.ace = ace;
		this.flash = flash;
		this.trade = trade;
		this.wallbang = wallbang;
		this.clutchOne = clutchOne;
		this.clutchTwo = clutchTwo;
		this.clutchThree = clutchThree;
		this.clutchFour = clutchFour;
		this.clutchFive = clutchFive;
		this.type = type;
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

	@Column(name = "data", nullable = false)
	public String getData() {return data;}
	public void setData(String data) {this.data = data;}

	@Column(name = "rating", nullable = false)
	public float getRating() {return rating;}
	public void setRating(float rating) {this.rating = rating;}

	@Column(name = "smoke_kill", nullable = false)
	public int getSmokeKill() {return smokeKill;}
	public void setSmokeKill(int smokeKill) {this.smokeKill = smokeKill;}

	@Column(name = "open_kill", nullable = false)
	public int getOpenKill() {return openKill;}
	public void setOpenKill(int openKill) {this.openKill = openKill;}

	@Column(name = "three_kill", nullable = false)
	public int getThreeKill() {return threeKill;}
	public void setThreeKill(int threeKill) {this.threeKill = threeKill;}

	@Column(name = "four_kill", nullable = false)
	public int getFourKill() {return fourKill;}
	public void setFourKill(int fourKill) {this.fourKill = fourKill;}

	@Column(name = "ace", nullable = false)
	public int getAce() {return ace;}
	public void setAce(int ace) {this.ace = ace;}

	@Column(name = "flash", nullable = false)
	public int getFlash() {return flash;}
	public void setFlash(int flash) {this.flash = flash;}

	@Column(name = "trade", nullable = false)
	public int getTrade() {return trade;}
	public void setTrade(int trade) {this.trade = trade;}

	@Column(name = "wallbang", nullable = false)
	public int getWallbang() {return wallbang;}
	public void setWallbang(int wallbang) {this.wallbang = wallbang;}

	@Column(name = "clutch_one", nullable = false)
	public int getClutchOne() {return clutchOne;}
	public void setClutchOne(int clutchOne) {this.clutchOne = clutchOne;}

	@Column(name = "clutch_two", nullable = false)
	public int getClutchTwo() {return clutchTwo;}
	public void setClutchTwo(int clutchTwo) {this.clutchTwo = clutchTwo;}

	@Column(name = "clutch_three", nullable = false)
	public int getClutchThree() {return clutchThree;}
	public void setClutchThree(int clutchThree) {this.clutchThree = clutchThree;}

	@Column(name = "clutch_four", nullable = false)
	public int getClutchFour() {return clutchFour;}
	public void setClutchFour(int clutchFour) {this.clutchFour = clutchFour;}

	@Column(name = "clutch_five", nullable = false)
	public int getClutchFive() {return clutchFive;}
	public void setClutchFive(int clutchFive) {this.clutchFive = clutchFive;}

	@Column(name = "type", nullable = false)
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}

	@Override
	public String toString() {
		return "Desmond{" +
				"id=" + id +
				", name='" + name + '\'' +
				", data='" + data + '\'' +
				", rating=" + rating +
				", smokeKill=" + smokeKill +
				", openKill=" + openKill +
				", threeKill=" + threeKill +
				", fourKill=" + fourKill +
				", ace=" + ace +
				", flash=" + flash +
				", trade=" + trade +
				", wallbang=" + wallbang +
				", clutchOne=" + clutchOne +
				", clutchTwo=" + clutchTwo +
				", clutchThree=" + clutchThree +
				", clutchFour=" + clutchFour +
				", clutchFive=" + clutchFive +
				", type='" + type + '\'' +
				'}';
	}

}
