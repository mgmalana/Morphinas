package Stemmer.ObjectPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by laurenz on 07/02/2017.
 */
public class DatabasePool
{
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/morphinas?autoReconnect=true&useSSL=false";
	// Database credentials
	static final String USER = "root";
	static final String PASS = "dlsu1234";
	// SQL Returns
	ResultSet rs;
	PreparedStatement psFindRoot = null;

	public DatabasePool()
	{

	}

}
