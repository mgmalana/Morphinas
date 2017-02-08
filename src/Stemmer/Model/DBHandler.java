package Stemmer.Model;

import Stemmer.Model.ObjectPool.JDBCConnectionPool;
import Stemmer.Model.ObjectPool.Test.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Utility.print.println;

/**
 * Created by laurenztolentino on 02/08/2017.
 */
public class DBHandler
{
	// JDBC driver name and database URL
	private final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL 				= "jdbc:mysql://localhost:3306/morphinas?autoReconnect=true&useSSL=false";
	// Database credentials
	static final String USER 				= "root";
	static final String PASS 				= "dlsu1234";
	// SQL Returns
	ResultSet rs;
	PreparedStatement query 				= null;
	String word;
	ArrayList<String> results;

	public DBHandler()
	{

	}

	public void createConnection()
	{
		results = new ArrayList<>();
		// instantiate the jdbcpool
		JDBCConnectionPool pool = new JDBCConnectionPool(JDBC_DRIVER, DB_URL, USER, PASS);
		// Get a connection:
		Connection con = pool.checkOut();
		/* Perform query */
		try
		{
			query = con.prepareStatement("select * from rootWords WHERE Word = '" +word+ "'");
			rs 	  = query.executeQuery();
			while ( rs.next() )
			{
				results.add(rs.getString(1));
			}
		}
		catch (SQLException e)
		{
			println("Hi Ma'am/Sir, may problema po sa inyong query o baka sa inyong database connection");
			println("Paki-check nalang po ang inyong DB_URL, Username, at Password at siguraduhin na ito ay tama");
			e.printStackTrace();
		}
		// Return the connection:
		pool.checkIn(con);
	}

	public Boolean lookup(String word)
	{
		this.word = word;
		createConnection();
		if( results != null)
		{
			return true;
		}
		return false;
	}

	public ArrayList<String> getResults()
	{
		return results;
	}

	public void setResults(ArrayList<String> results) {
		this.results = results;
	}

	public static class testThis
	{
		public static void main(String args[])
		{
			DBHandler m = new DBHandler();
			println("Word: " + m.lookup("tao"));
			println("Word: " + m.lookup("bangkay"));
			println("Word: " + m.lookup("barangay"));
		}
	}
}
