package com.hxd.dao.common;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hxd.entry.User;
import com.hxd.redis.RedisUtils;


@Aspect
@Component
public class CacheableAop {
	
	@Autowired
	private  RedisUtils redisUtils;
	private Class returnType;
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
	

	//切入点
    @Pointcut(value = "execution(* com.hxd.service..*.*(..))")
    private void pointcut() {}
	
	
	//execution(* com.hxd.service..*.*(..)) &&&& @annotation(testInterface)
	@Around(value = "pointcut() && @annotation(com.hxd.dao.common.TestInterface) && @annotation(testInterface)")
	public Object cached(final ProceedingJoinPoint pjp,TestInterface testInterface) throws Throwable {
		//获取缓存的key
		String key=getCacheKey(pjp, testInterface);
	
		//从缓存获取数据
		Object value=redisUtils.get(key);
		//如果取到值 之后 直接返回
		if(value!=null) {
		//获取方法的返回类型,让缓存可以返回正确的类型
		returnType=((MethodSignature)pjp.getSignature()).getReturnType();
		return (testInterface.entryFlag() ? JSON.parseObject(value.toString(), returnType) :JSON.parse(value.toString()) );
		}
		//跳过缓存,到后端查询数据
		Object result = pjp.proceed();
		//判断从数据查询结果是否为空 不为空就缓存到redis中
		if (result!=null&&result!="") {
			Object setResult= JSON.toJSONString(result);
			//将数据库查询出的值插入redis中
			if(testInterface.expire()<=0) {	
				//如果没有设置过期时间,则无限期缓存
				redisUtils.set(key,setResult);
			} else {			
				//否则设置缓存时间
				redisUtils.set(key,setResult,testInterface.expire(),TimeUnit.SECONDS)  ;
			}
		}
		 return  result;
	}
	
	/**
	 * 获取缓存的key值
	 * @param pjp
	 * @param cache
	 * @return
	 */
	private String getCacheKey(ProceedingJoinPoint pjp,TestInterface cache) {
		
		 Object[] args=pjp.getArgs();
		 User user = null;
		 if (args != null && args.length > 0) {
			  user=(User) args[0];
		}
		 String key="";
		 if (user!=null) {
		key=user.getId().toString();
		}
		
		return key;
	}
	
/*	@Around(value = "pointcut() && @annotation(com.hxd.dao.common.HashCacheable) && @annotation(testInterface)")
	public Object hashCached(final ProceedingJoinPoint pjp,HashCacheable hashCacheable) throws Throwable {
		//获取缓存的key
		String key=hashCacheable.key();
		//获取注解中的hash中的key
		String entryKey=hashCacheable.fieldKey();
		//从缓存获取数据
		Object value=hashOps.get(key,entryKey);
		//如果取到值 之后 直接返回
		if(value!=null) {
		//获取方法的返回类型,让缓存可以返回正确的类型
		returnType=((MethodSignature)pjp.getSignature()).getReturnType();
		return (hashCacheable.entryFlag() ? JSON.parseObject(value.toString(), returnType) :JSON.parse(value.toString()) );
		}
		//跳过缓存,到后端查询数据
		Object result = pjp.proceed();
		//判断从数据查询结果是否为空 不为空就缓存到redis中
		if (result!=null&&result!="") {
			Object setResult= JSON.toJSONString(result);
			//将数据库查询出的值插入redis中
			if(hashCacheable.expire()<=0) {	
				//如果没有设置过期时间,则无限期缓存
				redisUtils.set(key,setResult);
			} else {			
				//否则设置缓存时间
				redisUtils.set(key,setResult,hashCacheable.expire(),TimeUnit.SECONDS)  ;
			}
		}
		 return  result;
	}*/
	
	
	
	
	
}