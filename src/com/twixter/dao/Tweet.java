package com.twixter.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Tweet {
	public int id;
	public int person_id;
	public String text;
	
	public static class TweetMapper implements RowMapper<Tweet> {

		@Override
		public Tweet mapRow(ResultSet rs, int rowNum) throws SQLException {
			Tweet t = new Tweet();
			t.setId(rs.getInt(1));
			t.setPerson_id(rs.getInt(2));
			t.setText(rs.getString(3));
			return t;
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPerson_id() {
		return person_id;
	}
	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
