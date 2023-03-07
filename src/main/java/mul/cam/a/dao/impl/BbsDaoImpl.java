package mul.cam.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mul.cam.a.dao.BbsDao;
import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;

@Repository
public class BbsDaoImpl implements BbsDao {

	// singleton
	@Autowired
	SqlSession session;

	String ns = "Bbs.";

	@Override
	public List<BbsDto> bbslist(BbsParam bbs) {
		return session.selectList(ns + "bbslist", bbs);
	}

	@Override
	public int getAllBbs(BbsParam bbs) {
		return session.selectOne(ns + "getAllBbs", bbs);
	}

	@Override
	public int writeBbs(BbsDto dto) {
		return session.insert(ns + "writeBbs", dto);
	}

	@Override
	public BbsDto getBbsDto(int seq) {
		return session.selectOne(ns + "getBbs", seq);
	}

	@Override
	public void readcount(int seq) {
		session.update(ns + "readcount", seq);
	}

	@Override
	public int bbsUpdate(BbsDto dto) {
		return session.update(ns + "bbsUpdate", dto);
	}

	@Override
	public int bbsDelete(int seq) {
		return session.update(ns + "bbsDelete", seq);
	}

	@Override
	public int answerUp(BbsDto dto) {
		return session.update(ns + "answerUp", dto);
	}

	@Override
	public int answerIn(BbsDto dto) {
		return session.insert(ns + "answerIn", dto);
	}

	@Override
	public int commentWrite(BbsComment bbs) {
		return session.insert(ns + "commentWrite", bbs);
	}

	@Override
	public List<BbsComment> commentList(int seq) {
		return session.selectList(ns + "commentList", seq);
	}

}
