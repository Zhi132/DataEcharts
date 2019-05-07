package com.Model;

import java.util.ArrayList;

public class Cluster {		//簇
	private int id;	//	簇id
	private ArrayList<Point> items;		//簇中点集合
	private Point centroid;		//中心点

	public Cluster(int id, Point centroid) {		//构造方法
		this.id = id;
		this.centroid = centroid;
		this.items = new ArrayList<Point>();
	}

	public double PointDistance(Point apoint) { // 欧氏距离, Math.pow(X,Y)就是计算X的Y次方)
		double distance = Math.sqrt(Math.pow(centroid.getX() - apoint.getX(), 2)
						+ Math.pow(centroid.getY() - apoint.getY(), 2));
		return distance;
	}

	public void NewCenter() {// 根据簇的均值中心坐标，计算新的中心点坐标
		double sumx = 0.0;
		double sumy = 0.0;
		double itemnum = items.size();
		for (int i = 0; i < itemnum; i++) {
			sumx += items.get(i).getX();
			sumy += items.get(i).getY();
		}
		Point 	meansCenter = new Point(sumx / itemnum, sumy / itemnum, id,null);
		
		double minDistance = Double.MAX_VALUE;//记录该簇中各点到均值中心的最短距离
		double meanDistance;//同一个簇内点到均值中心的距离
		int centerPointID = 0;//记录哪一个点为中心点
		for (int i = 0; i < itemnum; i++) {
			meanDistance = Math.sqrt(Math.pow(meansCenter.getX() - items.get(i).getX(), 2)+ Math.pow(meansCenter.getY() - items.get(i).getY(), 2));	
			if (minDistance > meanDistance) {
				minDistance = meanDistance;
				centerPointID= i;
			}	
		}
		centroid = items.get(centerPointID);
	}

	public void addPoint(Point apoint) {		//给簇添加新点
		items.add(apoint);
	}

	public void removePoint(Point point) {		//删除旧点
		items.remove(point);
	}

	public String toString() {
		int ids = id+1;
		return "第" + ids + "个簇有" + items.size() + "个点,中心点为:" + centroid.CenterID();
		
	}

	public void printElements() {// 打印簇内的元素
		int xSum = 0;
		int ySum = 0;
		String names = "簇中的省份:";
		for (int i = 0; i < items.size(); i++) {
			names += items.get(i).getName();
			System.out.print("(" + items.get(i).getX() + ","
					+ items.get(i).getY() + ")");		
			xSum += items.get(i).getX();
			ySum += items.get(i).getY();
		}
		System.out.println("\n");
		System.out.println("x的总和"+xSum + ",y的总和"+ySum);
		System.out.println(names);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Point> getItems() {
		return items;
	}

	public void setItems(ArrayList<Point> items) {
		this.items = items;
	}

	public Point getCentroid() {
		return centroid;
	}

	public void setCentroid(Point centroid) {
		this.centroid = centroid;
	}
	
	
}
