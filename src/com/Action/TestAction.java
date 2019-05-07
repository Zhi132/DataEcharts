package com.Action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import com.opensymphony.xwork2.ActionSupport;

import com.Model.Customer;
import com.Model.biaoge;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
* @author ZhaoSiZhi
* @version 创建时间：2019年4月3日 下午9:57:52
* 类说明
*/
public class TestAction extends ActionSupport {

	public String save() throws IOException {
		System.out.println("SSSSSSSS");
		List<Customer> list = new ArrayList<>();
		Customer customer = new Customer();
		customer.setCust_id((long) 1);
		customer.setCust_name("力气沉111");
		list.add(customer);
		Customer customer1 = new Customer();
		customer1.setCust_id((long) 2);
		customer1.setCust_name("李瑞强111");
		list.add(customer1);
		Customer customer2 = new Customer();
		customer2.setCust_id((long) 3);
		customer2.setCust_name("王五111");
		list.add(customer2);
		for (Customer customer3 : list) {
			System.out.println(customer3.toString());
		}
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		System.out.println(jsonArray.toString());
		CreateFileUtil.createJsonFile(jsonArray.toString(), "D:/upload", "NewTest");
		return null;
	}
	
	@Test
	public String find() throws IOException {
		System.out.println("####没进来");
		Integer[] salesVolume = {15000,100,20,56,35,80};
		double[] bussinessVolume = {10*10,100*8.5,20*9.5,56*9,35*9.5,80*9};
		String[] months = {"杨鸿宇","哈哈哈","3","4","5","6"};
		Map<String, Object> map = new HashMap<>();
		map.put("salesVolume", salesVolume);
		map.put("bussinessVolume",bussinessVolume);
		map.put("months", months);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println(jsonObject.toString());
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		System.out.println(jsonObject.toString());
		ServletActionContext.getResponse().getWriter().println(jsonObject.toString());
		System.out.println("为什么不能到界面");
		CreateFileUtil.createJsonFile(jsonObject.toString(), "D:/upload", "NewTest22");
		return null;
	}
	
	@Test
	public String lianyi() throws IOException {
		//System.out.println("####没进来");
		
		String s[]= {"[1,2]","[2,3]"};
		String s1[] = new String[10];
//		System.out.println(s);
//		for(int i = 1; i <= 10; i++) {
//			if(i!=10) s += "["+i+","+(i*10)+"],";
//			else s += "["+i+","+(i*10)+"]]";
//		}
//		System.out.println(s);
		for(int i = 0; i < 10; i++) {
			s1[i] = "["+i+","+i+"]";
		}
		Map<String, Object> map = new HashMap<>();
		map.put("data1", s1);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		//System.out.println(jsonObject.toString());
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		//System.out.println(jsonObject.toString());
		ServletActionContext.getResponse().getWriter().println(jsonObject.toString());
		CreateFileUtil.createJsonFile(jsonObject.toString(), "D:/upload", "NewTest");
		//System.out.println("为什么不能到界面");
		return null;
	}
	
	
	@Test
	public String shuyu() throws IOException {
		//System.out.println("####没进来");
//		Integer[] salesVolume = {10,100,20,56,35,80};
//		double[] bussinessVolume = {10*10,100*8.5,20*9.5,56*9,35*9.5,80*9};
//		String[] months = {"李书宇","哈哈哈11111","3","4","5","6"};
		Map<String, Object> map = new HashMap<>();
		map.put("snum", 5);
		map.put("nsum",7);
		//map.put("months", months);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println(jsonObject.toString());
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		System.out.println(jsonObject.toString());
		ServletActionContext.getResponse().getWriter().println(jsonObject.toString());
		System.out.println("为什么不能到界面");
		CreateFileUtil.createJsonFile(jsonObject.toString(), "D:/upload", "shuyu");
		return null;
	}
	
