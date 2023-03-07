package mul.cam.a.util;

import java.util.Date;

public class PdsUtil {
	
	// 파일명 → 변경(time)

	// filename     newfilename
	// myfile.txt → 432423432.txt		
	
	public static String getNewFileName(String filename) {
		
		String newfilename =  "";
		String fp = ""; //파일명의 위치
		
		if(filename.indexOf('.') >= 0) { // 확장자명이 있음
			fp = filename.substring(filename.indexOf('.')); // .txt
			newfilename = new Date().getTime() + fp; // String → 432423432.txt
		}else { //확장자명이 없음(-1)
			newfilename = new Date().getTime() + ".back"; // 432423432 + .back
		}
		
		
		return newfilename;
	}
}
