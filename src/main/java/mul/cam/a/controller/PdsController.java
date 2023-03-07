package mul.cam.a.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import mul.cam.a.dto.PdsDto;
import mul.cam.a.dto.PdsParam;
import mul.cam.a.service.PdsService;
import mul.cam.a.util.PdsUtil;

@Controller
public class PdsController {
	
	@Autowired
	PdsService service;
	
	@RequestMapping(value = "pdslist.do", method = RequestMethod.GET)
	public String pdslist(Model model , PdsParam param,  String choice, String search) {
		List<PdsDto> list = service.pdslist(param);

		
		model.addAttribute("pdslist", list);
		
		return "pdslist";
	}
	
	@GetMapping(value = "pdswrite.do")
	public String pdswrite() {
		return "pdswrite";
	}
	
	//파일 업로드
	@PostMapping(value = "pdsupload.do")
	public String pdsupload(Model model, PdsDto dto, HttpServletRequest req, //파일(upload) 경로를 얻어 오기 위해
			@RequestParam(value="fileload", required = false) // 실패시 재업로드 = false
			MultipartFile fileload) {  //다양한 데이터(binary 데이터 → file 데이터타입) 
	
		//filename 취득             (원본파일명)
		String filename = fileload.getOriginalFilename();
		
		//        (DB에 저장되는 파일명)
		dto.setFilename(filename); // 원본 파일명 설정(DB에 넣기 위함)
		
		// upload의 경로 설정 
		
		//server
		String fupload = req.getServletContext().getRealPath("/upload"); // (서버 on/off시 업로드 파일이 사라지는 경우가 있으므로 당황하지말고 재업로드)
		
		// folder
		//String fupload = "d:\\temp";
		
		System.out.println("fupload:" + fupload );
		
		// 파일명을 충돌되지 않는 명칭(Date)으로 변경 (ex)time.확장자명)
		String newfilename = PdsUtil.getNewFileName(filename);
		
		// 변경된 파일명      (실제로 올가가는 파일)
		dto.setNewfilename(newfilename);
		
		File file = new File(fupload + "/" + newfilename);
		
		// io 소속
		try {
			// ※핵심※ 실제로 파일 생성 + 기입 = 업로드 되는 부분
			FileUtils.writeByteArrayToFile(file, fileload.getBytes());
			
			// db에 저장
			service.uploadPds(dto);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		// controller → controller 이동 redirect
		return "redirect:/pdslist.do";
	}
	
	@PostMapping(value = "filedownLoad.do")
	// controller -- seq, filename, newfilename -→ DownloadView
	public String filedownLoad(HttpServletRequest req, Model model, int seq, String filename, String newfilename) {
		
		// 경로 취득 
		//server
		String fupload = req.getServletContext().getRealPath("/upload");
		
		// folder
	    //String fupload = "d:\\temp";
				
		
		// 다운로드 받을 파일
		File downloadFile = new File(fupload + "/" + newfilename);
		
		
		model.addAttribute("downloadFile", downloadFile); // (file) 실제 업로드 되어있는 filename 경로/434143.txt
		model.addAttribute("filename", filename); // (String) 원 파일명 filename ex) abc.txt
		model.addAttribute("seq", seq); // (int) download count를 증가 시켜주기 위해서 
		
		return "downloadView";
	}
	
	@GetMapping(value = "pdsdetail.do")
	public String pdsdetail(Model model, int seq) {
		
		PdsDto pds = service.getPds(seq);
		
		model.addAttribute("pds", pds);
		
		return "pdsdetail";
	}
	
	@GetMapping(value = "pdsupdate.do")
	public String pdsupdate(Model model, int seq) {
		
		PdsDto pds = service.getPds(seq);
		
		model.addAttribute("pds", pds);
		
		return "pdsupdate";
	} 
	
	@PostMapping(value="pdsupdateAf.do")
	public String pdsupdateAf(Model model, PdsDto dto, HttpServletRequest req, 
			@RequestParam(value="fileload", required = false) 
			MultipartFile fileload) {
		
		String orginalFileName = fileload.getOriginalFilename();
		
		if(orginalFileName != null && !orginalFileName.equals("")) { //파일이 변경 되었다.
			
			String newfilename = PdsUtil.getNewFileName(orginalFileName);
			
			dto.setFilename(orginalFileName);
			dto.setNewfilename(newfilename);
			
			//경로 취득
			String fupload = req.getServletContext().getRealPath("/upload");
			
			File file = new File(fupload + "/" + newfilename);
			
			try {
				// 새로운 파일로 업로드
				FileUtils.writeByteArrayToFile(file, fileload.getBytes());
				
				// DB갱신
				service.updatePds(dto);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}else { //파일이 변경되지 않음
			service.updatePds(dto);
		}
		
		return "redirect:/pdsdetail.do?seq=" + dto.getSeq();
		
	}
}
