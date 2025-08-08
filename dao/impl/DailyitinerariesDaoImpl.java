package dao.impl;

import dao.DailyitinerariesDao;
import model.Dailyitineraries;
import util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DailyitinerariesDaoImpl implements DailyitinerariesDao {

    @Override
    public boolean add(Dailyitineraries d) {
        String sql = "INSERT INTO Dailyitineraries (itinerary_id, day_number, time, location, category, note, cost, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbConnection.connectDb();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, d.getItinerary_id());
            ps.setInt(2, d.getDay_number());
            ps.setString(3, d.getTime());
            ps.setString(4, d.getLocation());
            ps.setString(5, d.getCategory());
            ps.setString(6, d.getNote());
            ps.setInt(7, d.getCost());
            ps.setString(8, d.getAddress());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Dailyitineraries d) {
        String sql = "UPDATE Dailyitineraries SET day_number=?, time=?, location=?, category=?, note=?, cost=?, address=? WHERE id=?";
        try (Connection conn = DbConnection.connectDb();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, d.getDay_number());
            ps.setString(2, d.getTime());
            ps.setString(3, d.getLocation());
            ps.setString(4, d.getCategory());
            ps.setString(5, d.getNote());
            ps.setInt(6, d.getCost());
            ps.setString(7, d.getAddress());
            ps.setInt(8, d.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Dailyitineraries WHERE id = ?";
        try (Connection conn = DbConnection.connectDb();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Dailyitineraries getById(int id) {
        String sql = "SELECT * FROM Dailyitineraries WHERE id = ?";
        try (Connection conn = DbConnection.connectDb();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extract(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Dailyitineraries> getByItineraryId(int itineraryId) {
        List<Dailyitineraries> list = new ArrayList<>();
        String sql = "SELECT * FROM Dailyitineraries WHERE itinerary_id = ? ORDER BY day_number, id";
        try (Connection conn = DbConnection.connectDb();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, itineraryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(extract(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    private Dailyitineraries extract(ResultSet rs) throws SQLException {
        return new Dailyitineraries(
                rs.getInt("id"),
                rs.getInt("itinerary_id"),
                rs.getInt("day_number"),
                rs.getString("time"),
                rs.getString("location"),
                rs.getString("category"),
                rs.getString("note"),
                rs.getInt("cost"),
                rs.getString("address")
        );
    }
}
