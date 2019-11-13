package com.lw.springcloud.eurekaconsumer.Utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {

//    private static Jedis jedis;
    private static JedisPool jedisPool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setTestOnBorrow(false);
        config.setTestOnReturn(true);
        config.setMinIdle(5);

        jedisPool = new JedisPool(config, "10.40.1.179", 6379);
//        jedis = jedisPool.getResource();
    }

    public synchronized static Jedis getJedis() {
        if (jedisPool != null) {
            Jedis jedis = jedisPool.getResource();
            return jedis;
        }
        return null;
    }

    public static void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    public static boolean getLock(String lockKey, String requestId, int timeout) {
        Jedis jedis = getJedis();
        String result = jedis.set(lockKey, requestId, "NX", "EX", timeout);
        if ("OK".equals(result)) {
            return true;
        }
        return false;
    }

    public static boolean getLock2(String lockKey, String requestId, int timeout) {
        Jedis jedis = getJedis();
        Long result = jedis.setnx(lockKey, requestId);
        if (result == 1) {
            jedis.expire(lockKey, timeout);
            return true;
        }
        return false;
    }

    public static void releaseLock(String lockKey, String requestId) {
        Jedis jedis = getJedis();
        if (requestId.equals(jedis.get(lockKey))) {
            jedis.del(lockKey);
        }
    }

    public static void poolConnect() {
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setTestOnBorrow(false);
//        config.setTestOnReturn(true);
//        JedisPool jedisPool = new JedisPool(config, "10.40.1.179", 6379);
//        Jedis jedis2 = jedisPool.getResource();
//        System.out.println(jedis2.get("num"));
//
//        jedis2.close();
//        jedisPool.close();

    }

    public static void main(String[] args) {
        //Jedis单实例连接
//        Jedis jedis1 = new Jedis("10.40.1.179", 6379);
//        System.out.println(jedis1.get("foo"));

//        poolConnect();
    }

}
