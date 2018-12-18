package com.hxd.redis;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
@Component
public class RedisUtils {
	
	private static Logger logger=Logger.getLogger(RedisUtils.class);
	
	/*@Autowired 
	private  RedisTemplate redisTemplate;*/
	 @Resource(name = "redisTemplate")
	 private ValueOperations<String, Object> vOps;
	 @Resource(name = "redisTemplate")
	 private ListOperations<String, Object> listOps;
	 @Resource(name = "redisTemplate")
	 private SetOperations<String, Object> setOps;
	 @Resource(name = "redisTemplate")
	 private ZSetOperations<String, Object> zSetOps;
	 @Resource(name = "redisTemplate")
	 private HashOperations<String,String, Object> hashOps;
	 

	/**
	 * 根据key获取缓存中的值
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		 Object value=vOps.get(key);
		 return value;
	}
	/**
	 * 向redis中保存
	 * @param key
	 * @param value
	 * @return 
	 */
	public void set(String key,Object value) {
		vOps.set(key, value);
	}
	/**
	 * 向redis中保存 并设置失效时间
	 * @param key
	 * @param value
	 * @param timeout 失效时间
	 * @param unit 失效时间的单位
	 */
	public void set(String key,Object value,long timeout,TimeUnit unit) {
		vOps.set(key, value, timeout, unit);;
	}
	
	
	
}

