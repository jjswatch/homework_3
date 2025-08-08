package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.ItineraryDao;
import model.Dailyitineraries;
import model.Itinerary;
import util.DbConnection;

public class ItineraryDaoImpl implements ItineraryDao {


	public static void main(String[] args) {
		List<Itinerary> l=new ItineraryDaoImpl().selectAll();
		
		for(Itinerary r:l)
		{
			System.out.println("id:"+r.getCountry()+"\tsum:"+r.getDestination());
		}
	}
	
	private static Connection conn = DbConnection.connectDb();
	
	@Override
	public void add(Itinerary itinerary) {
		String sql = "insert into Itineraries(country, destination, start_date, end_date, note, member_id) values (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement PS = conn.prepareStatement(sql);
			PS.setString(1, itinerary.getCountry());
			PS.setString(2, itinerary.getDestination());
			PS.setDate(3, new java.sql.Date(itinerary.getStart_date().getTime()));
			PS.setDate(4, new java.sql.Date(itinerary.getEnd_date().getTime()));
			PS.setString(5, itinerary.getNote());
			PS.setInt(6, itinerary.getMember_id());
			PS.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean addItinerary(String country, String destination, java.sql.Date start, java.sql.Date end, String note, int member_id) {
		String sql = "INSERT INTO Itineraries (member_id, country, destination, start_date, end_date, note) VALUES (?, ?, ?, ?, ?, ?)";
        try {
        	PreparedStatement PS = conn.prepareStatement(sql);
            PS.setInt(1, member_id);
            PS.setString(2, country);
            PS.setString(3, destination);
            PS.setDate(4, start);
            PS.setDate(5, end);
            PS.setString(6, note);
            return PS.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public List<Itinerary> selectAll() {
		String sql = "select * from itineraries";
		List<Itinerary> lists = new ArrayList<>();
		try {
			PreparedStatement PS = conn.prepareStatement(sql);
			ResultSet RS = PS.executeQuery();
			while (RS.next()) {
				Itinerary itinerary = new Itinerary();
				itinerary.setId(RS.getInt("id"));
				itinerary.setCountry(RS.getString("country"));
				itinerary.setDestination(RS.getString("destination"));
				itinerary.setStart_date(RS.getDate("start_date"));
				itinerary.setEnd_date(RS.getDate("end_date"));
				itinerary.setNote(RS.getString("note"));
				lists.add(itinerary);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}

	@Override
	public Itinerary selectById(int id) {
		String sql = "select * from itineraries where id = ?";
		Itinerary itinerary = null;
		try {
			PreparedStatement PS = conn.prepareStatement(sql);
			PS.setInt(1, id);
			ResultSet RS = PS.executeQuery();
			if (RS.next()) {
				itinerary = new Itinerary();
				itinerary.setId(RS.getInt("id"));
				itinerary.setCountry(RS.getString("country"));
				itinerary.setDestination(RS.getString("destination"));
				itinerary.setStart_date(RS.getDate("start_date"));
				itinerary.setEnd_date(RS.getDate("end_date"));
				itinerary.setNote(RS.getString("note"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itinerary;
	}

	@Override
	public List<Itinerary> getById(int memberId) {
		List<Itinerary> lists = new ArrayList<>();
        String sql = "SELECT id, country, destination, start_date, end_date, note FROM itineraries WHERE member_id = ?";
        try (Connection conn = DbConnection.connectDb();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, memberId);
            ResultSet RS = pstmt.executeQuery();

            while (RS.next()) {
				Itinerary itinerary = new Itinerary();
				itinerary.setId(RS.getInt("id"));
				itinerary.setCountry(RS.getString("country"));
				itinerary.setDestination(RS.getString("destination"));
				itinerary.setStart_date(RS.getDate("start_date"));
				itinerary.setEnd_date(RS.getDate("end_date"));
				itinerary.setNote(RS.getString("note"));
				lists.add(itinerary);
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
	}
	
	@Override
	public void update(Itinerary itinerary) {
		String sql = "update itineraries set country = ?, destination = ?, start_date = ?, end_date = ?, note = ? where id = ?";
		try {
			PreparedStatement PS = conn.prepareStatement(sql);
			PS.setString(1, itinerary.getCountry());
			PS.setString(2, itinerary.getDestination());
			PS.setDate(3, new java.sql.Date(itinerary.getStart_date().getTime()));
			PS.setDate(4, new java.sql.Date(itinerary.getEnd_date().getTime()));
			PS.setString(5, itinerary.getNote());
			PS.setInt(6, itinerary.getId());
			PS.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Itinerary itinerary) {
		String sql = "delete from itineraries where id = ?";
		try {
			PreparedStatement PS = conn.prepareStatement(sql);
			PS.setInt(1, itinerary.getId());
			PS.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
