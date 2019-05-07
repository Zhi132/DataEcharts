package com.Model;

public class Point {		//点
	private double x;// 点x坐标
	private double y;// 点y坐标
	private int clusternum;// 点属于的聚类类别
	private String name;	//坐标点对应省份

	public Point(double x, double y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
	}

	public Point(double x, double y, int clusternum,String name) {
		this.x = x;
		this.y = y;
		this.clusternum = clusternum;
		this.name = name;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getClusternum() {
		return this.clusternum;
	}

	public void setClusternum(int clusternum) {
		this.clusternum = clusternum;
	}



	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + ", name=" + name + "]";
	}

	public String CenterID() {
		return "Point" + "(" + x + "," + y + ")";
	}
}
