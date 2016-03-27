package com.twixter.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.twixter.dao.TwixterDao;
import com.twixter.model.OperationResult;
import com.twixter.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/api-servlet.xml")
public class PersonTests {
	
	@Autowired
	TwixterDao dao;
	
	boolean containsPerson(List<Person> persons, int id) {
		Person tmp = new Person();
		tmp.setId(id);
		return persons.contains(tmp);
	}
	
	@Test
	public void getFollowersTest() {
		List<Person> p = dao.getFollowers(1);
		Assert.assertEquals("Should have 3 followers", p.size(), 3);
		Assert.assertTrue("3 should be following 1", containsPerson(p, 3));
		Assert.assertTrue("6 should be following 1", containsPerson(p, 6));
	}
	
	@Test
	public void getFollowingTest() {
		List<Person> p = dao.getFollowing(1);
		Assert.assertEquals("Should have 2 following", p.size(), 2);
		Assert.assertTrue("1 should be following 2", containsPerson(p, 2));
		Assert.assertTrue("1 should be following 3", containsPerson(p, 3));
	}
	
	@Test
	public void addFollowing() {
		dao.addFollowing(1, 6);
		List<Person> p = dao.getFollowing(1);
		Assert.assertEquals("Should have 3 following", p.size(), 3);
		Assert.assertTrue("1 should be following 6", containsPerson(p, 6));
		dao.removeFollowing(1, 6);
	}
	
	@Test
	public void addFollowingDuplicate() {
		dao.addFollowing(1, 6);
		OperationResult op = dao.addFollowing(1, 6);
		List<Person> p = dao.getFollowing(1);
		Assert.assertEquals("Should have 3 following", p.size(), 3);
		Assert.assertTrue("1 should be following 6", containsPerson(p, 6));
		Assert.assertEquals("2nd call should have failed", op.status, "FAILED");
		dao.removeFollowing(1, 6);
	}
	
	@Test
	public void removeFollowing() {
		dao.removeFollowing(3, 1);
		List<Person> p = dao.getFollowing(3);
		Assert.assertEquals("Should have 0 following", p.size(), 0);
		dao.addFollowing(3, 1);
	}
}
