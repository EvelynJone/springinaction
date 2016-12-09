package cache.config;


import net.sf.ehcache.CacheManager;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class CachingConfig {

    @Bean
    public EhCacheCacheManager cacheManager(CacheManager cm) {
        return new EhCacheCacheManager(cm);
    }

    @Bean
    public EhCacheManagerFactoryBean ehcache() {
        EhCacheManagerFactoryBean ehCacheFactoryBean =
                new EhCacheManagerFactoryBean();
        ehCacheFactoryBean.setConfigLocation(
                new ClassPathResource("ehcache.xml"));
        return ehCacheFactoryBean;
    }
/**
 * Redis缓存管理器Bean
 * @param redisTemplate
 * @return
 *//*
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }

    *//**
     * Redis连接工厂 bean
     * @return
     *//*
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.afterPropertiesSet();
        return factory;
    }

    *//**
     * RedisTemplate Bean
     * @param factory
     * @return
     *//*
    @Bean
    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String,String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
    @Bean
    public RedisTemplate<String,String> redisTemplate() {
        RedisTemplate<String,String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    *//**
     * 使用多个缓存管理器
     * @param cm
     * @param jcm
     * @return
     *//*
    @Bean
    public CacheManager cacheManager(net.sf.ehcache.CacheManager cm, javax.cache.CacheManager jcm) {
        // 创建缓存管理器的管理器
        CompositeCacheManager cacheManager = new CompositeCacheManager();
        List<CacheManager>  managers = new ArrayList<CacheManager>();
        managers.add(new JCacheCacheManager(jcm));
        managers.add(new EhCacheCacheManager(cm));
        managers.add(new RedisCacheManager(redisTemplate()));
        // 这样添加了以后，查找条目按照JCacheCacheManager，EhCacheCacheManager，RedisCacheManager的顺序查找
        cacheManager.setCacheManagers(managers);
        return cacheManager;
    }*/
}