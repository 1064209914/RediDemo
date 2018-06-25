

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import redis.clients.jedis.Jedis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class UserDaoTest extends AbstractJUnit4SpringContextTests  {

	@Autowired
	public JedisConnectionFactory connect;
	@Autowired
	private com.hxd.test.Test test;
	
	@Autowired 
    public RedisTemplate redisTemplate;
	
	/*@Test  
	public void test1() {  
		test.ssss();
	}*/ 
	/**
	 * 哨兵模式测试
	 */
	@Test  
	@SuppressWarnings("unchecked")
	public void sentinelTest() {  
		ValueOperations<String, String> valueOperations= redisTemplate.opsForValue();
		valueOperations.set("sentinel", "this is test");
		System.out.println(valueOperations.get("sentinel")+"");
	} 
	
	
}
