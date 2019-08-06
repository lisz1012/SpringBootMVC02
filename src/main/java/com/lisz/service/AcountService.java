package com.lisz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisz.controller.ResponseStatus;
import com.lisz.dao.AccountDao;
import com.lisz.entity.Account;

@Service
public class AcountService {

	@Autowired
	private AccountDao accountDao;
	
	public List<Account> findAll() {
		List<Account> list = accountDao.findAll();
		return list;
	}

	public Account findById(Integer id) {
		return accountDao.getOne(id);
	}

	public ResponseStatus save(Account account) {
		try {
			Account savedAccount = accountDao.save(account);
		} catch (Exception e) {
			return new ResponseStatus(500, "发生错误", e.getMessage());
		}
		return ResponseStatus.build(200);
	}
	
}
