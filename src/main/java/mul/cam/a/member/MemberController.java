package mul.cam.a.member;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mul.cam.a.dto.MemberDto;
import mul.cam.a.service.MemberService;

@Controller
public class MemberController {
	// service 접근(생성)

	@Autowired
	MemberService service;

	// 이동
	// @GetMapping(value = "login.do")
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login() {
		//System.out.println("MemberController login()" + new Date());

		return "login"; // login.jsp로 이동
	}

	@RequestMapping(value = "regi.do", method = RequestMethod.GET)
	public String regi() {
		//System.out.println("MemberController regi" + new Date());

		return "regi";
	}

	@ResponseBody
	@RequestMapping(value = "idcheck.do", method = RequestMethod.POST)
	public String idcheck(String id) {
		//System.out.println("id: " + id);

		boolean isS = service.idcheck(id);

		if (isS == true) { // 아이디가 있음
			return "NO";
		}

		return "YES"; // 아이디가 없음
	}

	@RequestMapping(value = "regiAf.do", method = RequestMethod.POST)
	public String regiAf(Model model, MemberDto dto) {
		//System.out.println("MemberController regiAf " + new Date());

		boolean isS = service.addMember(dto);
		String message = "";
		if (isS) {
			message = "MEMBER_ADD_YES";
		} else {
			message = "MEMBER_ADD_NO";
		}

		model.addAttribute("message", message);

		return "message";
	}

	@RequestMapping(value = "loginAf.do", method = RequestMethod.POST)
	                     // 세션 사용을 원할 때 Request를 불러 사용한다. //우리 설정한 데이터를 제일 맨뒤어 놓은걸 선호. 
	public String loginAf(HttpServletRequest req, Model model, MemberDto dto) {
		//System.out.println("MemberController login" + new Date());

		MemberDto mem = service.login(dto);
		
		// String id, String pwd가 parameter일 경우
		// service.login(new MemberDto(id,pwd,name,email,auth));

		String msg = "";
		if (mem != null) {
			req.getSession().setAttribute("login", mem);
			//req.getSession().setMaxInactiveInterval(60 * 2);
			msg = "LOGIN_OK";
		} else {
			msg = "LOGIN_FAIL";
		}
		model.addAttribute("login", msg);
		
		return "message";
	}
	
	@RequestMapping(value = "sessionOut.do", method = RequestMethod.GET)
	public String sessionOut(Model model) {
		String sessionOut = "logout";
		
		model.addAttribute("sessionOut", sessionOut);
		
		return "message";
	}
}
