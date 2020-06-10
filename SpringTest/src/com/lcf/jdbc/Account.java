package com.lcf.jdbc;

public class Account {
	private Integer id;
	private String username;
	private Double balance;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String toString () {
		return "id="+id+",username="+username+",balance="+balance;
	}
}
