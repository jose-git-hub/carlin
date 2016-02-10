package controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import entity.Sample;

@RestController
public class Controller {
	
	private static final Logger logger = Logger.getLogger(Controller.class);
	
	public static final String URL = "/submit";
	
	@RequestMapping(value = URL, method=RequestMethod.POST)
	public void processRequest(
		    @RequestBody
			Sample sample
		 ) {
		logger.info("received");
		logger.warn(sample); 
			
	}
}
