package com.lcf.jdbc;

import java.util.List;

public interface AccountDao {
	public int addAccount(Account account);
	public int updateAccount(Account account);
	public int deleteAccount(int id);
	public Account findAccountById(int id);
	public List<Account> findAllAccount();
	public void transfer(String outUser,String inUser,Double money);
	public void transferById(int idOut,int idIn,Double money);
}
