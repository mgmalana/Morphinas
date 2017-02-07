package Stemmer.ObjectPool.Test;

import Stemmer.ObjectPool.JDBCConnectionPool;

import java.sql.Connection;

/**
 * Created by laurenz on 07/02/2017.
 */
public class Main
{



	public static void main(String args[])
	{
		// Do something...
//    ...

		// Create the ConnectionPool:
		JDBCConnectionPool pool = new JDBCConnectionPool(
				"org.hsqldb.jdbcDriver", "jdbc:hsqldb://localhost/mydb",
				"sa", "secret");

		// Get a connection:
		Connection con = pool.checkOut();

		// Use the connection
//    ...

		// Return the connection:
		pool.checkIn(con);

	}
}
