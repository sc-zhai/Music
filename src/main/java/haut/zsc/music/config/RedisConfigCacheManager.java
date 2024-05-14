package haut.zsc.music.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.*;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
public class RedisConfigCacheManager extends RedisCacheManager {
 
 
    public RedisConfigCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }
 
    private static final RedisSerializationContext.SerializationPair<Object> DEFAULT_PAIR = RedisSerializationContext.SerializationPair
            .fromSerializer(new GenericJackson2JsonRedisSerializer());
 
    private static final CacheKeyPrefix DEFAULT_CACHE_KEY_PREFIX = cacheName -> cacheName+":";
 
    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        final int lastIndexOf = StringUtils.lastIndexOf(name, '#');
        if (lastIndexOf > -1) {
            final String time = StringUtils.substring(name, lastIndexOf + 1);
            final Duration duration = Duration.ofSeconds(Long.parseLong(time));
            cacheConfig = cacheConfig.entryTtl(duration);
            //修改缓存key和value值的序列化方式
            cacheConfig = cacheConfig.computePrefixWith(DEFAULT_CACHE_KEY_PREFIX)
                    .serializeValuesWith(DEFAULT_PAIR);
            final String cacheName = StringUtils.substring(name, 0, lastIndexOf);
            return super.createRedisCache(cacheName, cacheConfig);
        }else{
            //修改缓存key和value值的序列化方式
            cacheConfig = cacheConfig.computePrefixWith(DEFAULT_CACHE_KEY_PREFIX)
                    .serializeValuesWith(DEFAULT_PAIR);
            return super.createRedisCache(name, cacheConfig);
        }
    }
}