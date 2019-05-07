package com.kmeans2;


public class Point1 
{
	private double x;//点x坐标
	private double y;//点y坐标
	private int clusternum;//点属于的聚类类别
	private String name;
	
	public Point1(double x, double y,String name)
	{
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	public Point1(double x, double y,int clusternum)
	{
		this.x = x;
		this.y = y;
		this.clusternum = clusternum;
	}
	
	public double getX()
	{
		return this.x;
	}
	
	public double getY()
	{
		return this.y;
	}
	
	public int getClusternum()
	{
		return this.clusternum;
	}
	
	public void setClusternum(int clusternum)
	{
		this.clusternum = clusternum;
	}
	
	public String toString()
	{
		return "Point" + "(" + x + "," + y + ") belongs to cluster " + clusternum;
	}
	public String CenterID()
	{
		return "Point" + "(" + x + "," + y + ")";
	}
//	public static void main(String[] args) {  
//        System.out.println(new Point(3,4).toString());  
//    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
}
