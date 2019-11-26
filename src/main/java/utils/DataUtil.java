package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.io.Reader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DataUtil {
	public static Object[][] getData(Xls_Reader xls, String testCaseName) {
		String sheetName = "Data";
		// reads data for only testCaseName

		int testStartRowNum = 1;
		System.out.println(xls.getCellData(sheetName, 0, testStartRowNum));
		while (!xls.getCellData(sheetName, 0, testStartRowNum).equals(testCaseName)) {
			testStartRowNum++;
		}
		System.out.println("Test starts from row - " + testStartRowNum);
		int colStartRowNum = testStartRowNum + 1;
		int dataStartRowNum = testStartRowNum + 2;

		// calculate rows of data
		int rows = 0;
		while (!xls.getCellData(sheetName, 0, dataStartRowNum + rows).equals("")) {
			rows++;
		}
		System.out.println("Total rows are  - " + rows);

		// calculate total cols
		int cols = 0;
		while (!xls.getCellData(sheetName, cols, colStartRowNum).equals("")) {
			cols++;
		}
		System.out.println("Total cols are  - " + cols);
		Object[][] data = new Object[rows][1];
		// read the data
		int dataRow = 0;
		Hashtable<String, String> table = null;
		for (int rNum = dataStartRowNum; rNum < dataStartRowNum + rows; rNum++) {
			table = new Hashtable<String, String>();
			for (int cNum = 0; cNum < cols; cNum++) {
				String key = xls.getCellData(sheetName, cNum, colStartRowNum);
				String value = xls.getCellData(sheetName, cNum, rNum);
				table.put(key, value);
				// 0,0 0,1 0,2
				// 1,0 1,1
			}
			data[dataRow][0] = table;
			dataRow++;
		}
		return data;
	}

	// true - Y
	// false - N
	public static boolean isTestRunnable(Xls_Reader xls, String testName) {
		int rows = xls.getRowCount("TestCases");
		for (int rNum = 2; rNum <= rows; rNum++) {
			String tNameXLS = xls.getCellData("TestCases", "TCID", rNum);
			if (tNameXLS.equals(testName)) {// found
				String runmode = xls.getCellData("TestCases", "Runmode", rNum);
				if (runmode.equals("Y"))
					return true;
				else
					return false;
			}
		}
		return false;
	}

	public static void main(String args[]) {
		List<HashMap<String, String>> li = new ArrayList<HashMap<String, String>>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://10.0.20.19/extramarks_application", "javac_test",
					"0oMR235ui4o3j24n92nlk");
			// here sonoo is database name, root is username and password
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user");
			while (rs.next()) {
				HashMap<String, String> hm = new HashMap<String, String>();
				for (int i = 0; i <= 1; i++) {
					hm.put("id", rs.getString(1));
					hm.put("Username", rs.getString(3));
					hm.put("MobileNumber", rs.getString(4));
				}
				li.add(hm);
				System.out.println(li);
			}

			for (int j = 0; j < li.size(); j++) {
				System.out.println(li.get(j).get("MobileNumber"));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static List<HashMap<String, String>> connectionsGetData() throws ClassNotFoundException, SQLException {
		List<HashMap<String, String>> li = new ArrayList<HashMap<String, String>>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://10.0.20.19/extramarks_application", "javac_test",
					"0oMR235ui4o3j24n92nlk");
			// here sonoo is database name, root is username and password
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user");
			for (int i = 0; rs.next(); i++) {
				HashMap<String, String> hm = new HashMap<String, String>();
				hm.put("id", rs.getString(1));
				hm.put("Username", rs.getString(3));
				hm.put("MobileNumber", rs.getString(4));
				li.add(hm);

			}
			/*
			 * for (int j = 0; j < li.size(); j++) {
			 * System.out.println(li.get(j).get("MobileNumber")); }
			 */
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return li;
	}

	public static void connectionsetup2() throws ClassNotFoundException, SQLException {
		// Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
		// String dbUrl = "jdbc:mysql://10.0.3.99/website_ver2";
		/*
		 * String dbUrl = "jdbc:mysql://10.0.20.107/phpmyadmin";
		 */
		// test-automation server
		String dbUrl = "jdbc:mysql://10.1.17.6/phpauto/";
		// Database Username
		// String username = "website";
		/*
		 * String username = "ReadDaemons";
		 */ String username = "auto_test"; // automation server
		String password = "Abc!@#456"; // automation server
		// Database Password
		// String password = "website";
		// String password = "KU8wHAyyacdnYpp9FJqCbECbBhfFQxReC5DLUZ";

		// Query to Execute
		String query = "select *from t_sms_log ";

		// Load mysql jdbc driver
		Class.forName("com.mysql.jdbc.Driver");

		// Create Connection to DB
		Connection con = DriverManager.getConnection(dbUrl, username, password);

		// Create Statement Object
		Statement stmt = con.createStatement();

		// Execute the SQL Query. Store results in ResultSet

		ResultSet rs = stmt.executeQuery(query);

		// While Loop to iterate through all data and print results
		List li = new ArrayList();
		/*
		 * int RowNumber=rs.getRow(); System.out.println(" total row >"+RowNumber); for
		 * (int row=1;row<=RowNumber;row++){ rs.absolute(-row);// for last row in Table
		 * int count = rs.getMetaData().getColumnCount();
		 * System.out.println("columncount >"+count); for(int i=1;i<=count;i++){ String
		 * ColumnNAME=rs.getMetaData().getColumnName(i); String
		 * ColName=i+" "+"ColName >" +ColumnNAME;
		 * 
		 * //System.out.println(ColName); String Columnvalue=rs.getString(i);
		 * System.out.println("ROW"+row+ ":"+ ColumnNAME + ":"+" "+Columnvalue);
		 * 
		 * }
		 */
		// last entry in Database
		rs.absolute(-1);// for last row in Table
		int count = rs.getMetaData().getColumnCount();
		System.out.println("columncount >" + count);
		for (int i = 1; i <= count; i++) {
			String ColumnNAME = rs.getMetaData().getColumnName(i);
			String ColName = i + " " + "ColName >" + ColumnNAME;
			System.out.println(ColName);
			String Columnvalue = rs.getString(i);
			System.out.println(ColumnNAME + ":" + " " + Columnvalue);
		}
		String OTPMessage = rs.getString(5);
		String MobileNumber = rs.getString(3);

		/**
		 * Scanner input = new Scanner(System.in); System.out.print("Enter Mobile Number
		 * > "); String inputString = input.nextLine(); System.out.print("You entered :
		 * "); System.out.println(inputString);
		 * 
		 */

		System.out.println("mobile no. > " + MobileNumber);
		System.out.println("SMS Text >" + OTPMessage);
		int OTP = Integer.parseInt(OTPMessage.replaceAll("\\D", ""));
		System.out.println("Required OTP for Registration > " + OTP);

		/*
		 * if (inputString==MobileNumber){ System.out.println(OTPMessage); int OTP =
		 * Integer.parseInt(OTPMessage.replaceAll("\\D", ""));
		 * 
		 * 
		 * System.out.println("Required OTP for Registration : " + OTP);
		 * 
		 * } else { System.out.println(" mobile comparision is failed"); }
		 */
		/*
		 * rs.absolute(-2);
		 * 
		 * 
		 * String OTP1= rs.getString(5); System.out.println(OTP1); for(String
		 * w:OTP1.split("\\s",0)){ li.add(w); //System.out.println(w); } int
		 * Arraysize=li.size(); //System.out.println(Arraysize); String
		 * otpnumber=(String) li.get(4); System.out.println("OTP FOR REGISTRATION :" +
		 * otpnumber);
		 */

		/*
		 * while (rs.next()){ //while (rs.absolute(3)){ String myName = rs.getString(1);
		 * String myAge = rs.getString(2); System. out.println(myName+"  "+myAge);
		 * 
		 * 
		 * String OTP= rs.getString(5);
		 * 
		 * System.out.println(OTP); }
		 */

		// closing DB Connection
		con.close();
	}

}
