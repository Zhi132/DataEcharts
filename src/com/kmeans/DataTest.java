package com.kmeans;

import java.util.ArrayList;
import java.util.List;

import com.Model.User;

public class DataTest {
	
	public List<User> Data(){
		List<User> list = new ArrayList<User>();
		for(int i = 0; i < 10000; i++) {
			User user = new User();
			user.setAge_range(Integer.toString((int)(Math.random()*100)%8));
			user.setCat_id(Integer.toString((int)(Math.random()*100)%3));
			list.add(user);
		}
		return list;
	}
}
