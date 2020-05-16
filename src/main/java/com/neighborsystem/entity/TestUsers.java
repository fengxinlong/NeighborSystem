package com.neighborsystem.entity;

import java.io.Serializable;

public class TestUsers implements Serializable {
	private String uname;
	private String uage;
	private String ubirthday;

	@Override
	public String toString() {
		return "TestUsers{" +
				"uname='" + uname + '\'' +
				", uage='" + uage + '\'' +
				", ubirthday='" + ubirthday + '\'' +
				'}';
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUage() {
		return uage;
	}

	public void setUage(String uage) {
		this.uage = uage;
	}

	public String getUbirthday() {
		return ubirthday;
	}

	public void setUbirthday(String ubirthday) {
		this.ubirthday = ubirthday;
	}
}
