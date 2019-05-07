package com.kmeans2;

import java.util.ArrayList;

public class Cluster {
	private int id;
	private ArrayList<Point1> items;
	private Point1 centroid;

	public Cluster(int id, Point1 centroid) {
		this.id = id;
		this.centroid = centroid;
		this.items = new ArrayList<Point1>();
	}

	public double PointDistance(Point1 apoint) { // 欧氏距离, Math.pow(X,Y)就是计算X的Y次方)
		double distance = Math
				.sqrt(Math.pow(centroid.getX() - apoint.getX(), 2)
						+ Math.pow(centroid.getY() - apoint.getY(), 2));
		return distance;
	}

	public void NewCenter() {// 计算簇的中心点坐标
		double sumx = 0.0;
		double sumy = 0.0;
		double itemnum = items.size();
		for (int i = 0; i < itemnum; i++) {
			sumx += items.get(i).getX();
			sumy += items.get(i).getY();
		}
		centroid = new Point1(sumx / itemnum, sumy / itemnum, id);
	}

	public void addPoint(Point1 apoint) {
		items.add(apoint);
	}

	public void removePoint(Point1 point) {
		items.remove(point);
	}

	public String toString() {
		return "第 " + id + " 个簇有 " + items.size()
				+ " 个元素，中心点为" + centroid.CenterID();
	}

	public void printElements() {// 打印簇内的元素
		for (int i = 0; i < items.size(); i++) {
			System.out.print(" (" + items.get(i).getX() + ","
					+ items.get(i).getY() + ","+items.get(i).getName()+") ");
		}
		System.out.println();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Point1> getItems() {
		return items;
	}

	public void setItems(ArrayList<Point1> items) {
		this.items = items;
	}

	public Point1 getCentroid() {
		return centroid;
	}

	public void setCentroid(Point1 centroid) {
		this.centroid = centroid;
	}
	
	
}
