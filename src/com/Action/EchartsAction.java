package com.Action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.Dao.CommDao;
import com.Model.Point;
import com.kmeans2.Point1;
import com.kmeans2.kmeans2;
import com.kmedoids.KMedoids;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class EchartsAction extends ActionSupport {
	KMedoids k = new KMedoids();
	public String Picture() throws IOException {
		CommDao dao = new CommDao();
		List<Point> list= dao.getProvince();
		KMedoids k = new KMedoids();
		Map<String,Object> map = new HashMap<String,Object>();
		List<String[]> list2 = new ArrayList<String[]>();
		map.clear();
		list2.clear();
		map = k.main(list);
		JSONObject jsonObject = JSONObject.fromObject(map);
		//System.out.println(jsonObject.toString());
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		//System.out.println(jsonObject.toString());
		ServletActionContext.getResponse().getWriter().println(jsonObject.toString());
		CreateFileUtil.createJsonFile(jsonObject.toString(), "D:/upload", "zuizong");
		return null;
	}
	
	public String Picture1() throws IOException {
		CommDao dao = new CommDao();
		List<Point> list= dao.getProvince();
		KMedoids k = new KMedoids();
		Map<String,Object> map = new HashMap<String,Object>();
		List<String[]> list2 = new ArrayList<String[]>();
		map.clear();
		list2.clear();
		map = k.main(list);
		List<Map<String,String>> list1 = k.NumberJson();
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray jsonArray = JSONArray.fromObject(list1,jsonConfig);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		System.out.println(jsonArray.toString());
		return null;
	}
	
	public String Picture2() throws IOException {
		CommDao dao = new CommDao();
		kmeans2 kmewans2=new kmeans2(); 
		List<Point1> list1= dao.getProvince2();
		Map<String,Object> map = kmewans2.main(list1);
		JSONObject jsonObject = JSONObject.fromObject(map);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonObject.toString());
		System.out.println(jsonObject.toString());
		return null;
	}
	
	public String Picture3() throws IOException {
		CommDao dao = new CommDao();
		kmeans2 kmewans2=new kmeans2(); 
		List<Point1> list1= dao.getProvince2();
		List<Map<String,String>> list = kmewans2.main1(list1);
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		System.out.println(jsonArray.toString());
		return null;
	}
	
	
	//省份和购买力
	public String dataDao() {
		CommDao dao = new CommDao();
		List<Point> list= dao.getProvince();
		KMedoids k = new KMedoids();
		Map<String,Object> map = new HashMap<String,Object>();
		List<String[]> list2 = new ArrayList<String[]>();
		map.clear();
		list2.clear();
		map = k.main(list);
		return SUCCESS;
	}
	//省份和年龄段
	public String dataDao1() {
		CommDao dao = new CommDao();
		List<Point> list1= dao.getProvince1();
		return SUCCESS;
	}
	
	//年龄段和购买力
	public String dataDao2() {
		CommDao dao = new CommDao();
		kmeans2 kmewans2=new kmeans2(); 
		List<Point1> list1= dao.getProvince2();
		kmewans2.main(list1);
		return SUCCESS;
	}
}
