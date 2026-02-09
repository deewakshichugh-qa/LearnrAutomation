package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logger.ScriptLogger;


/**
 * Represents database acessing methods
 * 
 * @author Manmay Kumar Mohanty
 * @version 1.0.0
 * @since 2021-04-22
 * @updatedOn 2021-04-22
 */

public class DatabaseAccess {
	public static Connection conn = null;

	/**
	 * Open DB Connection
	 * 
	 * @param driver
	 * @param url
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	public static Connection connectDatabase(String driver, String url, String username, String password)
			throws Exception {
		ScriptLogger.info();	
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			ScriptLogger.info("DB Connected"+"\n URL:- "+url+"\n USER:- "+username);
			ScriptLogger.debug("DB Connected"+"\n URL:- "+url+"\n USER:- "+username);
		} catch (Exception e) {
			throw new Exception();
		}
		
		return conn;
	}


	/**
	 * This method is to fire any query. the return type is the ResultSet in the
	 * form of a map
	 * 
	 * @param conn
	 * @param query
	 * @return map
	 * @throws Exception
	 * 
	 */
	
	public static Map<String, List<Object>> executeQuery(String query) throws Exception {
		ScriptLogger.info();
		ResultSetMetaData rsmd = null;
		Statement stmt = null;
		ResultSet rs = null;

		stmt = conn.createStatement();

		try {
			rs = stmt.executeQuery(query);
		} catch (Exception e) {
			throw new Exception();
		}

		rsmd = rs.getMetaData();

		int columnCount = rsmd.getColumnCount();

		Map<String, List<Object>> map = new HashMap<String, List<Object>>(columnCount);
		for (int i = 1; i <= columnCount; ++i) {

			map.put(rsmd.getColumnName(i), new ArrayList<Object>());
		}

		while (rs.next()) {
			for (int i = 1; i <= columnCount; ++i) {
				map.get(rsmd.getColumnName(i)).add(rs.getObject(i));
			}
		}
             System.out.println(map.containsKey(1));
		rs.close();
		stmt.close();
		return map;
	}
	
	/**
	 * This method is to close the connection to the database.
	 *  
	 * @throws Exception
	 * 
	 */

	public static void closeDatabase() throws Exception {
		ScriptLogger.info();
		try {
			conn.close();
		} catch (Exception e) {
			throw new Exception();
		}
		ScriptLogger.info("DB Connected closed");
		ScriptLogger.debug("DB Connected closed");
	}
	
	/*Unit test method
	 * public static void main(String[] args) throws Exception {	
		connectDatabase("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://qa-20170830.csp8v7nskf8t.us-east-1.rds.amazonaws.com;database=gurusql2", "prod", "Pow3rEdg3");
		String query="select * from tgurubill where GuruID = 2073679";
		System.out.println(DatabaseAccess.executeQuery(query));	
		
	}*/
}
