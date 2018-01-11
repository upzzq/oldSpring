package com.supplies.joint.develop.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisStringCache extends StringCache<Object> {

    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void set(String key, Object value) {
        this.redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, Object value, long expire, TimeUnit timeUnit) {
        this.redisTemplate.opsForValue().set(key, value, expire, timeUnit);
    }

    @Override
    public void set(Map<String, Object> values) {
        this.redisTemplate.opsForValue().multiSet(values);
    }

    @Override
    public <T> T get(String key) {
        return (T) this.redisTemplate.opsForValue().get(key);
    }

    @Override
    public void del(Set<String> keys) {
        this.redisTemplate.delete(keys);
    }

    @Override
    public void del(String key) {
        this.redisTemplate.delete(key);
    }

    @Override
    public Set<String> keys(String pattern) {
        return this.redisTemplate.keys(pattern);
    }

    @Override
    public boolean exists(String key) {
        return this.redisTemplate.hasKey(key);
    }

    @Override
    public void multiDel(String pattern) {
        del(keys(pattern));
    }

    @Override
    public long size(String key) {
        return ((String) get(key)).getBytes(charset).length;
    }

    public <T> void leftPush(String key, T value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    public long listSize(final String key) {
        return redisTemplate.opsForList().size(key);
    }


    @SuppressWarnings("unchecked")
    public <T> T rightPop(String key) {
        return (T) redisTemplate.opsForList().rightPop(key);
    }

    public void flushDB() {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return null;
            }
        });
    }

    public void flushAll() {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushAll();
                return null;
            }
        });
    }

    public Properties info() {
        return redisTemplate.execute(new RedisCallback<Properties>() {
            @Override
            public Properties doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.info();
            }
        });
    }


}
