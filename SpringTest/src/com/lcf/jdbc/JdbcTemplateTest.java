package com.lcf.jdbc;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

public class JdbcTemplateTest {
	@Test
	public void Test()
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdT = (JdbcTemplate) context.getBean("jdbcTemplate");
		jdT.execute(
				"create table account("+
				"id int primary key auto_increment,"+
				"username varchar(50),"+
				"balance double)"
		);
		System.out.println("账户表Account创建成功！");
	}
	@Test
	public void addUserTest() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		AccountDao ac = (AccountDaoImpl) context.getBean("accountDao");
		Account account1=new Account();
		account1.setId(5);
		account1.setUsername("Joy");
		account1.setBalance(1000.00);
		Account account2=new Account();
		account2.setId(6);
		account2.setUsername("Tom");
		account2.setBalance(1000.00);
		Account account3=new Account();
		account3.setId(7);
		account3.setUsername("Jack");
		account3.setBalance(1000.00);
		Account account4=new Account();
		account4.setId(8);
		account4.setUsername("Rose");
		account4.setBalance(1000.00);
		ac.addAccount(account1);
		ac.addAccount(account2);
		ac.addAccount(account4);
		int num=ac.addAccount(account3);
		if(num>0) {
			System.out.println("成功添加了"+num+"个用户");
		}else {
			System.out.println("添加用户失败！");
		}
	}
	@Test
	public void updateUserTest() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		AccountDao ac = (AccountDaoImpl) context.getBean("accountDao");
		Account account=new Account();
		account.setId(3);
		account.setUsername("chu");
		account.setBalance(10.00);
		int num=ac.updateAccount(account);
		if(num>0) {
			System.out.println("成功修改了"+num+"个用户");
		}else {
			System.out.println("修改用户失败！");
		}
	}
	@Test
	public void deleteUserTest() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		AccountDao ac = (AccountDaoImpl) context.getBean("accountDao");
		Account account=new Account();
		int num=ac.deleteAccount(3);
		if(num>0) {
			System.out.println("成功删除了"+num+"个用户");
		}else {
			System.out.println("删除用户失败！");
		}
	}
	@Test
	public void findAccountByIdTest() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		AccountDao ac = (AccountDaoImpl) context.getBean("accountDao");
		Account account=ac.findAccountById(3);
		System.out.println(account);
	}
	@Test
	public void findAllAccountTest() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		AccountDao ac = (AccountDaoImpl) context.getBean("accountDao");
		List<Account> list=ac.findAllAccount();
		for(Account a:list) {
			System.out.println(a);
		}
	}
	@Test
	//转账业务
	public void tranferTest() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		AccountDao accountDao=(AccountDao)context.getBean("accountDao");
		accountDao.transfer("Jack", "Rose", 100.00);
		System.out.println("转账成功！");
	}
	@Transactional
	@Test
	public void tranferTestById() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		AccountDao accountDao=(AccountDao)context.getBean("accountDao");
		accountDao.transferById(5, 6, 100.00);
		System.out.println("转账成功！");
	}
}
