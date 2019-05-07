package com.kmeans2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class kmeans2 {
	private  String fileName = "E:\\data\\julei2.txt"; // 文件地址
	private  int numTotal; // 聚类总数
	private  int K; // 聚类的簇数
	private  ArrayList<Point1> points = new ArrayList<Point1>();
	private  ArrayList<Cluster> clusters = new ArrayList<Cluster>();
	private  double lastE = Double.MAX_VALUE;
	private  double curE = 0.0;

	// 初始化信息
	public  void initialize() {
		try {
			numTotal = points.size();// 聚类点总数
			K = 2;// 聚类的簇数
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
			for (int j = 0; j < clusters.size(); j++) {
				if (distance > clusters.get(j).PointDistance(points.get(i))) {
					distance = clusters.get(j).PointDistance(points.get(i));
					clusternum = j;
				}
			}
			curE += distance;
			int oldcluster = points.get(i).getClusternum();
			points.get(i).setClusternum(clusternum);
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
	public  void printBelongs() {
		for (int i = 0; i < clusters.size(); i++) {
			System.out.println(clusters.get(i).toString());
			clusters.get(i).printElements();
			System.out.println();
		}
	}
	
	public   Map<String,Object> main(List<Point1> list) {
		points = (ArrayList<Point1>) list;
		Integer[] data1 = new Integer[8];
		String[] huafen = new String[8];
		Map<String, Object> map = new HashMap<>();
		for(int i = 0; i < points.size(); i++) {
			data1[i] = (int) (points.get(i).getX()+points.get(i).getY());
			huafen[i] = points.get(i).getName();
		}
		map.put("data1", data1);
		map.put("huafen",huafen);
		initialize();
		int i = 1;
		while (true) {
			calBelongs();
			AllCenter();
			if (curE == lastE) // 利用方差不变进行判断停止条件
				break;
			lastE = curE;
			System.out.println("第 " +i+"次迭代");
			i+=1;
			printBelongs();
		}
		System.out.println("轮廓系数");
		profileNum();
		return map;
	}
	
	//数据可视化需要
	public  List<Map<String,String>> main1(List<Point1> list1){
		points = (ArrayList<Point1>) list1;
		List<Map<String,String>> list2 = new ArrayList<Map<String,String>>();
		Map<String,String> map1 = new HashMap<String,String>();
		String data1;
		String huafen;
		for(int i = 0; i < points.size(); i++) {
			int num = (int) (points.get(i).getX()+points.get(i).getY());
			data1 = num+"";
			huafen = points.get(i).getName();
			map1 = new HashMap<String,String>();
			map1.put("value", data1);
			map1.put("name", huafen);
			list2.add(map1);
		}
		
		return list2;
	}
	
	
	//计算轮廓系数
	public  void profileNum() {
		List<Point1> points1 = new ArrayList<Point1>();
		List<Point1> points2 = new ArrayList<Point1>();
		Point1 p1;
		Point1 p2;
		double ans=0.0,ans1=0.0;
		for (int i = 0; i < clusters.size(); i++) {
			p1=null;
			p2=null;
			points1 = null;
			points1 = clusters.get(i).getItems();
			for (int j = 0; j < points1.size(); j++) {
				ans = 0.0;
				p1 = points1.get(j);
				for(int k = 0; k < points1.size(); k++) {
					if(j == k) continue;
					p2 = points1.get(k);
//					System.out.println(p1.toString());
//					System.out.println(p2.toString());
					ans += Math.sqrt(Math.pow(p1.getX()-p2.getX(),2)+Math.pow(p1.getY()-p2.getY(), 2));
//					System.out.println(ans+"$$$$$$$$$"+Math.pow(p1.getX()-p2.getX(),2)+Math.pow(p1.getY()-p2.getY(), 2));
				}
//				System.out.println(points1.size()+"  ### "+ans);
				if(points1.size()-1 == 0) {
					ans = 0.0;
				}
				else ans /= (points1.size()-1);
				points2=null;
				p2=null;
				ans1=0.0;
				int num = 0;
				for(int k = 0; k < clusters.size(); k++) {
					
					if(k == i) continue;
					points2 = clusters.get(k).getItems();
					num += points2.size();
					p2=null;
					for(int k1 = 0; k1 < points2.size(); k1++) {
						p2 = points2.get(k1);
						ans1 += Math.sqrt(Math.pow(p1.getX()-p2.getX(),2)+Math.pow(p1.getY()-p2.getY(), 2));
					}
				}
				ans1 /= num;
				System.out.println("第"+i+"个簇中的第"+j+"个值的轮廓系数为"+(ans1-ans)/(Math.max(ans1, ans)));
			}
		}
	}
	
}
