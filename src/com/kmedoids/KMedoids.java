package com.kmedoids;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Model.*;
import com.opensymphony.xwork2.ActionSupport;


public class KMedoids extends ActionSupport{
	private  int numTotal; // 聚类总数
	private  int K; // 聚类的簇数
	private  ArrayList<Point> points = new ArrayList<Point>();
	private   ArrayList<Cluster> clusters = new ArrayList<Cluster>();
	private  double lastE = Double.MAX_VALUE;
	private  double curE = 0.0;
	private  List<String[]> list = new ArrayList<String[]>();
	private  List<String[]> list2 = new ArrayList<String[]>();
	
	public  ArrayList<Cluster> getClusters() {
		return clusters;
	}

	public  void setClusters(ArrayList<Cluster> clusters) {
		clusters = clusters;
	}

	// 初始化信息
	public  void initialize() {
		try {
			numTotal = points.size();// 聚类点总数
			K = 3;// 聚类的簇数
			System.out.println("聚类点总数: " + numTotal + ", 聚类簇数: " + K);
			for (int i = 0; i < K; i++) {// 初始化聚类中心
				Cluster acluster = new Cluster(i, points.get(i));
				clusters.add(acluster);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	// 判断每个点属于哪个簇
	public  void calBelongs() {
		curE = 0;
		for (int i = 0; i < points.size(); i++) {
			double distance = Double.MAX_VALUE;
			int clusternum = 0;
			for (int j = 0; j < clusters.size(); j++) {		//寻找某个点距离哪个簇的中心点最近
				if (distance > clusters.get(j).PointDistance(points.get(i))) {
					distance = clusters.get(j).PointDistance(points.get(i));
					clusternum = j;		//最近的簇编号
				}
			}
			curE += distance;		//总距离
			int oldcluster = points.get(i).getClusternum();		//获取点原来属于的簇
			points.get(i).setClusternum(clusternum);		//更新点的熟悉（簇编号）
			clusters.get(oldcluster).removePoint(points.get(i));// 原来的簇移除不满足的点
			clusters.get(clusternum).addPoint(points.get(i));// 新簇添加新点
		}
	}

	public  void AllCenter() {// 重新计算簇的中心坐标
		for (int i = 0; i < clusters.size(); i++) {
			clusters.get(i).NewCenter();
		}
	}

	// 打印每个点属于的聚类类别
	public  void printPoints() {
		for (int i = 0; i < points.size(); i++) {
			System.out.println(points.get(i).toString());
		}
	}

	// 打印每个簇的信息（包含元素个数、中心点坐标以及元素坐标）
	public  List<Cluster> printBelongs() {
		for (int i = 0; i < clusters.size(); i++) {
			System.out.println(clusters.get(i).toString());
			clusters.get(i).printElements();
			System.out.println();
		}
		
		return clusters;
	}
	
	//转换成json格式保存
	public  List<String[]> jsonjava(){
		list = new ArrayList<String[]>();
		biaoge biaoge1 = new biaoge();
		String s[] = new String[1];
		String s1[] = new String[50];
		String s2[] = new String[50];
		for(int i = 0; i < clusters.size(); i++) {
			s[0] = "["+clusters.get(i).getCentroid().getX()+","+clusters.get(i).getCentroid().getY()+"]";
			list.add(s);
			for(int j = 0; j < clusters.get(i).getItems().size(); j++) {
				int x = (int) clusters.get(i).getItems().get(j).getX();
				int y = (int) clusters.get(i).getItems().get(j).getY();
				String s3 = clusters.get(i).getItems().get(j).getName();
				s1[j] ="["+x+","+y+",\'"+s3+"\']"; 
			}
			list.add(s1);
			s=null;
			s = new String[1];
			s1=null;
			s1 = new String[50];
		}
		return list;
	}

	//获得最后形成的簇的个数，并将其保存起来
	public List<Map<String,String>> NumberJson() {
		List<Map<String,String>> list1 = new ArrayList<Map<String,String>>();
		Map<String,String> map1 = new HashMap<String,String>();
		for(int i = 0; i < clusters.size(); i++) {
			String s3 = clusters.get(i).getItems().size()+"";
			map1 = new HashMap<String,String>();
			map1.put("value", s3);
			map1.put("name", "第"+(i+1)+"个簇");
			list1.add(map1);
		}
		return list1;
	}
	
	public Map<String,Object> main(List<Point> list3) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.clear();
		list2.clear();
		list.clear();
		points = (ArrayList<Point>) list3;
		initialize();
		int sum= 1;
		while (true) {
			calBelongs();
			if (curE == lastE) { // 利用方差不变进行判断停止条件
				System.out.println("所有点的总距离未改变，聚类结束");
				break;
			}
			lastE = curE;
			System.out.println("********第" + sum + "次聚类********" );
			System.out.println("所有点的总距离" + curE);
			printBelongs();
			list2 = new ArrayList<String[]>();
			list2 = jsonjava();
			map.put("key"+sum+"", list2); 
			sum++;
			AllCenter();
		}
		return map;
	}
	
	public String getJSON(){
		String json = null;
		
		return json;
	}
}
