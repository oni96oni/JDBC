package com.join.view;

import java.util.Scanner;

import com.join.controller.JoinController;
import com.join.menu.JoinMenu;
import com.join.vo.JoinVO;

/*
	CLI 화면에 회원가입, 탈퇴, 정보수정 등과 같은 메뉴를 보여주고 사용자가 데이터를 입력할 수 있는 화면을 제공하는 객체
 */
public class JoinView {

	private Scanner sc = new Scanner(System.in);
	private JoinMenu menu = new JoinMenu();
	private JoinController jc = new JoinController();
	
	
	public void show() {
		
		while(true) {
			
			System.out.println(menu.getMain());
			System.out.print(">>> ");
			String userInput = sc.nextLine();

			switch(userInput) {
			case "1": 
				this.joinMenu(); 	break;
			case "2": 
				this.loginMenu(); 	break;
			case "3": 
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default: System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
			}
		}
	}

	public void joinMenu() {
		//회원 가입을 처리하기 위한 메서드, 객체를 이용하여 정보를 저장하고 꺼내오고 할것이니까 객체 생성후 입력정보를 넣어준다.
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
			 this.afterLoginMenu(account);
		} else {
			System.out.println("잘못된 아이디 또는 비밀번호 입니다.");
		}
	}

	public void afterLoginMenu(JoinVO account) {
		while(true) {
			System.out.println(menu.getAfterLogin(account.getUserid()));
			System.out.println(">>> ");
			String input = sc.nextLine();
			
			switch(input) {
			case "1": 
				System.out.println("아무것도 입력을 하지 않으면 이전 값을 유지 합니다.");
				System.out.println("변경할 패스워드 : ");
				input = sc.nextLine();
				input = input.isEmpty() ? account.getUserpw() : input ;
				account.setUserpw(input);
				
				System.out.println("변경할 이름 : ");
				input = sc.nextLine();
				input = input.isEmpty() ? account.getUsername() : input ;
				account.setUsername(input);
				
				System.out.println("변경할 성별 : ");
				input = sc.nextLine();
				input = input.isEmpty() ? Character.toString(account.getGender()) : input ;
				account.setGender(input.charAt(0));
				
				System.out.println("변경할 나이 : ");
				input = sc.nextLine();
				input = input.isEmpty() ? Integer.toString(account.getAge()) : input ;
				account.setAge(input);
				
				if(jc.update(account)) {
					System.out.println("정보 수정이 완료되었습니다.");
				} else {
					System.out.println("정보 수정이 실패하였습니다.");
				}
				
				break;
			case "2": 
				System.out.println("패스워드 : ");
				input = sc.nextLine();
				if(jc.remove(account,input)) {
					System.out.println("계정 삭제 작업이 완료되었습니다.");
					return;
				} else {
					System.out.println("계정 삭제 작업에 실패하였습니다.");
				}
				break;
			case "3": 
				account = null;
				System.out.println("로그아웃합니다.");
				return;
			default: System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
			}
		}
	}
}
