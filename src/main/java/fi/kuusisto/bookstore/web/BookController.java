package fi.kuusisto.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class BookController {
	
	@GetMapping("/")
	public String index() {
		return "Hello!";
	}

}
