package com.dataBase.Connection;

/**
 * 
 * @author Henry Granados
 * This program shows a very good understanding 
 * of what Exceptions, Objects, Clases, Contructors are, as well as how to connect 
 * to Database working with JDBC.  
 */

public class MyDBClass {

	public static void main(String[] args) {
		DataBaseConnect connect = new DataBaseConnect();
		connect.getDataFromDB();

	}
}
