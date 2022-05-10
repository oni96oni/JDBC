package com.conn.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConn {
	
	public void localConnect() throws Exception{
		// 1. Driver 등록
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		// 2. DBMS 연결
		Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@127.0.0.1:1521/XE","puser1", "puser1"
				);
		
		// 3. Statement 생성
		Statement stat = conn.createStatement();
		
		// 4. SQL 질의문 전송 및 반환
//		int rowCount = stat.executeUpdate("INSERT INTO DEPARTMENTS VALUES(280, 'Tester', NULL, 1700)"); //INSERT문 예시
//		int rowCount = stat.executeUpdate("UPDATE DEPARTMENTS SET DEPARTMENT_NAME = 'Dept Tester' WHERE DEPARTMENT_ID = 280"); //UPDATE문 예시
		int rowCount = stat.executeUpdate("DELETE FROM DEPARTMENTS WHERE DEPARTMENT_ID = 280"); //DELETE문 예시
		System.out.println(rowCount + "개 행이 반영되었습니다.");
		
		ResultSet rs = stat.executeQuery("SELECT * FROM DEPARTMENTS");
		while(rs.next()){
			System.out.print(rs.getString(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getString(3) + "\t");
			System.out.print(rs.getString(4) + "\n");
		}
		
		// 5. 객체 반환
		rs.close(); //ResultSet 사용한 경우 반납처리
		stat.close();
		conn.close();
		
	}
	
	public static void main(String[] args) throws Exception {
		DBConn myDB = new DBConn();
		myDB.localConnect();
	}

}
