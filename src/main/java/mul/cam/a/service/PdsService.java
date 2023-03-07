package mul.cam.a.service;

import java.util.List;

import mul.cam.a.dto.PdsDto;
import mul.cam.a.dto.PdsParam;

public interface PdsService {

	List<PdsDto> pdslist(PdsParam param);
	
	boolean uploadPds(PdsDto dto);
	
	PdsDto getPds(int seq);
	
	void downCount(int seq);
	
	boolean updatePds(PdsDto pds);
}
