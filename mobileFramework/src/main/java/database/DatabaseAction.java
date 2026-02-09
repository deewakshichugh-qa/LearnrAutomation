package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import logger.ScriptLogger;
import objects.exceptions.ApplicationException;

/**
 * Represents database validation methods
 * 
 * @author Manmay Kumar Mohanty
 * @version 1.0.0
 * @since 2021-04-22
 * 
 */

public class DatabaseAction {
	static Connection conn = DatabaseAccess.conn;

	
	/**
	 * This method is to fire an update Query. the return type is  String eg:-"0 row/s affected."
	 * form of a map
	 * 
	 * @param conn
	 * @param query
	 * @return map
	 * @throws Exception
	 * 
	 */
	public static String executeUpdate(String query) throws Exception {
		ScriptLogger.info();
		Statement stmt = null;
		stmt = DatabaseAccess.conn.createStatement();
		int rowsAffected=0;
		try {
			rowsAffected = stmt.executeUpdate(query);			
		} catch (Exception e) {
			throw new Exception();
		}
		ScriptLogger.info(rowsAffected+" row/s affected.");
		ScriptLogger.debug(rowsAffected+" row/s affected.");
		return rowsAffected+" row/s affected.";	
	}
	
	public static void executeProcedure(String storedProc) throws Exception {
		ScriptLogger.info();
		// Example of Job Name=String B = "'Update Category Feedback Stats_Version_2.0'";
    	try {
			PreparedStatement pstmt=DatabaseAccess.conn.prepareStatement(storedProc);
			pstmt.execute();
		} catch (Exception e) {
			throw new ApplicationException(e,"Unable to execute procedure");
		}
	}
	
	
	
	
	
	
	
	
	/*public static void main(String[] args) throws Exception {
		DatabaseAccess.connectDatabase("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://qa-20170830.csp8v7nskf8t.us-east-1.rds.amazonaws.com;database=gurusql2", "prod", "Pow3rEdg3");
		String query="select * from tGuruBill where BillID in (941038,941039)";
		System.out.println(DatabaseAccess.executeQuery(query));
		query="UPDATE tGuruBill SET AutoPaymentDate = '2017-10-12' where BillID in (941038,941039)";
		System.out.println(executeUpdate(query));
		
		query="select * from tGuruBill where BillID in (941038,941039)";
		System.out.println(DatabaseAccess.executeQuery(query));
		DatabaseAccess.closeDatabase();
	}*/

}

