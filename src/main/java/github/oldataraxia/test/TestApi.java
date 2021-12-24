package github.oldataraxia.test;

import redis.clients.jedis.Jedis;

import java.util.*;

public class TestApi {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);


        //String
        jedis.append("k1", "v1");
        System.out.println(jedis.get("k1"));
        jedis.set("k2", "v2");
        // 批处理
        jedis.mset("k3", "v3", "k4", "v4", "k5", "v5");
        System.out.println(jedis.mget("k3", "k4", "k5"));

        // list
        jedis.lpush("list1", "v1", "v2", "v3");
        List<String> list = jedis.lrange("list1", 0, -1);
        for(String element: list) {
            System.out.println(element);
        }

        // set
        jedis.sadd("set1", "v1", "v2", "v3");
        Set<String> set = jedis.smembers("set1");
        for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
            String element = iterator.next();
            System.out.println(element);
        }
        jedis.srem("set1", "v2");
        System.out.println(jedis.smembers("set1").size());

        // hash
        jedis.hset("hash1", "k1", "v1");
        System.out.println(jedis.hget("hash1", "k1"));
        Map<String, String> map = new HashMap<>();
        map.put("k2", "v2");
        map.put("k3", "v3");
        jedis.hmset("hash2", map); // 直接设置一个hash
        List<String> result = jedis.hmget("hash2", "k2", "k3");
        for (String element: result) {
            System.out.println(element);
        }

        // zset
        jedis.zadd("zset1", 1, "v1");
        jedis.zadd("zset1", 2, "v2");
        jedis.zadd("zset1", 3, "v3");
        Set<String> zset = jedis.zrange("zset1", 0, -1);
        for(Iterator iterator = zset.iterator(); iterator.hasNext();) {
            String string = (String) iterator.next();
            System.out.println(string);
        }
    }
}
