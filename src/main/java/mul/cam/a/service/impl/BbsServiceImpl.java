package mul.cam.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mul.cam.a.dao.BbsDao;
import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;
import mul.cam.a.service.BbsService;

@Service
public class BbsServiceImpl implements BbsService {
	
	@Autowired
	BbsDao dao;

	@Override
	public List<BbsDto> bbslist(BbsParam bbs) {
		return dao.bbslist(bbs);
	}

	@Override
	public int getAllBbs(BbsParam bbs) {
		return dao.getAllBbs(bbs);
	}

	@Override
	public boolean writeBbs(BbsDto dto) {
		int count = dao.writeBbs(dto);
		return count>0? true:false;
	}

	@Override
	public BbsDto getBbs(int seq) {
		
		return dao.getBbsDto(seq);
	}

	@Override
	public boolean bbsUpdate(BbsDto dto) {
		int count = dao.bbsUpdate(dto);
		return count>0? true:false;
	}

	@Override
	public boolean bbsDelete(int seq) {
		int count = dao.bbsDelete(seq);
		return count>0? true:false;
	}

	@Override
	public void readcount(int seq) {
		dao.readcount(seq);
	}

	@Override
	public boolean answerBbs(BbsDto dto) {
		dao.answerUp(dto);
		int count = dao.answerIn(dto);
		return count>0? true:false;
	}

	@Override
	public boolean commentWrite(BbsComment bbs) {
		int count = dao.commentWrite(bbs);
		return count>0? true:false;
	}

	@Override
	public List<BbsComment> commentList(int seq) {
		return dao.commentList(seq);
	}

	


	
}
