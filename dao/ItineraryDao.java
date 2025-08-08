package dao;

import java.sql.Date;
import java.util.List;

import model.Itinerary;

public interface ItineraryDao {
	// Create
	void add(Itinerary itinerary);
	public boolean addItinerary(String country, String destination, Date start, Date end, String note, int member_id);
	
	// Read
	List<Itinerary> selectAll();
	Itinerary selectById(int id);
	public List<Itinerary> getById(int member_id);

	// Update
	void update(Itinerary itinerary);
	
	// Delete
	void delete(Itinerary itinerary);
}
