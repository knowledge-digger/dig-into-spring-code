package dig.into.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

	@GetMapping(value="/main.do")
	public String main() {
		return "main";
	}
	
    @GetMapping(value="/member.do")
    public String member() {
    	return "member";
    }
    
    @GetMapping(value="/admin.do")
    public String admin() {
    	return "admin";
    }
}
