package com.twixter.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.twixter.model.OperationResult;
import com.twixter.model.Person;
import com.twixter.model.Tweet;

@Repository
public class TwixterDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DriverManagerDataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public List<Tweet> getTweetsForPerson(int id) {
    	String sql = "SELECT * FROM tweet where person_id = :id";
    	Map<String, Object> namedParameters = new HashMap<String, Object>();
    	namedParameters.put("id", id);
    	List<Tweet> p = (List<Tweet>) jdbcTemplate.query(sql, namedParameters, new Tweet.TweetMapper());
    	return p;
    }
    
    public List<Tweet> getTweetsForFollowers(int id) {
    	String sql = "SELECT tweet.* from tweet INNER JOIN personfollower ON tweet.person_id = personfollower.following INNER JOIN person ON person.id = personfollower.follower where person.id = :id;";
    	Map<String, Object> namedParameters = new HashMap<String, Object>();
    	namedParameters.put("id", id);
    	List<Tweet> p = (List<Tweet>) jdbcTemplate.query(sql, namedParameters, new Tweet.TweetMapper());
    	return p;
    }
    
    public List<Person> getFollowers(int id) {
    	String sql = "SELECT person.* FROM personfollower INNER JOIN person ON person.id = personfollower.follower WHERE personfollower.following = :id;";
    	Map<String, Object> namedParameters = new HashMap<String, Object>();
    	namedParameters.put("id", id);
    	List<Person> p = (List<Person>) jdbcTemplate.query(sql, namedParameters, new Person.PersonMapper());
    	return p;
    }
    
    public List<Person> getFollowing(int id) {
    	String sql = "SELECT person.* FROM personfollower INNER JOIN person ON person.id = personfollower.following WHERE personfollower.follower = :id;";
    	Map<String, Object> namedParameters = new HashMap<String, Object>();
    	namedParameters.put("id", id);
    	List<Person> p = (List<Person>) jdbcTemplate.query(sql, namedParameters, new Person.PersonMapper());
    	return p;
    }
    
    public OperationResult addFollowing(int follower, int following) {
    	String sql = "INSERT INTO personfollower (follower, following) VALUES (:follower, :following);";
    	Map<String, Object> namedParameters = new HashMap<String, Object>();
    	namedParameters.put("follower", follower);
    	namedParameters.put("following", following);
    	
    	OperationResult op = new OperationResult();
    	op.setStatus("SUCCESS");

    	try {
    		jdbcTemplate.update(sql, namedParameters);
    	}
    	catch (DuplicateKeyException dke) {
    		op.setStatus("FAILED");
    		op.setMessage("Duplicate row");
    	}
    	
    	return op;
    }
    
    public void removeFollowing(int follower, int following) {
    	String sql = "DELETE FROM personfollower WHERE follower = :follower AND following = :following;";
    	Map<String, Object> namedParameters = new HashMap<String, Object>();
    	namedParameters.put("follower", follower);
    	namedParameters.put("following", following);
    	jdbcTemplate.update(sql, namedParameters);
    }
    
    public List<Person> getPersons() {
    	String sql = "SELECT * FROM person";
    	return (List<Person>) jdbcTemplate.query(sql, new Person.PersonMapper());
    }
}