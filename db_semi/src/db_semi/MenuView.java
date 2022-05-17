package db_semi;

import java.util.Random;
import java.util.Scanner;
import com.menu.MenuCollection;
import com.menu.controller.MenuController;
import com.menu.dao.MenuDAO;
import com.menu.vo.MenuVO;

public class MenuView {
	private Scanner sc = new Scanner(System.in);
	private MenuCollection menu = new MenuCollection();
	private MenuController mc = new MenuController();
	private MenuDAO dao = new MenuDAO();
	private Random rand = new Random();


	public void show() {
		while(true) {

			System.out.println(menu.getMain());
			System.out.print(">>> ");
			String userInput = sc.nextLine();

			switch(userInput) {
			case "1": 
				this.showMenuList();	break;
			case "2": 
				this.MenuInsert();		break;
			case "3": 
				this.RandomMenuChoice(); 	break;
			case "4": 
				this.MenuUpdate(); 		break;
			case "5": 
				this.MenuDelete(); 		break;
			case "6": 
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default: System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
			}
		}
	}


	public void showMenuList() {
		System.out.println("저장되어 있는 메뉴,가격,판매점,위치를 출력합니다.");
		dao.getMenuList();
	}


	public void MenuDelete() {
		System.out.println("메뉴이름 : ");
		String input = sc.nextLine();
		if(mc.remove(input)) {
			System.out.println("메뉴 삭제 작업이 완료되었습니다.");
			return;
		} else {
			System.out.println("메뉴 삭제 작업에 실패하였습니다.");
		}
	}


	public void MenuUpdate() {
		//수정할 메뉴이름을 고른뒤에 수정하게 해야함. 
		MenuVO Menu = new MenuVO();
		
		System.out.println("정보를 변경할 메뉴이름을 입력하세요 : ");
		String input = sc.nextLine();
		input = input.isEmpty() ? Menu.getMenuname() : input ;
		Menu.setMenuname(input);
		
		System.out.println("아무것도 입력을 하지 않으면 이전 값을 유지 합니다.");
		System.out.println("변경할 가격 : ");
		input = sc.nextLine();
		input = input.isEmpty() ? Integer.toString(Menu.getPrice()) : input ;
		Menu.setPrice(input);

		System.out.println("변경할 상호명 : ");
		input = sc.nextLine();
		input = input.isEmpty() ? Menu.getTradename() : input ;
		Menu.setTradename(input);

		System.out.println("변경할 위치 : ");
		input = sc.nextLine();
		input = input.isEmpty() ? Menu.getLocation() : input ;
		Menu.setLocation(input);

		if(mc.update(Menu)) {
			System.out.println("메뉴 수정이 완료되었습니다.");
		} else {
			System.out.println("메뉴 수정이 실패하였습니다.");
		}
	}


	public void RandomMenuChoice() {
		/*
		 * Menu 테이블에 아무것도 없으면 메뉴 추가하라고 하기 -> 메뉴 인서트 메서드 켜주기
		 * 그 외의 경우에는 메뉴 랜덤으로 출력 -> 하나만 출력
		 */
		
		MenuVO data = dao.randomGetMenu();

		if(data == null) { //null이면 테이블에 아무런 값도 없다는 뜻!
			System.out.println("테이블에 아무런 메뉴가 없습니다. 메뉴추가를 시작합니다.");
			this.MenuInsert();
		} else {
			System.out.println(data.getMenuname()+" 가 나왔습니다.");
		}
	}


	public void MenuInsert() {
		//메뉴 추가를 처리하기 위한 메서드, 객체를 이용하여 정보를 저장하고 꺼내오고 할것이니까 객체 생성후 입력정보를 넣어준다.
		MenuVO data = new MenuVO();

		System.out.print("menuname 입력 :");
		data.setMenuname(sc.nextLine());

		System.out.print("price 입력 : ");
		data.setPrice(sc.nextLine());

		System.out.print("tradename 입력 :");
		data.setTradename(sc.nextLine());

		System.out.print("location 입력 :");
		data.setLocation(sc.nextLine());


		boolean result = mc.join(data);

		if(result) {
			System.out.println("메뉴 추가가 완료되었습니다");
		} else {
			System.out.println("메뉴 추가가 실패하였습니다. (메뉴 중복)");
		} //메뉴 중복이 유일한 오류가 될수 밖에 없다. 나머지 입력사항들은 중복 가능

	}
}
