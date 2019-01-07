import org.junit.Test;

import static org.junit.Assert.*;

public class RedisClientDemoTest {

    @Test
    public void testRedisSingle() throws Exception{
        RedisClientDemo redisClientDemo = new RedisClientDemo();
        redisClientDemo.testRedisSingle();
    }

    @Test
    public void testRedisPool() throws   Exception{
        RedisClientDemo redisClientDemo = new RedisClientDemo();
        redisClientDemo.testRedisPool();
    }
}