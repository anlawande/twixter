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
import com.twixter.model.Tweet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/api-servlet.xml")
public class TweetsTest {
	
	@Autowired
	TwixterDao dao;
	
	boolean containsTweet(List<Tweet> tweets, int id) {
		Tweet tmp = new Tweet();
		tmp.setId(id);
		return tweets.contains(tmp);
	}

	@Test
	public void TweetsForUser() {
		List<Tweet> tweets = dao.getTweetsForPerson(1);
		Assert.assertEquals("Should have 2 tweets", tweets.size() , 2);
		Assert.assertTrue("Should be the first tweet", containsTweet(tweets, 1));
		Assert.assertEquals("Text should be same", tweets.get(0).text , "This is some sample text");
	}
	
	@Test
	public void TweetsForUserSearch() {
		List<Tweet> tweets = dao.getTweetsForPerson(1, "sample");
		Assert.assertEquals("Should have 1 tweet", tweets.size() , 1);
		Assert.assertTrue("Should be the first tweet", containsTweet(tweets, 1));
		Assert.assertEquals("Text should be same", tweets.get(0).text , "This is some sample text");
	}
	
	@Test
	public void TweetsForUserFollowers() {
		List<Tweet> tweets = dao.getTweetsForFollowers(1);
		Assert.assertEquals("Should have 3 tweets", tweets.size() , 3);
		Assert.assertEquals("Tweet should be from follower", tweets.get(0).person_id , 2);
	}
	
	@Test
	public void TweetsForUserFollowersSearch() {
		List<Tweet> tweets = dao.getTweetsForFollowers(1, "beautiful");
		Assert.assertEquals("Should have 1 tweets", tweets.size() , 1);
		Assert.assertEquals("Tweet should be from follower", tweets.get(0).person_id , 2);
	}
	
	@Test
	public void TweetsForUserFollowersSearchNotFound() {
		List<Tweet> tweets = dao.getTweetsForFollowers(1, "bazinga!");
		Assert.assertEquals("Should have 0 tweets", tweets.size() , 0);
	}
}
