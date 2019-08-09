package com.lisz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lisz.entity.Account;
//这里就不用写@Repository注解了，写了也不错     指定装配类型和主键类型
public interface AccountDao extends JpaRepository<Account, Integer> { //用Spring Data JPA的话，这里得是接口 QuerydslJpaRepository, SimpleJpaRepository实现了这个接口

	List<Account> findByIdBetween(int min, int max); //这样自定义了一个方法之后就可以拿到一定范围内的ID的Account，包含了min和max

	Account findByUsernameAndPassword(String username, String password); // 后台居然会发sql：Hibernate: select account0_.id as id1_0_, account0_.age as age2_0_, account0_.location as location3_0_, account0_.nick_name as nick_nam4_0_, account0_.password as password5_0_, account0_.username as username6_0_ from account account0_ where account0_.username=? and account0_.password=?
}       //改成findByLoginnameAndPassword后会报错：No property loginname found for type Account! 所以名字定义要复合findBy...的规范
