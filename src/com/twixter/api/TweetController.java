package com.twixter.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twixter.dao.TwixterDao;
import com.twixter.model.Tweet;

@RestController
@RequestMapping("/person/{personId}/tweets")
public class TweetController {
	
	@Autowired
	private TwixterDao dao;

	@RequestMapping(value="/self")
	public @ResponseBody List<Tweet> getTweetsForPerson(@PathVariable("personId") String personIdParam, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String searchText = req.getParameter("search");
		
		int personId = 0;
		if (personIdParam == null) {
			resp.setStatus(400);
			resp.getWriter().write("Bad request - Missing personId param");
			return null;
		}
		try {
			personId = Integer.parseInt(personIdParam);
		}
		catch (NumberFormatException nfe) {
			resp.setStatus(400);
			resp.getWriter().write("Bad request - Cannot parse personId param");
			return null;
		}
		
		if (searchText != null)
			return dao.getTweetsForPerson(personId, searchText);
		else
			return dao.getTweetsForPerson(personId);
	}
	
	@RequestMapping(value="/all")
	public @ResponseBody List<Tweet> getTweetsForPersonAndFollowers(@PathVariable("personId") String personIdParam, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String searchText = req.getParameter("search");
		
		int personId = 0;
		if (personIdParam == null) {
			resp.setStatus(400);
			resp.getWriter().write("Bad request - Missing personId param");
			return null;
		}
		try {
			personId = Integer.parseInt(personIdParam);
		}
		catch (NumberFormatException nfe) {
			resp.setStatus(400);
			resp.getWriter().write("Bad request - Cannot parse personId param");
			return null;
		}
		
		List<Tweet> tweets = null;
		
		if (searchText != null) {
			//Self tweets
			tweets = dao.getTweetsForPerson(personId, searchText);
			//Followers tweets
			tweets.addAll(dao.getTweetsForFollowers(personId, searchText));
		}
		else {
			tweets = dao.getTweetsForPerson(personId);
			tweets.addAll(dao.getTweetsForFollowers(personId));
		}
		
		return tweets;
	}
}
