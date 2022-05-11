package com.join.dao;

import java.sql.ResultSet;

import com.conn.db.DBConn;
import com.join.vo.JoinVO;

public class JoinDAO {
	//DAO : Database Access Object
	
	private DBConn db;
	
	public JoinDAO() {
		try {
			db = new DBConn("localhost", "1521", "XE", "puser1", "puser1");
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
	
	public void modify() {
		//데이터 수정
	}
	
	public void remove() {
		//데이터 삭제
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
