package com.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class PipelineTest {

	 /**
     * @param args
     */
    public static void main(String[] args) {
       
        int count = 1000;
       
        long start = System.currentTimeMillis();
        withoutPipeline(count);
        long end = System.currentTimeMillis();
        System.out.println("withoutPipeline: " + (end-start));
       
        start = System.currentTimeMillis();
        usePipeline(count);
        end = System.currentTimeMillis();
        System.out.println("usePipeline: " + (end-start));
       
    }
 
    private static void withoutPipeline(int count){
        Jedis jr = null;
        try {
            jr = new Jedis("127.0.0.1", 6379);
            for(int i =0; i<count; i++){
                jr.incr("testKey1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if(jr!=null){
                jr.disconnect();
            }
        }
    }
   
    private static void usePipeline(int count){
        Jedis jr = null;
        try {
            jr = new Jedis("127.0.0.1", 6379);
            Pipeline pl = jr.pipelined();
            for(int i =0; i<count; i++){
                 pl.incr("testKey2");
            }
                pl.sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if(jr!=null){
                jr.disconnect();
            }
        }
    }
	
}
