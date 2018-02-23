package com.mdoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mdoc.model.App;
import com.mdoc.repository.AppRepository;

@Controller
public class AppsController {

	@Autowired
	AppRepository app_repo;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(){
		App app = new App();
		app.setAppName("test");
		app_repo.save(app);
		return app_repo.findAll().toString();
	}
	
}
