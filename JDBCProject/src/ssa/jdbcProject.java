package ssa;


import java.sql.*;
import java.lang.*;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class jdbcProject {


		
		static Connection myConn=null;
		static Statement stmt= null;
		static ResultSet rs= null;

	
		
	
		
		
		
		public static void main(String[] args) throws SQLException, FileNotFoundException, IOException{
			
		
			
			System.out.println("*********************************");
			System.out.println("//  Exercise 1 - Add a record  //  ");
			System.out.println("*********************************");
			insert();
			fetchOne();
			fetchAll();
			System.out.println("\n\n***********************************");
			System.out.println("// Exercise 2 - Update a record  // ");
			System.out.println("**********************************");
			update();
			fetchOne();
			fetchAll();
			System.out.println("\n\n***********************************");
			System.out.println("// Exercise 3 - Delete a record  // ");
			System.out.println("**********************************");
			delete();
			fetchAll();
		
			
			
	
		}
		public static void fetchAll() throws SQLException{
			
			
			try{
				load();
				
				
				//1. create a statement
				stmt=myConn.createStatement();
				
				//3. Execute SQL query
				
				ResultSet rs = stmt.executeQuery("select student.id,student.first_name,student.last_name,student.gpa,student.sat,major.major_description from student join major on student.major_id = major.id order by id");
				
				//4. Process the result set
				
				System.out.printf("\n\n\n%10s %20s %20s %20s %20s %20s", "ID", "First Name", "Last Name", "GPA", "SAT", "Major");
				System.out.println("\n****************************************************************************************************************************");
				while(rs.next())
					
					System.out.printf("\n%10d %18s %23s %20.2f %20d %25s", rs.getInt("id"),rs.getString("first_name"), rs.getString("last_name"), rs.getDouble("gpa"), rs.getInt("sat"),rs.getString("major_description"));
				
				
			}catch(Exception exc){
				  exc.printStackTrace();
			}
			
			finally{
				
				Myclose(myConn,stmt,rs);
			}
		}
			
		
		public static void fetchOne() throws SQLException{
			
			
				try{
					load();
					
					
					//1. create a statement
					stmt=myConn.createStatement();
					
					//3. Execute SQL query
					
					ResultSet rs = stmt.executeQuery("select student.id,student.first_name,student.last_name,student.gpa,student.sat,major.major_description from student join major on student.major_id = major.id where first_name = 'George' and last_name = 'Washington' order by id;");
					
					//4. Process the result set
					
					System.out.printf("\n\n\n\n%10s %20s %20s %20s %20s %20s", "ID", "First Name", "Last Name", "GPA", "SAT", "Major");
					System.out.println("\n****************************************************************************************************************************");
					while(rs.next())
						System.out.printf("\n%10d %18s %23s %20.2f %20d %25s", rs.getInt("id"),rs.getString("first_name"), rs.getString("last_name"), rs.getDouble("gpa"), rs.getInt("sat"),rs.getString("major_description"));
					
					
				}catch(Exception exc){
					  exc.printStackTrace();
				}
				
				finally{
					
					Myclose(myConn,stmt,rs);
				}
			}
				public static void Myclose(Connection con, Statement tsmt, ResultSet rs) throws SQLException{
					
					if(con!=null)
						con.close();
					if(tsmt !=null)
						tsmt.close();
					if(rs != null)
						rs.close();
				}
				

		public static void load() throws SQLException{
	try{
			Properties props = new Properties();
			props.load(new FileInputStream("creds.properties"));
			
			
			String url = props.getProperty("url");
			 String user = props.getProperty("user");
			 String pwd = props.getProperty("pwrd");
			//props.load(new fileInputStream("c:/file/location")
			 
			 
				myConn= (Connection)DriverManager.getConnection(url,user,pwd);
				
	}catch(Exception exc){
		  exc.printStackTrace();
	}
			
		}
		public static void insert() throws SQLException{
			
			try{   load();
				
			
            				
				//. create a statement object to execute the desired query/update/insert/delete action
				stmt=myConn.createStatement();
				
				//3. Execute SQL query
				
				String sql =
            "insert student(id,first_name,last_name,gpa, sat, major_id) values(200,'George','Washington',4.0,1600,7)";
						          
				
				int RowsAffected = stmt.executeUpdate(sql);
				
				//4. Process the result set
				/*
				while(rs.next())
					System.out.println("Name" + " " + rs.getString("first_name") + " " + rs.getString("last_name") + " " +
				      rs.getDouble("gpa"));
				
				System.out.println("Data Fetched");
				*/
			}catch(Exception exc){
				  exc.printStackTrace();
			}
			
			finally{
				

				if(myConn!=null)
					myConn.close();
				if(stmt !=null)
					stmt.close();
				if(rs != null)
					rs.close();
			}
		}
 /////////////////////////////////////////////////////////////method end///////////////////////////////////
		public static void update() throws SQLException{
			
			try{   load();
				
			
            				
				//. create a statement object to execute the desired query/update/insert/delete action
				stmt=myConn.createStatement();
				
				//3. Execute SQL query
				
				String sql1 ="update student set gpa = 3.5 where id = 200";
				String sql2 ="update student set sat = 1450 where id = 200";
				String sql3 ="update student set major_id = 1 where id = 200";
						          
				
				//int RowsAffected = stmt.executeUpdate(sql1);
				stmt.executeUpdate(sql1);
				stmt.executeUpdate(sql2);
				stmt.executeUpdate(sql3);
				
				//4. Process the result set
				/*
				while(rs.next())
					System.out.println("Name" + " " + rs.getString("first_name") + " " + rs.getString("last_name") + " " +
				      rs.getDouble("gpa"));
				
				System.out.println("Data Fetched");
				*/
			}catch(Exception exc){
				  exc.printStackTrace();
			}
			
			finally{
				

				if(myConn!=null)
					myConn.close();
				if(stmt !=null)
					stmt.close();
				if(rs != null)
					rs.close();
			}
		}
public static void delete() throws SQLException{
			
			try{   load();
				
			
            				
				//. create a statement object to execute the desired query/update/insert/delete action
				stmt=myConn.createStatement();
				
				//3. Execute SQL query
				
				String sql1 ="delete from student where sat =1450 and last_name = 'Washington'";
				
						          
				
				int RowsAffected = stmt.executeUpdate(sql1);
				
				
				System.out.println("\n" + RowsAffected + " :Row(s) has been deleted");
			}catch(Exception exc){
				  exc.printStackTrace();
			}
			
			finally{
				

				if(myConn!=null)
					myConn.close();
				if(stmt !=null)
					stmt.close();
				if(rs != null)
					rs.close();
			}
		}


			}
		

		


	
	
	

