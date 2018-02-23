package com.mdoc.controller;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.mdoc.model.User;
import com.mdoc.service.UserService;
import com.mdoc.utility.Utilities;

/**
 * This controller will provide the basic operations fo users. Like
 * signing-in,registering a new user.
 * 
 * @author navinkumark
 *
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    Properties properties;

    
    /**
     * This is super admin login view
     * @param mav
     * @param request
     * @return
     */
    @RequestMapping(value = "/super-admin/login", method = RequestMethod.GET)
    public ModelAndView superAdminLogin(ModelAndView mav, HttpServletRequest request) {
    	mav.addObject("appName", "Documento");
		mav.setViewName("login");
		return mav;
    	/*
    	
		*/
		
    }
    @RequestMapping(value = "/submitlogin", method = RequestMethod.POST)
    public ModelAndView submitLogin(ModelAndView mav, HttpServletRequest request) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		properties = new Utilities().loadProperties();
		User user = userService.findUserByEmail(auth.getName());
		request.getSession().setAttribute("user", user);
		mav.addObject("user", user);
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			mav.setView(new RedirectView("/documento/admin/home"));
			return mav;
		}
	    String app = (String)mav.getModel().get("appName");
		mav.addObject("appName", "Documento");
		mav.setViewName("login");
		return mav;
    }

    /**
     * Opens the registration page to register a new user.
     * 
     * @return ModelAndView
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(ModelAndView mav) {

	User user = new User();
	mav.addObject("user", user);
	mav.addObject("appName", properties.getProperty("appName"));
	mav.addObject("copyRight", properties.getProperty("copyRight"));
	mav.setViewName("registration");
	return mav;
    }

    /**
     * Gets the form input from registration page and adds the user to the
     * database.
     * 
     * @param user
     * @param bindResult
     * @return ModelAndView
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(ModelAndView mav, @Valid User user, BindingResult bindResult) {

	User userExists = userService.findUserByEmail(user.getEmail());

	if (userExists != null) {
	    bindResult.rejectValue("email", "error.user", "User already exists with Email id");
	}

	if (bindResult.hasErrors()) {
	    mav.addObject("appName", properties.getProperty("appName"));
	    mav.addObject("copyRight", properties.getProperty("copyRight"));
	    mav.setViewName("registration");
	} else {
	    userService.saveUser(user);
	    mav.addObject("successMessage", "User registered successfully!!");
	    /*mav.addObject("user", new User());
	    mav.setViewName("registration");*/
	    mav.setView(new RedirectView("/docs-app/admin/home"));
	}
	return mav;
    }


}
