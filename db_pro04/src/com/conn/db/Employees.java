package com.conn.db;

import java.sql.ResultSet;

public class Employees {

	private DBConn db;

	public Employees() {
		try {
			db = new DBConn("localhost", "1521", "XE", "puser1", "puser1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSalary(int salary)  {
		String query = "SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY FROM EMPLOYEES WHERE EMPLOYEES.SALARY = "+salary;

		ResultSet rs;
		try {
			rs = db.sendSelectQuery(query);
			while(rs.next()){
				System.out.print(rs.getString(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				System.out.print(rs.getString(3) + "\t");
				System.out.print(rs.getString(4) + "\n");
				rs.close();
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)  {
		Employees db = new Employees();
		db.getSalary(10000);
		db.close();
	}
}
