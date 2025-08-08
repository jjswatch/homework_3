package service;

import java.util.List;
import model.Dailyitineraries;

public interface DailyitinerariesService {
    boolean addDaily(Dailyitineraries d);
    boolean updateDaily(Dailyitineraries d);
    boolean deleteDaily(int id);
    Dailyitineraries getById(int id);
    List<Dailyitineraries> getByItineraryId(int itineraryId);
}
