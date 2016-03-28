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

/**
 * Api controller for person based calls
 * @author Aniket
 *
 */
@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	TwixterDao dao;

	/**
	 * Get followers for personId
	 * 
	 * @param personIdParam
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
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
	
	/**
	 * Get persons personId is following
	 * 
	 * @param personIdParam
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
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
	
	/**
	 * Add followingId to personId's following list
	 * 
	 * @param personIdParam
	 * @param followingIdParam
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
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
	
	/**
	 * Remove followingId from personId's following list
	 * 
	 * @param personIdParam
	 * @param followingIdParam
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
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
	
	/**
	 * Convenience method for demo page
	 * 
	 * @return
	 */
	@RequestMapping("/all")
	public List<Person> getAll () {
		return dao.getPersons();
	}
}
