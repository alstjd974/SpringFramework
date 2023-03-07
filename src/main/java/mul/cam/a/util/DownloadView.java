package mul.cam.a.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import mul.cam.a.service.PdsService;

// window 위에 window에 작업
public class DownloadView extends AbstractView {

	@Autowired // singleton이기 때문에 가능
	PdsService service;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("DownloadView renderMergedOutputModel");

		File downloadFile = (File) model.get("downloadFile");
		String filename = (String) model.get("filename");
		int seq = (Integer) model.get("seq");
		
		response.setContentType(this.getContentType());
		response.setContentLength((int)downloadFile.length());
		
		//이 설정은 한글명 파일일 경우 적용된다.
		filename = URLEncoder.encode(filename, "utf-8");
		
		// download창에 실제로 나오게 만드는 부분
		// 원본파일명으로 바꾸어주는 부분
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";");
		// Encoding 방식 → 2진수
		response.setHeader("Content-Transfer-Encoding", "binary;");
		// 내용의 길이
		response.setHeader("Content-Length", "" + downloadFile.length());
		// 저장 → x
		response.setHeader("Pragma", "no-cache;"); 
		// 기한 → x
		response.setHeader("Expires", "-1;");
		
		OutputStream os = response.getOutputStream();
		FileInputStream fis = new FileInputStream(downloadFile);
		
		// 실제 데이터를 기입하는 부분
		FileCopyUtils.copy(fis, os);
		
		//down load count 증가 시키는 부분
		service.downCount(seq);
		
		if(fis != null) {
			fis.close();
		}
	}

}
