package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents database validation methods
 * 
 * @author Manmay Kumar Mohanty
 * @version 1.0.0
 * @since 2021-04-22
 */

public class DatabaseValidation {

	/**
	 * Get Column Names of the table
	 * 
	 * @param map of all the db values
	 * @return List<String> of all table's column names
	 * @throws Exception
	 * 
	 */

	public static List<String> getTableColumnName(Map<String, List<Object>> map) throws Exception {
		List<String> columnNameList = new ArrayList<String>();
		if (!map.isEmpty()) {
			

			for (String key : map.keySet()) {
				columnNameList.add(key);

			}

			for (String string : columnNameList) {
				System.out.println("Column name list is:- " + string);
			}

			return columnNameList;

		} else {
			throw new Exception("There are no records in the database. Please check the query.l");
		}

	}

	/**
	 * Get Values inside the table
	 * 
	 * @param map
	 * @throws Exception
	 */

	public static List<List<Object>> getColumnValueByNumber(Map<String, List<Object>> map,int columnNum) throws Exception {
		List<List<Object>>  dbValueList = new ArrayList<List<Object>>();
			
		if (!map.isEmpty()) {
			int dbSize=map.keySet().size();
			
			
			for (String key : map.keySet()) {
				
				
				
				dbValueList.add(map.get(key));

			}

			for (List<Object> list : dbValueList) {
				System.out.println("getDBValues list:-" + list);
			}

			return dbValueList;

		} else {
			throw new Exception("There are no records in the database. Please check the query.l");
		}

	}

	/**
	 * Get All data
	 * 
	 * @param map
	 * @throws Exception
	 */

	public static Map<String, List<Object>> getAllTableData(Map<String, List<Object>> map) throws Exception {
		if (!map.isEmpty()) {
			for (String key : map.keySet()) {
				System.out.println("Key is =" + key + "\nValue is =" + map.get(key));
			}

			return null;
		} else {
			throw new Exception("There are no records in the database. Please check the query.l");
		}

	}

	/**
	 * Get Number of Rows
	 * 
	 * @param map
	 * @throws Exception
	 */

	public static int getNumberofRows(Map<String, List<Object>> map) throws Exception {
		if (!map.isEmpty()) {
			System.out.println("Number of Rows"+map.keySet().size());
				return map.keySet().size();
			
		} else {
			throw new Exception("There are no records in the database. Please check the query.");
		}

	}

	/**
	 * Get Number of Columns
	 * 
	 * @param map
	 * @throws Exception
	 */

	public static int getNumberofColumns(Map<String, List<Object>> map) throws Exception {

		if (!map.isEmpty()) {
			System.out.println("Number of Columns"+map.keySet().size());
				return map.keySet().size();
				
			
		} else {
			throw new Exception("There are no records in the database. Please check the query.");
		}
	}


}
