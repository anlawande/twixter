package com.twixter.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.twixter.dao.Tweet;
import com.twixter.dao.TwixterDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/api-servlet.xml")
public class TweetsTest {
	
	@Autowired
	TwixterDao dao;

	@Test
	public void TweetsForUser() {
		List<Tweet> tweets = dao.getTweetsForPerson(1);
		Assert.assertEquals("Should have 2 tweets", tweets.size() , 2);
		Assert.assertEquals("Should be the first tweet", tweets.get(0).id , 1);
		Assert.assertEquals("Text should be same", tweets.get(0).text , "This is some sample text");
	}
}