	@Test
	public String sandiantu() throws IOException {
		//System.out.println("####没进来");

//		String s[]= {"[1,2]","[2,3]"};
//		String s1[] = new String[10];
//		s1[0] = "[1,3]";
		List<biaoge> list1 = new ArrayList<biaoge>();
		List<biaoge> list2 = new ArrayList<biaoge>();
		List<biaoge> list3 = new ArrayList<biaoge>();
		
		biaoge biaoge1 = new biaoge();
		String s[] = new String[1];
		String s1[] = new String[10];
		String s2[] = new String[10];
		s[0] = "[1,3]";
		for(int i = 1; i < 10; i++) {
			s1[i] = "["+(i+1)+","+(i+1)+",1]";
		}
		for(int i = 1; i < 10; i++) {
			s2[i] = "["+i+","+i+"1]";
		}
		biaoge1.setS_1(s);
		biaoge1.setS_2(s1);
		biaoge1.setS_3(s2);
		list1.add(biaoge1);
		Map<String, Object> map = new HashMap<>();
		map.put("1",list1);
		
		
		biaoge biaoge2 = new biaoge();
		s[0] = "[1,3]";
		for(int i = 1; i < 10; i++) {
			s1[i] = "["+(i*10)+","+(i*10)+",1]";
		}
		for(int i = 1; i < 10; i++) {
			s2[i] = "["+i+","+i+"1]";
		}
		biaoge2.setS_1(s);
		biaoge2.setS_2(s1);
		biaoge2.setS_3(s2);
		list2.add(biaoge2);
		map.put("2",list2);
		
		
		biaoge biaoge3 = new biaoge();
		s[0] = "[1,3]";
		for(int i = 1; i < 10; i++) {
			s1[i] = "["+(i*100)+","+(i*50)+",1]";
		}
		for(int i = 1; i < 10; i++) {
			s2[i] = "["+i+","+(i+1)+"1]";
		}
		biaoge3.setS_1(s);
		biaoge3.setS_2(s1);
		biaoge3.setS_3(s2);
		list3.add(biaoge3);
		map.put("3",list3);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		//System.out.println(jsonObject.toString());
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		//System.out.println(jsonObject.toString());
		ServletActionContext.getResponse().getWriter().println(jsonObject.toString());
		CreateFileUtil.createJsonFile(jsonObject.toString(), "D:/upload", "sandiantu");
		//System.out.println("为什么不能到界面");
		return null;
	}
	
	public String sandiantu2() throws IOException {
		System.out.println("####没进来");

//		String s[]= {"[1,2]","[2,3]"};
//		String s1[] = new String[10];
//		s1[0] = "[1,3]";
		List<biaoge> list = new ArrayList<biaoge>();
		
		biaoge biaoge1 = new biaoge();
		String s[] = new String[1];
		String s1[] = new String[10];
		String s2[] = new String[10];
		s[0] = "1,3";
		for(int i = 1; i < 10; i++) {
			s1[i] = "["+(i+1)+","+(i+1)+",1]";
		}
		for(int i = 1; i < 10; i++) {
			s2[i] = "["+i+","+i+"1]";
		}
		biaoge1.setS_1(s);
		biaoge1.setS_2(s1);
		biaoge1.setS_3(s2);
		list.add(biaoge1);
		
		System.out.println(biaoge1.toString());
		
		biaoge biaoge2 = new biaoge();
		s=null;
		s1=null;
		s2=null;
		s = new String[1];
		s1 = new String[10];
		s2 = new String[10];
		s[0] = "[1.5,1]";
		for(int i = 1; i < 10; i++) {
			s1[i] = "["+(i*10)+","+(i*10)+",1]";
		}
		for(int i = 1; i < 10; i++) {
			s2[i] = "["+i+","+i+"1]";
		}

		biaoge2.setS_1(s);
		biaoge2.setS_2(s1);
		biaoge2.setS_3(s2);
		//System.out.println(biaoge2.toString());
		list.add(biaoge2);
		
		
		s=null;
		s1=null;
		s2=null;
		s = new String[1];
		s1 = new String[10];
		s2 = new String[10];
		biaoge biaoge3 = new biaoge();
		s[0] = "[1.5,1]";
		for(int i = 1; i < 10; i++) {
			s1[i] = "["+(i*100)+","+(i*50)+",1]";
		}
		for(int i = 1; i < 10; i++) {
			s2[i] = "["+i+","+(i+1)+"1]";
		}
		biaoge3.setS_1(s);
		biaoge3.setS_2(s1);
		biaoge3.setS_3(s2);
		//System.out.println(biaoge3.toString());
		list.add(biaoge3);
//		
//		System.out.println(list.size());
		
		for (biaoge string : list) {
			System.out.println(string.toString());
		}
		
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		System.out.println(jsonArray.toString());
		CreateFileUtil.createJsonFile(jsonArray.toString(), "D:/upload", "sandiantu2");
		return null;
	}
	
	public String tiaozhuan() {
		return "tiaozhuan";
	}
}
