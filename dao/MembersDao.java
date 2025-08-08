package dao;

import model.Members;

public interface MembersDao {
	// Create
	public boolean add(Members member);
	
	// Read
	public Members login(String username, String password);
	public Members get(int id);
	
	// Update
	public boolean update(Members member);
	
	// Delete
}
