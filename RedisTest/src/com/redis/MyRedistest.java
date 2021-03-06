package com.redis;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import redis.clients.jedis.Jedis;
/**
 * Title: MyRedistest.java
 * Description: 递增或递减的项目 都可以  这里模拟的是1000个人同时抢100票
 * Company: htfg
 * @author 胡小东
 * @date 2017年10月18日 下午1:32:18
 */
public class MyRedistest {

	public static void main(String[] args) {
        final String watchkeys = "watchkeys";
        ExecutorService executor = Executors.newFixedThreadPool(20);  //20个线程池并发数

        final Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set(watchkeys, "100");//设置起始的抢购数
       // jedis.del("setsucc", "setfail");
        jedis.close();
         
        for (int i = 0; i < 1000; i++) {//设置1000个人来发起抢购
            executor.execute(new MyRunnable("user"+getRandomString(6)));
        }
        executor.shutdown();
    }

  
 public static String getRandomString(int length) { //length是随机字符串长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";  
        Random random = new Random();  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < length; i++) {  
            int number = random.nextInt(base.length());  
            sb.append(base.charAt(number));  
        }  
        return sb.toString();  
     } 
	
}
