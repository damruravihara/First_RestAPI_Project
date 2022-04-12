package modal;

import java.sql.*; 

public class Item {
	
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eg_online_system", "root", "pafproject");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public String insertItem(String name, String address, String phone_no, String nic) {
		
		String output = "";
		
		try {
			Connection con = connect();
			
			if (con == null)
			{return  "Error while connecting to the database for inserting.";}
			
			// create a prepared statement
			String query = "  insert into user (`iduser`,`name`,`address`,`phone_no`,`nic`)" + " values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, name); 
			 preparedStmt.setString(3, address); 
			 preparedStmt.setString(4, phone_no); 
			 preparedStmt.setString(5, nic);
			 
			// execute the statement
			 
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Inserted successfully"; 
		}
		catch (Exception e) {
			 output = "Error while inserting the user."; 
			 System.err.println(e.getMessage()); 
		}
		
		return output;
	}
	
	public String readItems(){
		
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if(con == null)
			{
				return "Error while connecting to the database for reading."; 
			}
			
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>User Name</th><th>User Address</th>" +
			 "<th>Phone Number</th>" + 
			 "<th>NIC</th>" +
			 "<th>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select * from user"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 // iterate through the rows in the result set
			 while (rs.next()) {
				 String iduser = Integer.toString(rs.getInt("iduser")); 
				 String name = rs.getString("name"); 
				 String address = rs.getString("address"); 
				 String phone_no = rs.getString("phone_no"); 
				 String nic = rs.getString("nic"); 
				 
				 // Add into the html table
				 output += "<tr><td>" + name + "</td>"; 
				 output += "<td>" + address + "</td>"; 
				 output += "<td>" + phone_no + "</td>"; 
				 output += "<td>" + nic + "</td>";
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='items.jsp'>"
				 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
				 + "<input name='itemID' type='hidden' value='" + iduser + "'>" + "</form></td></tr>"; 
			 }
			 con.close(); 
			 // Complete the html table
			 output += "</table>";
		}
		catch(Exception e)
		{
			 output = "Error while reading the user."; 
			 System.err.println(e.getMessage()); 
		}
		
		return output;
	}
	
	public String updateItem(String iduser, String name, String address, String phone_no, String nic) 
	{
		String output = "";
		
		try {
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for updating."; } 
			 
			 // create a prepared statement
			 String query = "UPDATE user SET name=?,address=?,phone_no=?,nic=? WHERE iduser=?";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setString(1, name); 
			 preparedStmt.setString(2, address); 
			 preparedStmt.setString(3, phone_no); 
			 preparedStmt.setString(4, nic); 
			 preparedStmt.setInt(5, Integer.parseInt(iduser)); 
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Updated successfully"; 
		}
		 catch (Exception e) 
		 { 
		 output = "Error while updating the user."; 
		 System.err.println(e.getMessage()); 
		 }
		
		return output;
	}
	
	public String deleteItem(String iduser) {
		 String output = ""; 
		 try {
			 Connection con = connect(); 
			 
			 if (con == null) 
			 {return "Error while connecting to the database for deleting."; }
			 
			 // create a prepared statement
			 String query = "delete from user where iduser=?"; 
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(iduser)); 
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 
			 output = "Deleted successfully"; 
		 }
		 catch (Exception e) 
		 { 
			 output = "Error while deleting the user."; 
			 System.err.println(e.getMessage()); 
		 } 
		 
		 return output;
	}
	
}
