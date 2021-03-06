package com.menu.controller;

import com.menu.dao.MenuDAO;
import com.menu.vo.MenuVO;

public class MenuController {

	private MenuDAO dao = new MenuDAO();

	public boolean join(MenuVO data) {
		MenuVO account = dao.get(data.getMenuname());

		if(account == null) {
			boolean result = dao.add(data);
			if(result) {
				return true; //TRUE여야 메뉴가 성공적으로 생성!
			}
		}
		return false;
	}

	public boolean update(MenuVO data) {
		return dao.modify(data);
	}

	public boolean remove(String input) {
		if(dao.get(input) != null) { //삭제할 메뉴가 db에 있는지 확인하는 작업
			return dao.remove(input);
		} else {
			return false;
		}
	}
}