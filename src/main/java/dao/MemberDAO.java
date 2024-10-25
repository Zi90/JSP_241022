package dao;

import domain.MemberVO;

public interface MemberDAO {

	int join(MemberVO mvo);

	MemberVO login(MemberVO mvo);

	int lastLogin(String id);

}