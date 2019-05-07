package com.Dao;

import java.sql.*;
import java.util.*;

import com.Conn.Conn;
import com.Model.Point;
import com.kmeans2.Point1;


public class CommDao {
	Conn conn=new Conn();
	Connection dbc=conn.getConn();
	PreparedStatement pst = null;
	ResultSet rst = null;
	
	//省份和购买力
	public List<Point> getProvince(){
		List<Point> list = new ArrayList<Point>();
		//Random r=new Random();
		String s = "select province,cat_id,count(cat_id) as ans_count from taobaodata group by province,cat_id order by province";
		try {
			pst = dbc.prepareStatement(s);
			rst = pst.executeQuery();
			int x = 0,y=0,t=1,i1=1;
			while(rst.next()) {
				Point point = null;
				if(i1 >= 30) i1=1;
				i1 *= 2;
				if(t%2==0) {
					y = Integer.parseInt(rst.getString("ans_count"))*i1;
					point = new Point(x,y,rst.getString("province"));
					list.add(point);
					//System.out.println(point.toString());
					t+=1;
				}
				else {
					x =Integer.parseInt(rst.getString("ans_count"))*i1;
					t+=1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	//省份和年龄段
	public List<Point> getProvince1(){
		List<Point> list = new ArrayList<Point>();
		
		String s = "select province,age_range,count(age_range) as ans_count from taobaodata group by province,age_range order by province";
		try {
			pst = dbc.prepareStatement(s);
			rst = pst.executeQuery();
			int x = 0,y=0,t=1;
			while(rst.next()) {
				Point point = null;
				//System.out.println(t%8+"##############");
				if(t%8==0 || t%8>=3&&t%8<=7) {
					t+=1;
					continue;
				}
				if(t%2==0) {
					y = Integer.parseInt(rst.getString("ans_count"));
					point = new Point(x,y,rst.getString("province"));
					list.add(point);
					//System.out.println(point.toString());
					t+=1;
				}
				else {
					x = Integer.parseInt(rst.getString("ans_count"));
					t+=1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Point point : list) {
			System.out.println(point.toString());
		}
		return list;
	}
	
	//年龄段和购买力
	public List<Point1> getProvince2(){
		List<Point1> list = new ArrayList<Point1>();
		
		String s = "select age_range,cat_id,count(cat_id) as ans_count from taobaodata group by cat_id,age_range order by age_range,ans_count";
		try {
			pst = dbc.prepareStatement(s);
			rst = pst.executeQuery();
			int x = 0,y=0,t=1;
			while(rst.next()) {
				Point1 point = null;
				//System.out.println(t%8+"##############");
//				if(t%8==0 || t%8>=3&&t%8<=7) {
//					t+=1;
//					continue;
//				}
				if(t%2==0) {
					y = Integer.parseInt(rst.getString("ans_count"));
					//System.out.println(rst.getString("age_range"));
					//System.out.println(x+"   @@  "+y);
					if("1".equals(rst.getString("age_range"))) point = new Point1(x,y,"0-18");
					else if("2".equals(rst.getString("age_range"))) point = new Point1(x,y,"18-24");
					else if("3".equals(rst.getString("age_range"))) point = new Point1(x,y,"25-29");
					else if("4".equals(rst.getString("age_range"))) point = new Point1(x,y,"30-34");
					else if("5".equals(rst.getString("age_range"))) point = new Point1(x,y,"35-39");
					else if("6".equals(rst.getString("age_range"))) point = new Point1(x,y,"40-49");
					else if("7".equals(rst.getString("age_range"))) point = new Point1(x,y,"40-49");
					else if("8".equals(rst.getString("age_range"))) point = new Point1(x,y,"50");
					list.add(point);
					//System.out.println(point.toString());
					t+=1;
				}
				else {
					x = Integer.parseInt(rst.getString("ans_count"));
					t+=1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for (Point1 point : list) {
//			System.out.println(point.toString());
//		}
		return list;
	}
}
