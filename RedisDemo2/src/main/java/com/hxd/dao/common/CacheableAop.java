package com.hxd.dao.common;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
	

	//切入点
    @Pointcut(value = "@annotation(com.hxd.dao.common.TestInterface)")
    private void pointcut() {}
	
	
	//execution(* com.hxd.service..*.*(..)) &&&& @annotation(testInterface)
	@Around(value = "pointcut() && @annotation(testInterface)")
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
		//result=(testInterface.entryFlag() ? JSON.toJSONString(result) : result);
		Object setResult= JSON.toJSONString(result);
		//将数据库查询出的值插入redis中
		if(testInterface.expire()<=0) {	
			//如果没有设置过期时间,则无限期缓存
		redisUtils.set(key,setResult);
		} else {			
			//否则设置缓存时间
		redisUtils.set(key,setResult,testInterface.expire(),TimeUnit.SECONDS)  ;
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
		/*StringBuilder buf=new StringBuilder();
		buf.append(pjp.getSignature().getDeclaringTypeName()).append(".").append(pjp.getSignature().getName());
		if(cache.key().length()>0) {
			buf.append(".").append(cache.key());
		}
		
		Object[] args=pjp.getArgs();
		if(cache.keyMode()==KeyMode.DEFAULT) {
			Annotation[][] pas=((MethodSignature)pjp.getSignature()).getMethod().getParameterAnnotations();
			for(int i=0;i<pas.length;i++) {
				for(Annotation an:pas[i]) {
					if(an instanceof CacheKey) {
						buf.append(".").append(args[i].toString());
						break;
					}
				}
			}
		} else if(cache.keyMode()==KeyMode.BASIC) {
			for(Object arg:args) {
				if(arg instanceof String) {
					buf.append(".").append(arg);
				} else if(arg instanceof Integer || arg instanceof Long || arg instanceof Short) {
					buf.append(".").append(arg.toString());
				} else if(arg instanceof Boolean) {
					buf.append(".").append(arg.toString());
				}
			}
		} else if(cache.keyMode()==KeyMode.ALL) {
			for(Object arg:args) {
				buf.append(".").append(arg.toString());
			}
		}
		
		return buf.toString();*/
	}
}