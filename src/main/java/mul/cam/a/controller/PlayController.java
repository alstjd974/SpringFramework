package mul.cam.a.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PlayController {
	
	@RequestMapping(value = "playlist.do")
	public String playlist() {
		
		return "playlist";
	}
}
