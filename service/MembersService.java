package service;

import model.Members;

public interface MembersService {
	// Create
	boolean addMember(Members members);
	
	// Read
	public Members memberLogin(String username, String password);
	
	// Update	
	public boolean updateMember(Members member);
	
	// Delete
}
