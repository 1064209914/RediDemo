package com.hxd.entry;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name ="user")
public class User implements Serializable{

	    @Id
	    @GeneratedValue
	    private Integer id;
	    @Column
	private String useName;
	    @Column
	    private String password;
	public String getUseName() {
		return useName;
	}
	public void setUseName(String useName) {
		this.useName = useName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", useName=" + useName + ", password=" + password + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
