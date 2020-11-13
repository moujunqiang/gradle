package com.mmrx.health.bean;

public class User {
	private String name;            //用户名
	private String password;        //密码
	private int age;
	private String gender;
	private int tall;
	private int weight;
	private int targetweight;
	private String isLogin;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getTall() {
		return tall;
	}
	public void setTall(int tall) {
		this.tall = tall;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getTargetweight() {
		return targetweight;
	}

	public void setTargetweight(int targetweight) {
		this.targetweight = targetweight;
	}

	public String getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public User(String name, String password, String isLogin) {
		this.name = name;
		this.password = password;
		this.isLogin = isLogin;
	}

	public User(String name, int age, String gender, int tall, int weight) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.tall = tall;
		this.weight = weight;
	}
	public User() {
		super();
	}

	
	
}
