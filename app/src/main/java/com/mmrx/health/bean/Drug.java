package com.mmrx.health.bean;

import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;
@Table(name="fragment_drug")
public class Drug {
	@Id
	private int id;
	private String name;
	private String notice;
	private int baozhiqi;
	private boolean isEdible;
	
	
	public Drug(String name, String notice, int baozhiqi, boolean isEdible) {
		super();
		this.name = name;
		this.notice = notice;
		this.baozhiqi = baozhiqi;
		this.isEdible = isEdible;
	}
	public boolean isEdible() {
		return isEdible;
	}
	public void setEdible(boolean isEdible) {
		this.isEdible = isEdible;
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
	public int getBaozhiqi() {
		return baozhiqi;
	}
	public void setBaozhiqi(int baozhiqi) {
		this.baozhiqi = baozhiqi;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public Drug(int id, String name, String notice, int baozhiqi) {
		super();
		this.id = id;
		this.name = name;
		this.notice = notice;
		this.baozhiqi = baozhiqi;
		this.isEdible=false;
	}
	public Drug(String name, String notice, int baozhiqi) {
		super();
		this.name = name;
		this.notice = notice;
		this.baozhiqi = baozhiqi;
		this.isEdible=false;
	}
	public Drug() {
		super();
	}
	
	
	
	

	
}
