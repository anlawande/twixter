package com.twixter.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twixter.dao.TwixterDao;
import com.twixter.model.Tweet;

@RestController
@RequestMapping("/tweet")
public class TweetController {
	
	@Autowired
	private TwixterDao dao;

	@RequestMapping(value="/friend")
	public @ResponseBody List<Tweet> getTweetsForPerson(HttpServletRequest req) {
		String personIdParam = req.getParameter("personId");
		String searchText = req.getParameter("search");
		
		int personId = 0;
		if (personIdParam == null) {
			//Throw 400 here
			return null;
		}
		try {
			personId = Integer.parseInt(personIdParam);
		}
		catch (NumberFormatException nfe) {
			//Throw 400 here too
		}
		
		return dao.getTweetsForPerson(personId);
	}
}
