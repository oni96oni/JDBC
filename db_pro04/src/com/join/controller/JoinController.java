package com.join.controller;

import com.join.dao.JoinDAO;
import com.join.vo.JoinVO;

public class JoinController {
	
	private JoinDAO dao = new JoinDAO();
	
	public boolean join(JoinVO data) {
		//회원가입 처리 전 필요한 로직(데이터 검사, 계산 등)
		//회원가입 처리 후 결과를 반환
		
		//가입을 진행하기 전에 아이디 중복 확인!, 받은 데이터 객체에 userid가 있는지 확인후 없으면 추가, 있으면 중복이니까 false를 반환한다.
		JoinVO account = dao.get(data.getUserid());
		
		if(account == null) {
			boolean result = dao.add(data);
			if(result) {
				return true; //TRUE여야 아이디가 성공적으로 생성!
			}
		}
		return false;
		
	}

	public JoinVO login(String userid, String userpw) {
		JoinVO account = dao.get(userid);
		
		if(account != null) {
			
			if(account.getUserpw().equals(userpw)) {
				return account;
			}
			
		}
		return null;
		/*
		 * userid에 해당하는 계정이 있는지 확인
		 * 해당 계정이 userpw의 값과 동일한 패스워드를 가지고 있는지 확인후 동일한 정보면 사용자 정보 객체(JoinVO)전달 아니면 null 전달.
		 * 아이디로 JoinVO 객체에 대한 정보를 가져온다. 가져온 객체가 null값을 갖고있으면 null을 반환 -> 로그인 실패
		 * null이 아니면 로그인시 입력받은 비밀번호와 객체정보에 있는 비밀번호 값을 비교하여 같으면 아이디를 통해 가져온 객체를 반환한다. 
		*/ 
	}

}
