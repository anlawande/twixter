package com.twixter.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twixter.dao.TwixterDao;
import com.twixter.model.Person;

@RestController
@RequestMapping("/person/{personId}")
public class PersonController {
	
	@Autowired
	TwixterDao dao;

	@RequestMapping("/followers")
	public List<Person> getFollowers (@PathVariable("personId") String personIdParam, HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
		
		return dao.getFollowers(personId);
	}
	
	@RequestMapping("/following")
	public List<Person> getFollowing (@PathVariable("personId") String personIdParam, HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
		
		return dao.getFollowing(personId);
	}
}
