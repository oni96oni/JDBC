package com.menu.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	private PreparedStatement pstat;

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


	}

	public void createConnection(String username, String password) throws Exception {
		// 1. Driver 등록
		Class.forName(DRIVER_NAME);

		// 2. DBMS 연결
		conn = DriverManager.getConnection(
				BASE_URL + url_address, username, password
				);

		// 3. Statement 생성
//		stat = conn.createStatement();
	}
	
	public PreparedStatement getPstat(String query) throws Exception {
		pstat = conn.prepareStatement(query);
		return pstat;
	}
	
	public ResultSet sendSelectQuery() throws Exception {
		return this.pstat.executeQuery();
	}
	
	public int sendInsertQuery() throws Exception {
		return this.pstat.executeUpdate();
	}
	
	public int sendUpdateQuery() throws Exception {
		return this.pstat.executeUpdate();
	}
	
	public int sendDeleteQuery() throws Exception {
		return this.pstat.executeUpdate();
	}
	
	public void close() throws Exception {
		this.stat.close(); //열은 순서와 반대로 닫아주어야 한다. (데칼코마니처럼)
		this.conn.close();
	}
}
