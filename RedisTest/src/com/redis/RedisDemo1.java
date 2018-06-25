package com.redis;


import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisDemo1 {

	/**
	 * Title: test1
	 * Description: 单实例运用
	 * @author 胡小东
	 */
	@Test
	public void test1() {
		//1.链接IP 和端口
		Jedis jedis=new Jedis("127.0.0.1", 6379);
		//2.设值
		jedis.set("name", "张三");
		//3.取值并打印
		System.out.println(jedis.get("name"));
		//4.释放资源
		jedis.close();
	}
	/**
	 * 通过连接池链接	
	 */
	@Test
	public void test2(){
		//获取连接池对象
		JedisPoolConfig config=new JedisPoolConfig();
		//设置最大链接数
		config.setMaxTotal(30);
		//设置最大空闲数
		config.setMaxIdle(10);
		
		//获取连接池
		JedisPool jedisPool=new JedisPool(config,"127.0.0.1",6379);
		//获得核心对象
		Jedis jedis=null;
		try {
			//通过连接池获取核心对象
			jedis=jedisPool.getResource();
			jedis.set("name2", "张三2");
			System.out.println(jedis.get("name2"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (jedis!=null) {
				jedis.close();
			}
			if (jedisPool!=null) {
				jedisPool.close();
			}
		}
		
		
	}
	
}
