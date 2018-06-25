package com.hxd.redis;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
@Component
public class RedisUtils {
	
	private static Logger logger=Logger.getLogger(RedisUtils.class);
	
	/*@Autowired 
	private  RedisTemplate redisTemplate;*/
	 @Resource(name = "redisTemplate")
	 private ValueOperations<String, Object> vOps;

	/**
	 * 根据key获取缓存中的值
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		// ValueOperations<String, Object> valueOper=redisTemplate.opsForValue();
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
		//ValueOperations<String, Object> valueOper=redisTemplate.opsForValue();
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
		//ValueOperations<String, Object> valueOper=redisTemplate.opsForValue();
		vOps.set(key, value, timeout, unit);;
	}
	
	
	
}

