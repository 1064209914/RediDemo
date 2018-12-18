package com.hxd.listener;

import redis.clients.jedis.JedisPubSub;

public class RedisMsgPubSubListener extends JedisPubSub {
	
	
	 @Override  
	    public void unsubscribe() {  
	        super.unsubscribe();  
	    }  
	
	    @Override  
	    public void unsubscribe(String... channels) {  
	        super.unsubscribe(channels);  
	    }  
	  
	    @Override  
	    public void subscribe(String... channels) {  
	        super.subscribe(channels);  
	    }  
	  
	    @Override  
	    public void psubscribe(String... patterns) {  
	        super.psubscribe(patterns);  
	    }  
	  
	    @Override  
	    public void punsubscribe() {  
	        super.punsubscribe();  
	    }  
	  
	    @Override  
	    public void punsubscribe(String... patterns) {  
	        super.punsubscribe(patterns);  
	    }  
	  //监听到订阅平道接受到消息时的回调 
	    @Override  
	    public void onMessage(String channel, String message) {  
	        System.out.println("channel:" + channel + "receives message :" + message);  
	        this.unsubscribe();  
	    }  
	    //监听到订阅模式接受到消息时的回调 
	    
	    @Override  
	    public void onPMessage(String pattern, String channel, String message) {  
	  
	    }  
	  //订阅频道时的回调
	    @Override  
	    public void onSubscribe(String channel, int subscribedChannels) {  
	        System.out.println("channel:" + channel + "is been subscribed:" + subscribedChannels);  
	    }  
	  //取消订阅模式时的回调
	    @Override  
	    public void onPUnsubscribe(String pattern, int subscribedChannels) {  
	  
	    }  
	  //订阅频道模式时的回调
	    @Override  
	    public void onPSubscribe(String pattern, int subscribedChannels) {  
	  
	    }  
	  //取消订阅频道时的回调
	    @Override  
	    public void onUnsubscribe(String channel, int subscribedChannels) {  
	        System.out.println("channel:" + channel + "is been unsubscribed:" + subscribedChannels);  
	    }  
	
}
