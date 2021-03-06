package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Review {
	
	
	//A common method to connect to the DB
	
		private Connection connect()
		{
			Connection con = null;
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gbweb", "root", ""); 
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			return con;

		}
		
		
	public String insertreview (String projectCode, String review, String decision)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}
			
			// create a prepared statement
			
			String query = " insert into review (`reviewID`,`projectCode`,`review`,`decision`)"+ " values (?, ?, ?, ?)"; 
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, projectCode); 
			 preparedStmt.setString(3, review); 
			 preparedStmt.setString(4, decision); 
			// execute the statement

			preparedStmt.execute();
			
			con.close();
			
			String newreview = readreview();
			output = "{\"status\":\"success\", \"data\": \"" +
					newreview  + "\"}";
			
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
				System.err.println(e.getMessage());
		}
			
		return output;
	}


	public String readreview()
	{
			String output = "";
			try
			{
					Connection con = connect();
					
				if (con == null)
				{
					return "Error while connecting to the database for reading.";
				}
				
				// Prepare the html table to be displayed
		        
	            output = "<table border='1'><tr><th>project Code</th>"
	             +"<th>review</th><th>decision</th><th>Update</th><th>Remove</th></tr>";
		
				String query = "select * from review";
				
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through  rows in the result set
			
			while (rs.next())
			{
				String reviewID = Integer.toString(rs.getInt("reviewID"));
				String projectCode = rs.getString("projectCode");
				String review = rs.getString("review");
				String decision = rs.getString("decision");
				
				// Add into the html table
				output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + reviewID
						+ "'>" + projectCode + "</td>";
				output += "<td>" + review + "</td>";
				output += "<td>" + decision + "</td>";
	           
	              
	      //buttons
	            
	            output += "<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary' data-itemid='" + reviewID + "'></td>"
	            		+ "<td><input name = 'btnRemove' type='button' value = 'Remove' "
	            		+ "class = 'btnRemove btn btn-danger' data-itemid='" + reviewID + "'>"
	            		+"</td></tr>";
	            		
			}
			
			con.close();
			
			// Complete the html table
			
			output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}


		public String updatereview(String reviewID, String projectCode, String review, String decision)
		{
				String output = "";
				try
				{
						Connection con = connect();
						
						if (con == null)
						{
							return "Error while connecting to the database for updating.";
						}
						
						// create a prepared statement
						
						String query = "UPDATE review SET projectCode=?,review=?,decision=? WHERE reviewID=?"; 
						
						PreparedStatement preparedStmt = con.prepareStatement(query);
						// binding values
						 preparedStmt.setString(1, projectCode); 
						 preparedStmt.setString(2, review); 
						 preparedStmt.setString(3, decision); 
						 preparedStmt.setInt(4, Integer.parseInt(reviewID)); 
						// execute the statement
						preparedStmt.execute();
						
						con.close();
						
						String newreview = readreview();
						output = "{\"status\":\"success\", \"data\": \"" +
						newreview + "\"}";
				}
				catch (Exception e)
				{
					output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
					System.err.println(e.getMessage());
				}
				return output;
		}
		
		
	public String deletereview(String reviewID)
	{
		String output = "";
		try
		{
				Connection con = connect();
				if (con == null)
				{	
					return "Error while connecting to the database for deleting."; 
				}
				
				// create a prepared statement
				
				String query = "delete from review where reviewID=?"; 
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				
				preparedStmt.setInt(1, Integer.parseInt(reviewID));
				// execute the statement
				preparedStmt.execute();
				
				con.close();
				
				String newreview= readreview();
				output = "{\"status\":\"success\", \"data\": \"" +
				newreview + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
		}


}
