package com.kmeans2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class kmeans2 {
	private static String fileName = "E:\\data\\julei2.txt"; // 文件地址
	private static int numTotal; // 聚类总数
	private static int K; // 聚类的簇数
	private static ArrayList<Point1> points = new ArrayList<Point1>();
	private static ArrayList<Cluster> clusters = new ArrayList<Cluster>();
	private static double lastE = Double.MAX_VALUE;
	private static double curE = 0.0;

	// 初始化信息
	public static void initialize() {
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
	public static void calBelongs() {
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

	public static void AllCenter() {// 重新计算簇的中心坐标
		for (int i = 0; i < clusters.size(); i++) {
			clusters.get(i).NewCenter();
		}
	}

	// 打印每个点属于的聚类类别
	public static void printPoints() {
		for (int i = 0; i < points.size(); i++) {
			System.out.println(points.get(i).toString());
		}
	}

	// 打印每个簇的信息（包含元素个数、中心点坐标以及元素坐标）
	public static void printBelongs() {
		for (int i = 0; i < clusters.size(); i++) {
			System.out.println(clusters.get(i).toString());
			clusters.get(i).printElements();
			System.out.println();
		}
	}
	
	public static  Map<String,Object> main(List<Point1> list) {
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
		return map;
	}
	
	//数据可视化需要
	public static List<Map<String,String>> main1(List<Point1> list1){
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
	
}
