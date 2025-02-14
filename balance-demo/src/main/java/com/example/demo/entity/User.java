package com.example.demo.entity;

import java.io.Serializable;

import com.example.demo.form.SignUpForm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private Role role;
	@Column(nullable=false, unique=true)
	private String loginId;
	@Column(nullable=false)
	private String password;
	private String phone;
	private String email;
	private boolean active;
	
	public User() {
		
	}
	
	public User(SignUpForm signUp) {
		this.name = signUp.getName();
		this.loginId = signUp.getLoginId();
		this.password = signUp.getPassword();
		this.active = true;
		this.role = Role.Member;
	}
	
	public enum Role{
		Admin,
		Member
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		// TODO Auto-generated method stub
		return active;
	}

	

	

	
	
	
}
