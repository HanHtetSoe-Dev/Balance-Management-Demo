package com.example.demo.vo;

import com.example.demo.entity.User;

public class UserVo {

	private int id;
	private String name;
	private String loginId;
	private boolean status;
	private String phone;
	private String email;

	public UserVo() {

	}

	public UserVo(User entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.loginId = entity.getLoginId();
		this.status = entity.isActive();
		this.phone = entity.getPhone();
		this.email = entity.getEmail();
		
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
