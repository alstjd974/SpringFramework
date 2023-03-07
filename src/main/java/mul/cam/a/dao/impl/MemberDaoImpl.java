package mul.cam.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mul.cam.a.dao.MemberDao;
import mul.cam.a.dto.MemberDto;

@Repository // ==저장소 mybatis와 데이터를 주고 받는다.
public class MemberDaoImpl implements MemberDao {
	//mybatis 접근(생성)
	
	@Autowired // 클래스 자동생성
	SqlSession session;
	
	String ns = "Member.";
	
	@Override
	public List<MemberDto> allMember() {
		
		return session.selectList(ns + "allMember");
	}

	@Override
	public int idcheck(String id) {
		return session.selectOne(ns + "idcheck",id);
	}

	@Override
	public int addMember(MemberDto dto) {
		return session.insert(ns + "addmember", dto);
	}

	@Override
	public MemberDto login(MemberDto dto) {
		//MemberDto mem = session.selectOne(ns + "login", dto);
		return session.selectOne(ns + "login", dto);
	}
	
	

}
