package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Product {
	
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
		
		
	public String insertProduct(String reviewID) 
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
			
			 String query = " insert into product (`productID`,`reviewID`)"+ " values (?, ?)"; 
			 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, reviewID); 
			// execute the statement

			preparedStmt.execute();
			
			con.close();
			
			String newProduct = readProduct();
			output = "{\"status\":\"success\", \"data\": \"" +
					newProduct + "\"}";
			
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
				System.err.println(e.getMessage());
		}
			
		return output;
	}


	public String readProduct() 
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
		        
	            output = "<table border='1'><tr><th>product ID</th>"
	             + "<th>review ID</th>"
	             + "<th>Update</th><th>Remove</th></tr>";
		
				String query = "select * from product";
				
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			
			while (rs.next())
			{
				String productID = Integer.toString(rs.getInt("productID"));
				String reviewID = rs.getString("reviewID");
				
				// Add into the html table
				output += "<tr> <td>"  + productID +  "</td>";
				output += "<td>" + reviewID + "</td>";
			
	              
	      //buttons
	            
	            output += "<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary' data-itemid='" + productID + "'></td>"
	            		+ "<td><input name = 'btnRemove' type='button' value = 'Remove' "
	            		+ "class = 'btnRemove btn btn-danger' data-itemid='" + productID + "'>"
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


		public String updateProduct(String productID, String reviewID)
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
						
						 String query = "UPDATE product SET reviewID=? WHERE productID=?"; 
						 
						PreparedStatement preparedStmt = con.prepareStatement(query);
						// binding values
						 preparedStmt.setString(1, reviewID); 
						 preparedStmt.setInt(2, Integer.parseInt(productID)); 
						// execute the statement
						preparedStmt.execute();
						
						con.close();
						
						String newProduct = readProduct();
						output = "{\"status\":\"success\", \"data\": \"" +
					    newProduct + "\"}";
				}
				catch (Exception e)
				{
					output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
					System.err.println(e.getMessage());
				}
				return output;
		}
		
		
	public String deleteProduct(String productID) 
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
				
				 String query = "delete from product where productID=?"; 
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				
				preparedStmt.setInt(1, Integer.parseInt(productID));
				// execute the statement
				preparedStmt.execute();
				
				con.close();
				
				String newProduct = readProduct();
				output = "{\"status\":\"success\", \"data\": \"" +
				newProduct + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
		}
	
	
	
	
	

}
