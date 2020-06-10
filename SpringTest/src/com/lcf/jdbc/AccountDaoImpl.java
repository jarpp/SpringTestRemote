package com.lcf.jdbc;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

public class AccountDaoImpl implements AccountDao {
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int addAccount(Account account) {
		// TODO Auto-generated method stub
		String sql="insert into account(id,username,balance) value(?,?,?)";
		Object[] obj=new Object[] {
				account.getId(),
				account.getUsername(),
				account.getBalance()
		};
		int num=this.jdbcTemplate.update(sql, obj);
		return num;
	}

	@Override
	public int updateAccount(Account account) {
		// TODO Auto-generated method stub
		String sql="update account set username=?,balance=? where id=?";
		Object[] obj=new Object[] {
			account.getUsername(),
			account.getBalance(),
			account.getId()
		};
		int num=this.jdbcTemplate.update(sql,obj);
		return num;
	}

	@Override
	public int deleteAccount(int id) {
		// TODO Auto-generated method stub
		String sql="delete from account where id=?";
		int num=this.jdbcTemplate.update(sql,id);
		return num;
	}

	@Override
	public Account findAccountById(int id) {
		// TODO Auto-generated method stub
		String sql = "select*from account where id=?";
		RowMapper<Account> rowMapper = new BeanPropertyRowMapper<Account>(Account.class);
		return this.jdbcTemplate.queryForObject(sql, rowMapper,id);
	}

	@Override
	public List<Account> findAllAccount() {
		// TODO Auto-generated method stub
		String sql="select*from account";
		RowMapper<Account> rowMapper=new BeanPropertyRowMapper<Account>(Account.class);
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public void transfer(String outUser, String inUser, Double money) {
		// TODO Auto-generated method stub
		//
		String sql1="update account set balance=balance+? where username=?";
		String sql2="update account set balance=balance-? where username=?"; 
		this.jdbcTemplate.update(sql1, money, inUser);
		//模拟突发性问题
//		int i=1/0;
		//模拟汇款人
		this.jdbcTemplate.update(sql2, money, outUser);
	}
	
	@Override
	public void transferById(int idOut, int idIn, Double money) {
		// TODO Auto-generated method stub
		String sql1 = "update account set balance=balance+? where id=?";
		String sql2 = "update account set balance=balance-? where id=?";
		this.jdbcTemplate.update(sql2, money, idOut);
//		int i=1/0;
		this.jdbcTemplate.update(sql1, money, idIn);
	}
	
	
}
