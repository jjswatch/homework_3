package model;

import java.util.Date;

public class Itinerary {
	private int id;
	private String destination;
	private Date start_date;
	private Date end_date;
	private String note;
	
	
	private int member_id;
	private String country;

	
	public Itinerary() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Itinerary(String country, String destination, Date start_date, Date end_date, String note, int member_id) {
		super();
		this.country = country;
		this.destination = destination;
		this.start_date = start_date;
		this.end_date = end_date;
		this.note = note;
		this.member_id = member_id;
	}
	public Itinerary(int id, String country, String destination, Date start_date, Date end_date, String note, int member_id) {
		super();
		this.id = id;
		this.country = country;
		this.destination = destination;
		this.start_date = start_date;
		this.end_date = end_date;
		this.note = note;
		this.member_id = member_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
