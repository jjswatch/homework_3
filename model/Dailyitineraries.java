package model;

public class Dailyitineraries {
    private int id;
    private int itinerary_id;
    private int day_number;
    private String time;
    private String location;
    private String category;
    private String note;
    private int cost;
    private String address;

    public Dailyitineraries(int itinerary_id, int day_number, String time, String location, String category, String note, int cost, String address) {
        this.itinerary_id = itinerary_id;
        this.day_number = day_number;
        this.time = time;
        this.location = location;
        this.category = category;
        this.note = note;
        this.cost = cost;
        this.address = address;
    }

    public Dailyitineraries(int id, int itinerary_id, int day_number, String time, String location, String category, String note, int cost, String address) {
        this.id = id;
        this.itinerary_id = itinerary_id;
        this.day_number = day_number;
        this.time = time;
        this.location = location;
        this.category = category;
        this.note = note;
        this.cost = cost;
        this.address = address;
    }

    public int getId() {
    	return id;
    }
    public int getItinerary_id() { 
    	return itinerary_id; 
    }
    public int getDay_number() { 
    	return day_number; 
    }
    public String getTime() { 
    	return time; 
    }
    public String getLocation() { 
    	return location; 
    }
    public String getCategory() { 
    	return category; 
    }
    public String getNote() { 
    	return note; 
    }
    public int getCost() { 
    	return cost; 
    }
    public String getAddress() { 
    	return address; 
    }

    public void setId(int id) { 
    	this.id = id; 
    }
    public void setItinerary_id(int itinerary_id) { 
    	this.itinerary_id = itinerary_id; 
    }
    public void setDay_number(int day_number) { 
    	this.day_number = day_number; 
    }
    public void setTime(String time) { 
    	this.time = time; 
    }
    public void setLocation(String location) { 
    	this.location = location; 
    }
    public void setCategory(String category) { 
    	this.category = category; 
    }
    public void setNote(String note) { 
    	this.note = note; 
    }
    public void setCost(int cost) { 
    	this.cost = cost; 
    }
    public void setAddress(String address) { 
    	this.address = address; 
    }
}
