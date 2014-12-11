/**
 * 
 * @author Henry F. Granados
 * This program retrieves data from dataBase.
 * 
 */
package com.dataBase.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class DataBaseConnect {
	private Connection con;
	private Statement st;
	private ResultSet rs;

	/**
	 * This constructor enables connection to MySql Database
	 */
	public DataBaseConnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/javatest", "root", "compu123");
			st = con.createStatement();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * This method ensures to retrieve data from DataBase
	 */
	
	public void getDataFromDB() {

		Scanner input = new Scanner(System.in);
		LinkedList<customers> list = new LinkedList<customers>();
		try {
			
			String query = "SELECT * FROM people";
			rs = st.executeQuery(query);
			System.out.println("Customers From DataBase");

			/**
			 * This while loop will basically loop through each of the
			 * customer's informacion in the DataBase, and save them into string
			 * variables so they can be display on the screen.
			 */
			while (rs.next()) {

				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				String lastName = rs.getString("LastName");
				String age = rs.getString("AGE");
				list.add(new customers(id, name, lastName, age));

			}
			System.out.println("-------------------");
		
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		/**
		 * This code closes the DataBase
		 */
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/**
		 * Sorting customers by their last name
		 */
		Collections.sort(list,new Mycustomers());
        System.out.println("This is the list of customers sorted by their last names: ");
        System.out.println("--------------------------------------------------------------");
        System.out.println();
        for(customers cu:list){
            System.out.println(cu);
        }
        
        /**
         * This code will compare the data that is in the DataBase and the data entered 
         * by the user and make sure a customer exists in the DataBase.
         */
        System.out.println("***************************************************");
        System.out.println("***************************************************");
        System.out.print("\nPLEASE  ENTER A FIRST NAME TO SEARCH: ");
        String fname = input.nextLine();
        System.out.print("PLEASE ENTER A LASTNAME TO SEARCH: ");
        String lname = input.nextLine();
        
        boolean found = false;
        for(customers cu:list){
        	if(cu.getlastName().equalsIgnoreCase(lname) && cu.getName().equalsIgnoreCase(fname))
        	{
        		System.out.println("\nThe customer you are looking for was found!\n\n"+cu);
        		found = true;
        	}
        }
        if(!found)
        	
        System.out.println(fname+" "+lname+" was not found");
        
        }

	}
	/**
	 * This code implements the comparator interface to ensure comparison among 
	 * comtumer's last names ans sort them.
	 */
	class Mycustomers implements Comparator<customers>{
		 
	    public int compare(customers e1, customers e2) {
	    	return e1.getlastName().compareTo(e2.getlastName()); 
	    }
	}
	
	/**
	 * 
	 * This is the customer's class
	 *
	 */
	class customers {

		private String id;
		private String name;
		private String lastName;
		private String age;
		
		/**
		 * Contructs the customer's information
		 * @param id of customer
		 * @param name of customer
		 * @param lastName of customer
		 * @param age of customer
		 */
		public customers(String id, String name, String lastName, String age) {
			this.id = id;
			this.name = name;
			this.lastName = lastName;
			this.age = age;
		}
		/*
		 * gets Id
		 */
		public String getId() {
			return id;
		}
		/*
		 * sets Id
		 */
		public void setId(String id) {
			this.id = id;
		}
		/*
		 * gets Name
		 */
		public String getName() {
			return name;
		}
		/*
		 * sets Name
		 */
		public void setName(String name){
			this.name = name;
		}
		/*
		 * gets lastName
		 */
		public String getlastName(){
			return lastName;
		}
		/*
		 * sets lastName
		 */
		public void setLastName(String lastName) {
			this.name = lastName;
		}
		/*
		 *gets age
		 */
		public String getAge() {
			return age;
		}
		/*
		 * sets age
		 */
		public void setAge(String age) {
			this.age = age;
		}
		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return "---------------\nID: " + this.id + "\n"+"Name: "+ this.name + "\nLastName: " + this.lastName + "\nAGE: "
					+ this.age + "\n";
		}
	}


