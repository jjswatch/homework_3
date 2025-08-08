package service.impl;

import java.util.List;

import dao.impl.ItineraryDaoImpl;
import model.Itinerary;
import service.ItineraryService;

public class ItineraryServiceImpl implements ItineraryService {

	public static void main(String[] args) {
	
	}
	
	private static ItineraryDaoImpl IDI = new ItineraryDaoImpl();

	@Override
	public void addItinerary(Itinerary itinerary) {
		IDI.add(itinerary);
	}

	@Override
	public List<Itinerary> getAllItinerary() {
		return IDI.selectAll();
	}
	
	@Override
	public List<Itinerary> getItinerariesByMemberId(int memberId) {
		return IDI.getById(memberId);
	}

	@Override
	public boolean updateItinerary(Itinerary itinerary) {
		boolean result = false;
		if (IDI.selectById(itinerary.getId()) != null) { // 確認資料是否存在
			result = true;
			IDI.update(itinerary); // 直接呼叫使用最新的內容
		}
		return result;
	}

	@Override
	public boolean deleteItinerary(Itinerary itinerary) {
		Itinerary deleteItinerary = IDI.selectById(itinerary.getId());
		boolean result = false;
		if (deleteItinerary != null) {
			result = true;
			IDI.delete(deleteItinerary);
		}
		return result;
	}

	@Override
	public Itinerary getItineraryByItineraryId(int itineraryId) {
		// TODO Auto-generated method stub
		return IDI.selectById(itineraryId);
	}
}
