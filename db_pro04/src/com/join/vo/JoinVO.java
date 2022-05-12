package com.join.vo;

import java.sql.Date;

/*
	회원 정보를 담아 두기 위한 객체로 활용
	이름,성별, 등등의 것을 메소드로 보내면 매개변수가 너무 길어지잖아!
	객체로 만들어서 관리하자.
	
	vo인 이유 Value Object , 즉 값만 있어서.
 */
public class JoinVO {
	
	private String userid;
	private String userpw;
	private String username;
	private char gender;
	private int age;
	private Date createDate;
	
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public void setGender(String gender) {
		if(gender.charAt(0) == '남' || gender.charAt(0) == 'M') {
			this.gender = 'M';
		} else if(gender.charAt(0) == '여' || gender.charAt(0) == 'F') {
			this.gender = 'F';
		} else {
			System.out.println("잘못된 입력입니다.");
		}
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void setAge(String age) {
		this.age = Integer.parseInt(age);
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
