package br.com.gostoudaaula.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/")
public class SiteController {

	@RequestMapping(method = GET)
	public String index() {
		return "index";
	}
}
