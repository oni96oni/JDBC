package com.join.dao;

import java.io.File;
import java.sql.ResultSet;

import com.conn.db.DBConn;
import com.join.vo.JoinVO;

public class JoinDAO {
	//DAO : Database Access Object
	
	private DBConn db;
	
	public JoinDAO() {
		try {
			db = new DBConn(new File(System.getProperty("user.home") + "/oracle_db.conf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean add(JoinVO data) {
		//데이터 추가
		String query = String.format("INSERT INTO accounts VALUES('%s', '%s', '%s', '%c', %d, SYSDATE)"
				, data.getUserid()
				, data.getUserpw()
				, data.getUsername()
				, data.getGender()
				, data.getAge()
				);
		
		try {
			int result = db.sendInsertQuery(query);
			
			if(result == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public boolean modify(JoinVO data) {
		//데이터 수정
		String query = "UPDATE accounts "
				+ "SET USERPW = '" + data.getUserpw() + "'"
				+ " , USERNAME= '" + data.getUsername() + "'"
				+ " , GENDER = '" + data.getGender() + "'"
				+ " , AGE = " + data.getAge()
				+ "  WHERE USERID = '" +data.getUserid() + "'";
		
		try {
			int rs = db.sendUpdateQuery(query);
			
			if(rs == 1) { //행의 변화가 1이냐 -> 1개 업데이트하니까 
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean remove(JoinVO data) {
		//데이터 삭제
		String query = "DELETE accounts "
				+ "WHERE USERID = '" +data.getUserid() + "'";
		try {
			int rs = db.sendDeleteQuery(query);
			
			if(rs == 1) { //행의 변화가 1이냐 -> 1개 업데이트하니까  
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public JoinVO get(String userid) {
		//단일 데이터 조회
		
		String query = String.format("SELECT * FROM accounts WHERE USERID = '%s'", userid);
		
		try {
			ResultSet rs = db.sendSelectQuery(query);
			
			if(rs.next()) {
				JoinVO data = new JoinVO();
				data.setUserid(rs.getString("USERID"));
				data.setUserpw(rs.getString("USERPW"));
				data.setUsername(rs.getString("USERNAME"));
				data.setGender(rs.getString("GENDER").charAt(0));
				data.setAge(rs.getInt("AGE"));
				data.setCreateDate(rs.getDate("CREATEDATE"));
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
