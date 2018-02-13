package main.java;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLiteHandler {
	private Connection c;
	private Statement stmt;
	private boolean open;
	public void openConnection(){
		File file = new File(".");
		
		String url = "jdbc:sqlite:"+file.getAbsolutePath()+"/database/parceloption.db";
	    try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection(url);
	         open = true;
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	}
	
	public void createPickupOptionTable() {
		if (open) {
			try {
				stmt = c.createStatement();
				String sql = "CREATE TABLE PARCELOPTION " + 
						  "(ID 			   INTEGER 		PRIMARY KEY     AUTOINCREMENT,"
						+ " NAME	       TEXT    						NOT NULL, "
						+ " PRICE	       DOUBLE 	   					NOT NULL)";
				stmt.executeUpdate(sql);
				stmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void createParcelDeliveryOptionTable() {
		if (open) {
			try {
				stmt = c.createStatement();
				String sql = "CREATE TABLE PARCELDELIVERYOPTION " + 
						  "(ID 			   INTEGER 		PRIMARY KEY     AUTOINCREMENT,"
						+ " OPT_ID	       INTEGER		FORGEIN KEY		NOT NULL) ";
				stmt.executeUpdate(sql);
				stmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void createParcelSentOptionTable() {
		if (open) {
			try {
				stmt = c.createStatement();
				String sql = "CREATE TABLE PARCELSENTOPTION " + 
						  "(ID 			   INTEGER 		PRIMARY KEY     AUTOINCREMENT,"
						+ " OPT_ID	       INTEGER		FORGEIN KEY		NOT NULL) ";
				stmt.executeUpdate(sql);
				stmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void insertDeliveryOption(String name, double price){
		insertParcelOption(name, price);
		int id = -1;
		try {
			stmt = c.createStatement();
			 ResultSet rs = stmt.executeQuery( "SELECT * FROM PARCELOPTION WHERE NAME='"+name+"';" );
		      
		      while ( rs.next() ) {
		    	 id = rs.getInt("id");
		      }
		      rs.close();
		      stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(id > 0){
			try {
				stmt = c.createStatement();
		        String sql = "INSERT INTO PARCELDELIVERYOPTION (OPT_ID) " +
                        "VALUES ("+id+");"; 
		        stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void insertSentOption(String name, double price){
		insertParcelOption(name, price);
		int id = -1;
		try {
			stmt = c.createStatement();
			 ResultSet rs = stmt.executeQuery( "SELECT * FROM PARCELOPTION WHERE NAME='"+name+"';" );
		      
		      while ( rs.next() ) {
		    	 id = rs.getInt("id");
		      }
		      rs.close();
		      stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(id > 0){
			try {
				stmt = c.createStatement();
		        String sql = "INSERT INTO PARCELSENTOPTION (OPT_ID) " +
                        "VALUES ("+id+");"; 
		        stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public PickupOption getOptionByName(String name){
		PickupOption o = new PickupOption(name, 0.0);
		try {
			stmt = c.createStatement();
			 ResultSet rs = stmt.executeQuery( "SELECT * FROM PARCELOPTION WHERE parceloption.name='"+name+"';" );
		      
		      while ( rs.next() ) {
		    
		         name = rs.getString("name");
		         double price  = rs.getDouble("price");
		         o.setName(name);
		         o.setPrice(price);
		      }
		      rs.close();
		      stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
	
	public List<PickupOption> getSentOptions(){
		List<PickupOption> res= new ArrayList<PickupOption>();
		try {
			stmt = c.createStatement();
			 ResultSet rs = stmt.executeQuery( "SELECT PARCELOPTION.name, PARCELOPTION.price FROM PARCELSENTOPTION LEFT JOIN PARCELOPTION ON parcelsentoption.opt_id = parceloption.id;" );
		      
		      while ( rs.next() ) {
		    
		         String  name = rs.getString("name");
		         double price  = rs.getDouble("price");
		         res.add(new ParcelSentOption(name, price));
		      }
		      rs.close();
		      stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return res;
	}
	
	public List<PickupOption> getDeliveryOptions(){
		List<PickupOption> res= new ArrayList<PickupOption>();
		try {
			stmt = c.createStatement();
			 ResultSet rs = stmt.executeQuery( "SELECT PARCELOPTION.name, PARCELOPTION.price FROM PARCELDELIVERYOPTION LEFT JOIN PARCELOPTION ON parceldeliveryoption.opt_id = parceloption.id;" );
		      
		      while ( rs.next() ) {
		    
		         String  name = rs.getString("name");
		         double price  = rs.getDouble("price");
		         res.add(new ParcelDeliveryOption(name, price));
		      }
		      rs.close();
		      stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return res;
	}
	
	public void insertParcelOption(String name, double price){
		if(open){
			try {
				stmt = c.createStatement();
		        String sql = "INSERT INTO PARCELOPTION (NAME,PRICE) " +
                        "VALUES ('"+ name+"', "+ price+");"; 
		        stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public List<PickupOption> getParcelOptions(){
		List<PickupOption> res= new ArrayList<PickupOption>();
		try {
			stmt = c.createStatement();
			 ResultSet rs = stmt.executeQuery( "SELECT * FROM PARCELOPTION;" );
		      
		      while ( rs.next() ) {
		    
		         String  name = rs.getString("name");
		         double price  = rs.getDouble("price");
		         res.add(new PickupOption(name, price));
		      }
		      rs.close();
		      stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return res;
	}
}
