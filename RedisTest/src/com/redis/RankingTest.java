package com.redis;

import java.util.ArrayList;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Set;

import org.junit.After;
import org.junit.Before;  
import org.junit.Ignore;  
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;  
 
public class RankingTest {  
   
	private JedisPool jedisPool;
	private Jedis jedis;
	
    public RankingTest() {  
  
    }  
  
    @Before  
    public void init() throws Exception {  
  
    	//获取连接池对象
		JedisPoolConfig config=new JedisPoolConfig();
		//设置最大链接数
		config.setMaxTotal(30);
		//设置最大空闲数
		config.setMaxIdle(10);
		
		//获取连接池
		JedisPool jedisPool=new JedisPool(config,"127.0.0.1",6379);
		//获得核心对象
		Jedis jedis=jedisPool.getResource(); 
    }  
  /*    @After
    public void destoy(){
    	if (jedis!=null) {
			jedis.close();
		}
		if (jedisPool!=null) {
			jedisPool.close();
		}
		System.out.println("redis关闭了！！");
    }
    */
    
    @Test   
    public void rankAdd() {  
    	
    	//获取连接池对象
    			JedisPoolConfig config=new JedisPoolConfig();
    			//设置最大链接数
    			config.setMaxTotal(30);
    			//设置最大空闲数
    			config.setMaxIdle(10);
    			
    			//获取连接池
    			JedisPool jedisPool=new JedisPool(config,"127.0.0.1",6379);
    			//获得核心对象
    			Jedis jedis=jedisPool.getResource(); 
    	
        User user1 = new User("12345", "常少鹏", 99.9);  
        User user2 = new User("12346", "王卓卓", 99.8);  
        User user3 = new User("12347", "邹雨欣", 96.8);  
        User user4 = new User("12348", "郑伟山", 98.8);  
        User user5 = new User("12349", "李超杰", 99.6);  
        User user6 = new User("12350", "董明明", 99.0);  
        User user7 = new User("12351", "陈国峰", 100.0);  
        User user8 = new User("12352", "楚晓丽", 99.6);  
        jedis.zadd("game".getBytes(), user1.getScore(), ObjectSer.ObjectToByte(user1));  
        jedis.zadd("game".getBytes(), user2.getScore(), ObjectSer.ObjectToByte(user2));  
        jedis.zadd("game".getBytes(), user3.getScore(), ObjectSer.ObjectToByte(user3));  
        jedis.zadd("game".getBytes(), user4.getScore(), ObjectSer.ObjectToByte(user4));  
        jedis.zadd("game".getBytes(), user5.getScore(), ObjectSer.ObjectToByte(user5));  
        jedis.zadd("game".getBytes(), user6.getScore(), ObjectSer.ObjectToByte(user6));  
        jedis.zadd("game".getBytes(), user7.getScore(), ObjectSer.ObjectToByte(user7));  
        jedis.zadd("game".getBytes(), user8.getScore(), ObjectSer.ObjectToByte(user8));  
        System.out.println("game".getBytes());
    }  
          
    @Test  
    public void gameRankShow() { 

    	//获取连接池对象
		JedisPoolConfig config=new JedisPoolConfig();
		//设置最大链接数
		config.setMaxTotal(30);
		//设置最大空闲数
		config.setMaxIdle(10);
		
		//获取连接池
		JedisPool jedisPool=new JedisPool(config,"127.0.0.1",6379);
		//获得核心对象
		Jedis jedis=jedisPool.getResource();
    	System.out.println(jedis);
        Set<byte[]> set = jedis.zrevrange("game".getBytes(), 0, -1);  
        Iterator<byte[]> iter = set.iterator();  
      
        int i = 1;  
        List<User> list = new ArrayList<User>();  
        while(iter.hasNext()) {  
            User user = (User) ObjectSer.ByteToObject(iter.next());  
            user.setRank(i++);  
            list.add(user);  
        }  
          
        for(User user : list)   
            System.out.println(user);  
    }  
      
}  


