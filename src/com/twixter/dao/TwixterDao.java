package com.twixter.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

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
}