package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.MemberDAO;
import dao.MemberDAOImpl;
import domain.MemberVO;

public class MemberServiceImpl implements MemberService {
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	private MemberDAO mdao;
	
	public MemberServiceImpl() {
		mdao = new MemberDAOImpl();
	}

	@Override
	public int join(MemberVO mvo) {
		// TODO Auto-generated method stub
		return mdao.join(mvo);
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		// TODO Auto-generated method stub
		return mdao.login(mvo);
	}

	@Override
	public int lastLogin(String id) {
		// TODO Auto-generated method stub
		return mdao.lastLogin(id);
	}
}