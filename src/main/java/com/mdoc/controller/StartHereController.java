package com.mdoc.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.mdoc.model.TableOfContents;
import com.mdoc.utility.Utilities;

/**
 * This is the starting point to the web app.
 * @author yeswanth kumar
 *
 */
@Controller
public class StartHereController {
	/**
     * This method is called after the application is started. It will give the
     * welcome page for the application.
     * 
     * @param session
     * @return ModelAndView
     * @throws IOException
     */
    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String firstStop(ModelAndView mav, HttpSession session) throws IOException {
    	/*  
    	 * might be requiring sessin based redirection here.
    	 */
    	// Here we are redirecting to the super-admin interface.
    	return "redirect:/super-admin/login";
    }

}
