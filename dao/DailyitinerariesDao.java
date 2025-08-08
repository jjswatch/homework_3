package dao;

import java.util.List;
import model.Dailyitineraries;

public interface DailyitinerariesDao {
    boolean add(Dailyitineraries d);
    boolean update(Dailyitineraries d);
    boolean delete(int id);
    Dailyitineraries getById(int id);
    List<Dailyitineraries> getByItineraryId(int itineraryId);
}
