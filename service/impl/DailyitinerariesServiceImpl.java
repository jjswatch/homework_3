package service.impl;

import dao.DailyitinerariesDao;
import dao.impl.DailyitinerariesDaoImpl;
import model.Dailyitineraries;
import service.DailyitinerariesService;

import java.util.List;

public class DailyitinerariesServiceImpl implements DailyitinerariesService {

    private DailyitinerariesDao dao = new DailyitinerariesDaoImpl();

    @Override
    public boolean addDaily(Dailyitineraries d) {
        return dao.add(d);
    }

    @Override
    public boolean updateDaily(Dailyitineraries d) {
        return dao.update(d);
    }

    @Override
    public boolean deleteDaily(int id) {
        return dao.delete(id);
    }

    @Override
    public Dailyitineraries getById(int id) {
        return dao.getById(id);
    }

    @Override
    public List<Dailyitineraries> getByItineraryId(int itineraryId) {
        return dao.getByItineraryId(itineraryId);
    }
}
