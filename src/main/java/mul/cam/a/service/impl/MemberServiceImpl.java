package mul.cam.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mul.cam.a.dao.MemberDao;
import mul.cam.a.dto.MemberDto;
import mul.cam.a.service.MemberService;

@Service // DB에서 가져온 데이터를 편집// dao에서 데이터를 받아 편집 후 controller에 보낸다. 
public class MemberServiceImpl implements MemberService {
	// Dao 접근(생성)
	
	@Autowired
	// MemberDao dao = new MemberDaoImpl();
	MemberDao dao;
	
	@Override
	public List<MemberDto> allMember() {
		return dao.allMember();
	}

	@Override
	public boolean idcheck(String id) {
		int count = dao.idcheck(id);
		return count>0? true:false;
	}

	@Override
	public boolean addMember(MemberDto dto) {
		
		int count = dao.addMember(dto);
		return count>0?true:false;
	}

	@Override
	public MemberDto login(MemberDto dto) {
		return dao.login(dto);
	}
	
	
	 
	
	
}
