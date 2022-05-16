package com.menu.dao;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Random;
import com.menu.db.DBConn;
import com.menu.vo.MenuVO;


public class MenuDAO {

	private DBConn db;
	private Random rand = new Random();

	
	public MenuDAO() {
		try {
			db = new DBConn(new File(System.getProperty("user.home") + "/oracle_db.conf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
/*	public MenuVO[] getMenu() {
		String query = "SELECT * FROM Menu";
		
		try {
			PreparedStatement pstat = db.getPstat(query);
			ResultSet rs = db.sendSelectQuery();
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
		
	}*/
	
	public MenuVO randomGetMenu() {
		String query = "SELECT * FROM Menu";

		try {
			PreparedStatement pstat = db.getPstat(query);
			ResultSet rs = db.sendSelectQuery();
			int i = 0;
			MenuVO[] data = new MenuVO[i];
			while(rs.next()) {
				//next할때마다 동적배열로 늘려주기 or 애초에 카운트해서 길이잡기.
				MenuVO[] temp2 = new MenuVO[i+1];
				MenuVO temp = new MenuVO();
				
				temp.setMenuname(rs.getString("Menuname"));
				temp.setPrice(rs.getString("Price"));
				temp.setTradename(rs.getString("Tradename"));
				temp.setLocation(rs.getString("Location"));
				
				data = Arrays.copyOf(temp2, temp2.length + 1);
				data[i] = temp;
				i++;
			}
			return data[rand.nextInt(i)];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public MenuVO get(String menuname) {
		//단일 데이터 조회 메뉴가 기존에 존재하냐?
		String query = "SELECT * FROM Menu WHERE Menuname = ?";

		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, menuname);
			ResultSet rs = db.sendSelectQuery();
			
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
		String query ="INSERT INTO Menu VALUES(?, ?, ?, ?)";

		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, data.getMenuname());
			pstat.setInt(2, data.getPrice());
			pstat.setString(3, data.getTradename());
			pstat.setString(4, data.getLocation());
			
			int result = db.sendInsertQuery();

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
				+ "SET Price = ?"
				+ " , Tradename = ?"
				+ " , Location = ?"
				+ "  WHERE Menuname = ?";

		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setInt(1, data.getPrice());
			pstat.setString(2, data.getTradename());
			pstat.setString(3, data.getLocation());
			pstat.setString(4, data.getMenuname());
			
			int rs = db.sendUpdateQuery();

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
				+ "WHERE Menuname = ? ";
		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, input);
			int rs = db.sendDeleteQuery();

			if(rs == 1) { //행의 변화가 1이냐 -> 1개 삭제되니까
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void getMenuList() {
		String query = "SELECT * FROM Menu";

		try {
			PreparedStatement pstat = db.getPstat(query);
			ResultSet rs = db.sendSelectQuery();
			while(rs.next()) {
				System.out.print(pstat.getResultSet().getString("Menuname")); System.out.print("\t");
				System.out.print(pstat.getResultSet().getString("Price")); System.out.print("\t");
				System.out.print(pstat.getResultSet().getString("Tradename")); System.out.print("\t");
				System.out.println(pstat.getResultSet().getString("Location")); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
