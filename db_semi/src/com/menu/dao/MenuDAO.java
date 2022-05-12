package com.menu.dao;

import java.io.File;
import java.sql.ResultSet;
import com.menu.db.DBConn;
import com.menu.vo.MenuVO;


public class MenuDAO {

	private DBConn db;


	public MenuDAO() {
		try {
			db = new DBConn(new File(System.getProperty("user.home") + "/oracle_db.conf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MenuVO[] get() {
		String query = String.format("SELECT Menuname FROM Menu");

		try {
			//메뉴이름을 배열로 저장해서 반환하고, 반환한것은 random클래스 이용해서 nextint를 배열길이로
			ResultSet rs = db.sendSelectQuery(query);

			if(rs.next()) {
				MenuVO data[] = data[].setMenuname(rs.getString("Menuname"));
				//				data.setPrice(rs.getString("Price"));
				//				data.setTradename(rs.getString("Tradename"));
				//				data.setLocation(rs.getString("Location"));
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public MenuVO get(String menuname) {
		//단일 데이터 조회 메뉴가 기존에 존재하냐?
		String query = String.format("SELECT * FROM Menu WHERE Menuname = '%s'", menuname);

		try {
			ResultSet rs = db.sendSelectQuery(query);

			if(rs.next()) {
				MenuVO data = new MenuVO();
				data.setMenuname(rs.getString("Menuname"));
				data.setPrice(rs.getString("Price"));
				data.setTradename(rs.getString("Tradename"));
				data.setLocation(rs.getString("Location"));
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean add(MenuVO data) {
		//메뉴 추가
		String query = String.format("INSERT INTO Menu VALUES('%s', '%d', '%s', '%s')"
				, data.getMenuname()
				, data.getPrice()
				, data.getTradename()
				, data.getLocation()
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

	public boolean modify(MenuVO data) {
		//메뉴 업데이트
		String query = "UPDATE Menu "
				+ "SET Price= " + data.getPrice() 
				+ " , Tradename = '" + data.getTradename() + "'"
				+ " , Location = '" + data.getLocation() + "'"
				+ "  WHERE Menuname = '" +data.getMenuname() + "'";

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

	public boolean remove(String input) {
		//메뉴 삭제
		String query = "DELETE menu "
				+ "WHERE Menuname = '" + input + "'";
		try {
			int rs = db.sendDeleteQuery(query);

			if(rs == 1) { //행의 변화가 1이냐 -> 1개 삭제되니까
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
