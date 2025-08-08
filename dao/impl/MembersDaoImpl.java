package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dao.MembersDao;
import model.Members;
import util.DbConnection;

public class MembersDaoImpl implements MembersDao {

	public static void main(String[] args) {
		
	}
	
	private static Connection conn = DbConnection.connectDb();

	@Override
	public boolean add(Members member) {
		String sql = "INSERT INTO Members (username, password, name) VALUES (?, ?, ?)";
        try {
        	PreparedStatement PS = conn.prepareStatement(sql);
            PS.setString(1, member.getUsername());
            PS.setString(2, member.getPassword());
            PS.setString(3, member.getName());
            return PS.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public Members login(String username, String password) {
		String sql = "SELECT * FROM Members WHERE username = ? AND password = ?";
        try {
        	PreparedStatement PS = conn.prepareStatement(sql);
            PS.setString(1, username);
            PS.setString(2, password);
            ResultSet rs = PS.executeQuery();
            if (rs.next()) {
                Members member = new Members();
                member.setId(rs.getInt("id"));
                member.setEmail(rs.getString("email"));
                member.setUsername(rs.getString("username"));
                member.setPassword(rs.getString("password"));
                member.setName(rs.getString("name"));
                return member;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public Members get(int id) {
		String sql = "SELECT * FROM Members WHERE id = ?";
        try {
        	PreparedStatement PS = conn.prepareStatement(sql);
            PS.setInt(1, id);
            ResultSet RS = PS.executeQuery();
            if (RS.next()) {
                Members member = new Members();
                member.setId(RS.getInt("id"));
                member.setUsername(RS.getString("username"));
                member.setPassword(RS.getString("password"));
                member.setName(RS.getString("name"));
                return member;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public boolean update(Members member) {
		String sql = "UPDATE Members SET name = ?, password = ?, email = ? WHERE id = ?";
	    try {
	    	PreparedStatement PS = conn.prepareStatement(sql);
	        PS.setString(1, member.getName());
	        PS.setString(2, member.getPassword());
	        PS.setString(3, member.getEmail());
	        PS.setInt(4, member.getId());
	        return PS.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
}
