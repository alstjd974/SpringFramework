package mul.cam.a.dao;

import java.util.List;

import mul.cam.a.dto.MemberDto;

public interface MemberDao {
	// DAO: JDBC 기능을 좀 더 편리하게 사용할 수 있도록 한다.
	List<MemberDto> allMember();
	
	int idcheck(String id);
	
	int addMember(MemberDto dto);
	
	MemberDto login(MemberDto dto);
}
