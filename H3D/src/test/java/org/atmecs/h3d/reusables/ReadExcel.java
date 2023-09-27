package org.atmecs.h3d.reusables;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.atmecs.h3d.testscripts.LoginPage;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
public class ReadExcel {
	static Logger logger = Logger.getLogger(LoginPage.class);

	public static Map<String, String> getTestDataInMap(String testDataFile, String sheetName, String testcaseId) throws Exception {
	    Map<String, String> TestDataInMap = new TreeMap<String, String>();
	    String query = null;
	    query = String.format("SELECT * FROM %s WHERE Status='True' and TestCaseId='%s'",
	            sheetName, testcaseId);
	    Fillo fillo = new Fillo();
	    Connection conn = null;
	    Recordset recordset = null;
	    try {
	        conn = fillo.getConnection(testDataFile);
	        recordset = conn.executeQuery(query);
	        while (recordset.next()) {
	            for (String field : recordset.getFieldNames()) {
	                TestDataInMap.put(field, recordset.getField(field));
	            }
	        }
	    } catch (FilloException e) {
	    	logger.error("Error occured while fetching test data from Excel.",e);
	        e.printStackTrace();
	        throw new Exception("Test data cannot be retreived");
	    }finally {
	    	if(conn !=null) {
		    conn.close();
	    	}
	    }
	    return TestDataInMap;
	}
	
	
	public static void main(String[] args) throws Exception {
		Map<String,String> testDataMap = getTestDataInMap("D:\\H3DUITESTING\\H3D\\src\\test\\resources\\org.atmecs.h3d.data\\H3D_TestData.xlsx","Login","H3D_03");
		System.out.println(testDataMap.get("Username"));
	}
}
