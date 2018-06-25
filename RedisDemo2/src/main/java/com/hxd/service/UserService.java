package com.hxd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hxd.dao.Hdao;
import com.hxd.dao.common.TestInterface;
import com.hxd.entry.User;

@Service("userService")
public class UserService {
	@Autowired
	private Hdao hdao;
	
	@Cacheable(value="common",key="1")
    public User  get(Integer id) {
		System.out.println("测试缓存=================");
        return  hdao.getbyid(id) ;
    }


	//@CachePut(value="common",key="1")
/*	@TestInterface(expire=120)
	public List<Map<String, Object>> save(User user) {
		//hdao.save(user);
		System.out.println("----数据库取值中----");
		List<Map<String, Object>>  list =new ArrayList<Map<String,Object>>(); 
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("1", user);
		map.put("2", user);
		list.add(map);
		return list;
	}*/
	
	@TestInterface(expire=120,entryFlag=true)
	public User save(User user) {
		//hdao.save(user);
		System.out.println("----数据库取值中----");
		return user;
	}
	
}
