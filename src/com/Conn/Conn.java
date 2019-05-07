package com.Conn;

import java.sql.*;

public class Conn {
    public static final String DBDRIVER="org.gjt.mm.mysql.Driver";
    public static final String DBURL="jdbc:mysql://localhost:3306/data";
    public static final String DBUSER="root";
    public static final String DBPASS="123456";
    public Connection getConn() {
    	 Connection coon=null;
   	 try {
   		
   		 Class.forName(DBDRIVER);
   		 coon = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
   	 }
   	 catch(Exception e) {
   		 e.printStackTrace();
   	 }
   	 return coon;
    }
}
