package orders.redis.config;

import orders.redis.Item;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Class Name : RedisConfig<BR>
 * Descripe : TODO(这里用一句话描述这个类的作用)<BR>
 * Create by : zhaoxl<BR>
 * DATE: 2016/12/718:03<BR>
 * Version: V1.0<BR>
 * <p/>
 * copyright 轻重府.
 */
@Configuration
public class RedisConfig {

    /**
     * 创建连接工厂
     * @return
     */
    @Bean
    public RedisConnectionFactory redisCF() {

//        JedisConnectionFactory le = new LettuceConnectionFactory();
        JedisConnectionFactory cf = new JedisConnectionFactory();
        cf.setHostName("redis-server");
        cf.setPort(7379);
        cf.setPassword("foobared");
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String,Item> redisTemplate(RedisConnectionFactory cf) {
        RedisTemplate<String,Item> redis = new RedisTemplate<String, Item>();
        redis.setConnectionFactory(cf);
        return redis;
    }
}
