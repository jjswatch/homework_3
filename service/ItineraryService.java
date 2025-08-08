package service;

import java.util.List;

import model.Itinerary;

public interface ItineraryService {
	// Create
	void addItinerary(Itinerary itinerary);
	
	// Read
	List<Itinerary> getAllItinerary();
	public List<Itinerary> getItinerariesByMemberId(int memberId);
	Itinerary getItineraryByItineraryId(int itineraryId);
	
	// Update
	boolean updateItinerary(Itinerary itinerary);
	
	// Delete
	boolean deleteItinerary(Itinerary itinerary);
}
