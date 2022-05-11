package com.conn.exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Employees {
	public void getSalary(int salary) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@127.0.0.1:1521/XE","puser1", "puser1"
				);

		Statement stat = conn.createStatement();

		ResultSet rs = stat.executeQuery("SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY FROM EMPLOYEES WHERE EMPLOYEES.SALARY = "+salary);
		while(rs.next()){
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getString(3) + "\t");
			System.out.print(rs.getInt(4) + "\n");
		}

		rs.close(); 
		stat.close();
		conn.close();
	}
	
	public static void main(String[] args) throws Exception {
		Employees db = new Employees();
		db.getSalary(3000);
		
	}
}
