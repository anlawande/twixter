package com.twixter.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.twixter.dao.TwixterDao;
import com.twixter.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/api-servlet.xml")
public class PersonTests {
	
	@Autowired
	TwixterDao dao;
	
	@Test
	public void getFollowersTest() {
		List<Person> p = dao.getFollowers(1);
		Assert.assertEquals("Should have 3 followers", p.size(), 3);
		Assert.assertEquals("3 should be following 1", p.get(0).id, 3);
		Assert.assertEquals("6 should be following 1", p.get(2).id, 6);
	}
	
	@Test
	public void getFollowingTest() {
		List<Person> p = dao.getFollowing(1);
		Assert.assertEquals("Should have 2 following", p.size(), 2);
		Assert.assertEquals("1 should be following 2", p.get(0).id, 2);
		Assert.assertEquals("1 should be following 3", p.get(1).id, 3);
	}
}
