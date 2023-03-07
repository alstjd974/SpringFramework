package mul.cam.a.dao;

import java.util.List;

import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;

public interface BbsDao {
	
	List<BbsDto> bbslist(BbsParam bbs);
	
	int getAllBbs(BbsParam bbs);
	int writeBbs(BbsDto dto);
	
	BbsDto getBbsDto(int seq)
	;
	void readcount(int seq);
	
	int bbsUpdate(BbsDto dto);
	int bbsDelete(int seq);
	
	int answerUp(BbsDto dto);
	int answerIn(BbsDto dto);
	
	//댓글
	int commentWrite(BbsComment bbs);
	
	List<BbsComment> commentList(int seq);
}
