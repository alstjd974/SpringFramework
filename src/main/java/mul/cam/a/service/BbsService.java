package mul.cam.a.service;

import java.util.List;

import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;

public interface BbsService {

	List<BbsDto> bbslist(BbsParam bbs);

	int getAllBbs(BbsParam bbs);
	boolean writeBbs(BbsDto dto);
	BbsDto getBbs(int seq);
	
	void readcount(int seq);
	
	boolean bbsUpdate(BbsDto dto);
	boolean bbsDelete(int seq);
	
	boolean answerBbs(BbsDto dto);
	
	boolean commentWrite(BbsComment bbs);
	
	List<BbsComment> commentList(int seq);
}
