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
import com.twixter.model.OperationResult;
import com.twixter.model.Person;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	TwixterDao dao;

	@RequestMapping("/{personId}/followers")
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
	
	@RequestMapping("/{personId}/following")
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
	
	@RequestMapping("/{personId}/following/add/{followingId}")
	public OperationResult addFollowing (@PathVariable("personId") String personIdParam, @PathVariable("followingId") String followingIdParam, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int personId = 0;
		int followingId = 0;
		if (personIdParam == null) {
			resp.setStatus(400);
			resp.getWriter().write("Bad request - Missing personId param");
			return null;
		}
		try {
			personId = Integer.parseInt(personIdParam);
			followingId = Integer.parseInt(followingIdParam);
		}
		catch (NumberFormatException nfe) {
			resp.setStatus(400);
			resp.getWriter().write("Bad request - Cannot parse personId or followingId param");
			return null;
		}
		
		return dao.addFollowing(personId, followingId);
	}
	
	@RequestMapping("/{personId}/following/remove/{followingId}")
	public OperationResult removeFollowing (@PathVariable("personId") String personIdParam, @PathVariable("followingId") String followingIdParam, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int personId = 0;
		int followingId = 0;
		if (personIdParam == null) {
			resp.setStatus(400);
			resp.getWriter().write("Bad request - Missing personId param");
			return null;
		}
		try {
			personId = Integer.parseInt(personIdParam);
			followingId = Integer.parseInt(followingIdParam);
		}
		catch (NumberFormatException nfe) {
			resp.setStatus(400);
			resp.getWriter().write("Bad request - Cannot parse personId or followingId param");
			return null;
		}
		
		dao.removeFollowing(personId, followingId);
		
		OperationResult op = new OperationResult();
		op.setStatus("SUCCESS");
		
		return op;
	}
	
	@RequestMapping("/all")
	public List<Person> getAll () {
		return dao.getPersons();
	}
}
