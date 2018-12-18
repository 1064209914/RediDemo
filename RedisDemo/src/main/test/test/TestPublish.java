package test;
import org.junit.Test;

import redis.clients.jedis.Jedis;
//发布消息测试类
/*
 * 这个类向频道redisChatTest发布消息，第二步因为订阅了该频道，所以会收到该消息。
 * */
public class TestPublish {

    @Test  
    public void testPublish() throws Exception{ 
    	//连接redis 
        Jedis jedis = new Jedis("localhost",6379);  
        //如果有密码的话
        //jr.auth("密码");
        jedis.publish("redisChatTest", "我是天才");  
   
        jedis.publish("redisChatTest", "我牛逼");  
    
        jedis.publish("redisChatTest", "哈哈"); 
    }  
	
}
