package com.lisz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;

import com.lisz.controller.ResponseStatus;
import com.lisz.dao.AccountDao;
import com.lisz.entity.Account;

@Service
public class AcountService {

	@Autowired
	private AccountDao accountDao;
	
	public List<Account> findAll() {
		//List<Account> list = accountDao.findAll();
		List<Account> list = accountDao.findByIdBetween(2, 12);
		return list;
	}

	public Account findById(Integer id) {
		Optional<Account> optional = accountDao.findById(id); //也可以用findById，只不过会返回一个Optional<Account>, 但是@ResponseBody返回前端后，这个类型也可以被显示
		return optional.get();								//用JpaRepository的getOne方法会报错
	}
	
	public Account findByUsernameAndPassword(String username, String password) {
		return accountDao.findByUsernameAndPassword(username, password);
	}
	
	public List<Account> findByHQL() {
		return accountDao.findByHQL();
	}
	
	public List<Account> findByHQL2(int id) {
		return accountDao.findByHQL2(2);
	}

	public ResponseStatus save(Account account) {
		try {
			Account savedAccount = accountDao.save(account);
		} catch (Exception e) {
			return new ResponseStatus(500, "发生错误", e.getMessage());
		}
		return ResponseStatus.build(200);//成功也会在html上显示ok
	}
	
}
