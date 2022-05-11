package com.join.view;

import java.util.Scanner;
import com.join.controller.JoinController;
import com.join.vo.JoinVO;

/*
	CLI 화면에 회원가입, 탈퇴, 정보수정 등과 같은 메뉴를 보여주고 사용자가 데이터를 입력할 수 있는 화면을 제공하는 객체
 */
public class JoinView {

	private Scanner sc = new Scanner(System.in);
	JoinController jc = new JoinController();
	
	
	public void show() {
		System.out.println("  회원가입 프로그램  ");
		System.out.println("-----------------");
		System.out.println("  1. 회원 가입");
		System.out.println("  2. 로그인");
		System.out.println("  3. 프로그램 종료");
		System.out.println("-----------------");

		while(true) {
			System.out.print(">>> ");
			String userInput = sc.nextLine();

			switch(userInput) {
			case "1": 
				this.joinMenu(); 	break;
			case "2": 
				this.loginMenu(); 	break;
			case "3": 
				System.exit(0);
			default: System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
			}
		}
	}

	public void joinMenu() {
		//회원 가입을 처리하기 위한 메서드
		JoinVO data = new JoinVO();
		
		System.out.print("userid 입력 :");
		data.setUserid(sc.nextLine());
		
		System.out.print("userpw 입력 :");
		data.setUserpw(sc.nextLine());
		
		System.out.print("username 입력 :");
		data.setUsername(sc.nextLine());
		
		System.out.print("gender 입력 :");
		data.setGender(sc.nextLine());
		
		System.out.print("age 입력 :");
		data.setAge(sc.nextLine());
		
		boolean result = jc.join(data);
		
		if(result) {
			System.out.println("회원 가입이 완료되었습니다");
		} else {
			System.out.println("회원 가입이 실패하였습니다. (아이디 중복)");
		} //아이디 중복이 유일한 오류가 될수 밖에 없다. 나머지 입력사항들은 중복 가능
	}

	public void loginMenu() {
		//로그인을 처리하기 위한 메서드
		String userid, userpw;
		
		System.out.print("아이디 : ");
		userid = sc.nextLine();
		
		System.out.print("패스워드 : ");
		userpw = sc.nextLine();
		
		JoinVO account = jc.login(userid,userpw);
		
		if(account != null) {
			System.out.println(account.getUserid() + "님이 로그인 하였습니다.");
		} else {
			System.out.println("잘못된 아이디 또는 비밀번호 입니다.");
		}
	}
}
