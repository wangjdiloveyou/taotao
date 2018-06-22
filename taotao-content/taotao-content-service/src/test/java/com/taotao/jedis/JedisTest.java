package com.taotao.jedis;

import com.taotao.content.jedis.JedisClient;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;

/**
 * @ProjectName: taotao
 * @Package: com.taotao.jedis
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: wangjindi
 * @Version: 1.0
 */
public class JedisTest {

    @Test
    public void testJedisSingle(){
        //第一步创建redis对象
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //使用jedis对象操作数据库
        jedis.set("myTest","1000");
        String result = jedis.get("myTest");
        System.out.println(result);
        jedis.close();
    }

    @Test
    public void testJedisClientPool(){

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext-redis.xml");
        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
        jedisClient.set("client","663636");
        System.out.println(jedisClient.get("client"));
    }
}
