package service.impl;

import dao.impl.MembersDaoImpl;
import model.Members;
import service.MembersService;

public class MembersServiceImpl implements MembersService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	private static MembersDaoImpl MDI = new MembersDaoImpl();

	@Override
	public boolean addMember(Members member) {
		Members Member = MDI.get(member.getId());
		if (Member == null) {
			MDI.add(member);
			return true;
		}
		return false;
	}
	
	@Override
	public Members memberLogin(String username, String password) {
		return MDI.login(username, password);
	}

	@Override
	public boolean updateMember(Members member) {
		return MDI.update(member);
	}
}
