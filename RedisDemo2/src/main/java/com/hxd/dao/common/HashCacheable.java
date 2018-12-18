package com.hxd.dao.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD})  
public @interface HashCacheable { 
     
	 String key();   //redis 缓存中的key
     String fieldKey() ; //hash 中key
     int expire() default 0;  //默认失效时间
    public boolean entryFlag() default false;  //方法返回值类型是否是实体  
}