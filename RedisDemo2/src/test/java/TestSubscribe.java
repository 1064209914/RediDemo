
import org.junit.Test;

import com.hxd.listener.RedisMsgPubSubListener;

import redis.clients.jedis.Jedis;
// 订阅测试类

/*该类实现对频道redisChatTest的订阅监听，频道的订阅，取消订阅，收到消息都会调用listener对象的对应方法
注意：subscribe是一个阻塞的方法，在取消订阅该频道前，会一直阻塞在这，只有当取消了订阅才会执行下面的
other code，参考上面代码，我在onMessage里面收到消息后，调用了this.unsubscribe(); 来取消订阅，
这样才会执行后面的other code*/
public class TestSubscribe {

	@Test
	public void test() throws Exception{
		Jedis jedis = new Jedis("localhost",6379);  
        RedisMsgPubSubListener listener = new RedisMsgPubSubListener(); 
        //阻塞式  不取消 不会执行下一行代码
        jedis.subscribe(listener, "redisChatTest");
        //other code 
	}

}
