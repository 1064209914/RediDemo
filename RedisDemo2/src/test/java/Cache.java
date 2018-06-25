import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hxd.entry.User;
import com.hxd.service.UserService;

/*
 * 缓存测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-mvc.xml" })
public class Cache {
	
	@Autowired
	private UserService userService;
	@Test
	public void test(){
		/*User user =new User();
		user.setId(10001);
		user.setUseName("xiaodong");*/
		//userService.save(user)
		System.out.println("+++++++++++++");
	}
	
	@Test
	public void get(){
		System.out.println(userService.get(10001));
	}
}
