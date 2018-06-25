package com.hxd.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hxd.entry.User;

@Repository
public class Hdao {
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public User getbyid(Integer id){
		User user=(User) sessionFactory.openSession().get(User.class, id);
		return user;
	}
	
	public void save(User user){
 sessionFactory.openSession().save(user);
	}

	
}
