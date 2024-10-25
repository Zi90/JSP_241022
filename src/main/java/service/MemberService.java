package service;

import domain.MemberVO;

public interface MemberService {

	int join(MemberVO mvo);

	MemberVO login(MemberVO mvo);

	int lastLogin(String id);
}

