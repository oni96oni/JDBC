package com.conn.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DBConn {
	
	private final static String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final static String BASE_URL = "jdbc:oracle:thin:@";
	private String url_address;
	private Statement stat;
	private Connection conn;
	
	
	public DBConn(String address, String port, String serviceName, String username, String password) throws Exception { //로컬용
		url_address = String.format("%s:%s/%s", address, port, serviceName);
		this.createConnection(username, password);
	}
	
	public DBConn(String tnsAlias, String walletPath, String username, String password) throws Exception { //클라우드용
		url_address = String.format("%sTNS_ADMIN=%s", tnsAlias, walletPath);
		this.createConnection(username, password);
	}
	
	public DBConn(File config) throws Exception {
			//oracle_db.conf !파일!로 DBMS연결하는법
			Map<String, String> map = new HashMap<String, String>();
			BufferedReader br = new BufferedReader(new FileReader(config));
			
			while(br.ready()) {
				String[] kv = br.readLine().split("=");
				map.put(kv[0].strip(), kv[1].strip());
			}
			
			url_address = String.format("%s:%s/%s", map.get("host"), map.get("port"), map.get("service"));
			this.createConnection(map.get("username"), map.get("password"));
			
			
//			String[] lines = sb.toString().split("\r\n"); //라인기준으로 분리
			
//			System.out.println(lines[0]);
//			System.out.println(lines[1]);
//			System.out.println(lines[2]);
//			System.out.println(lines[3]);
//			System.out.println(lines[4]);
//			
//			for(int i=0; i<5; i++) {
//				map.put(lines[i].split("=")[0],lines[i].split("=")[1]);
//			}
//			for(String key : map.keySet()){ 
//				String value = (String) map.get(key); 
//				System.out.println(key + " : " + value); 
//				}
			
		
	}
	
	public static void main(String[] args) throws Exception {
		DBConn db = new DBConn(new File(System.getProperty("user.home") + "/oracle_db.conf"));
	}

	
	private void createConnection(String username, String password) throws Exception {
		// 1. Driver 등록
		Class.forName(DRIVER_NAME);
		
		// 2. DBMS 연결
		conn = DriverManager.getConnection(
				BASE_URL + url_address, username, password
				);
		
		// 3. Statement 생성
		stat = conn.createStatement();
	}
	
	public ResultSet sendSelectQuery(String query) throws Exception {
		return this.stat.executeQuery(query);
	}
	
	public int sendInsertQuery(String query) throws Exception {
		return this.stat.executeUpdate(query);
	}
	
	public int sendUpdateQuery(String query) throws Exception {
		return this.stat.executeUpdate(query);
	}
	
	public int sendDeleteQuery(String query) throws Exception {
		return this.stat.executeUpdate(query);
	}
	
	public void close() throws Exception {
		this.stat.close(); //열은 순서와 반대로 닫아주어야 한다. (데칼코마니처럼)
		this.conn.close();
	}
	
}