package com.taotao.content.jedis;

/**
 * @ProjectName: taotao
 * @Package: com.taotao.content.jedis
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: wangjindi
 * @Version: 1.0
 */
public interface JedisClient {

    String set(String key, String value);
    String get(String key);
    Boolean exists(String key);
    Long expire(String key, int seconds);
    Long ttl(String key);
    Long incr(String key);
    Long hset(String key, String field, String value);
    String hget(String key, String field);
    Long hdel(String key, String... field);
}