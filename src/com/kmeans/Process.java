package com.kmeans;

import java.util.ArrayList;
import java.util.List;

import com.Model.User;

public class Process {
	public List<Node> Pretreatment(List<User> list1) {
		List<Node> list = new ArrayList<Node>(); 
		Node age_1 = new Node();
		age_1.setAge(15);
		Node age_2 = new Node();
		age_2.setAge(21);
		Node age_3 = new Node();
		age_3.setAge(27);
		Node age_4 = new Node();
		age_4.setAge(32);
		Node age_5 = new Node();
		age_5.setAge(37);
		Node age_6 = new Node();
		age_6.setAge(44);
		Node age_7 = new Node();
		age_7.setAge(55);
		for(User user : list1) {
			String str = user.getAge_range();
			String str1 = user.getCat_id();
			if(str.equals("1")) {
				if(str1.equals("1")) {
					age_1.setX(age_1.getX()+1);
				}else {
					age_1.setY(age_1.getY()+1);
				}
			}else if(str.equals("2")) {
				if(str1.equals("1")) {
					age_2.setX(age_2.getX()+1);
				}else {
					age_2.setY(age_2.getY()+1);
				}
			}else if(str.equals("3")) {
				if(str1.equals("1")) {
					age_3.setX(age_3.getX()+1);
				}else {
					age_3.setY(age_3.getY()+1);
				}
			}else if(str.equals("4")) {
				if(str1.equals("1")) {
					age_4.setX(age_4.getX()+1);
				}else {
					age_4.setY(age_4.getY()+1);
				}
			}else if(str.equals("5")) {
				if(str1.equals("1")) {
					age_5.setX(age_5.getX()+1);
				}else {
					age_5.setY(age_5.getY()+1);
				}
			}else if(str.equals("6")) {
				if(str1.equals("1")) {
					age_6.setX(age_6.getX()+1);
				}else {
					age_6.setY(age_6.getY()+1);
				}
			}else if(str.equals("7") || str.equals("8")){
				if(str1.equals("1")) {
					age_7.setX(age_7.getX()+1);
				}else {
					age_7.setY(age_7.getY()+1);
				}
			}
		}
		list.add(age_1);
		list.add(age_2);
		list.add(age_3);
		list.add(age_4);
		list.add(age_5);
		list.add(age_6);
		list.add(age_7);
		return list;
	}
}
