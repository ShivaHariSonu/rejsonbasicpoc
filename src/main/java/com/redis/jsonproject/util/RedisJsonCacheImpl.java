package com.redis.jsonproject.util;

import io.github.dengliming.redismodule.redisjson.RedisJSON;
import io.github.dengliming.redismodule.redisjson.args.GetArgs;
import io.github.dengliming.redismodule.redisjson.args.SetArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisJsonCacheImpl implements RedisJsonCache{

    @Autowired
    RedisJSON redisJSON;

    @Override
    public <K, V> void putInCache(String path, K key, V value) {

        redisJSON.set((String) key, SetArgs.Builder.create(path, value.toString()));
    }

    @Override
    public <K, V> V getFromCache(String path,Class type, K key) {
        return (V) redisJSON.get((String) key,type,new GetArgs().path(path));
    }
    @Override
    public <K, V> long delInCache(String path, K key) {

        return redisJSON.del((String) key,path);
    }

    @Override
    public <K, V> long arrAppendInCache(String path, K key, V value) {
        return redisJSON.arrAppend((String) key,path,value);
    }
    @Override
    public <K, V> long arrInsertInCache(String path, K key, V value, long index) {
        return redisJSON.arrInsert((String) key,path,index,value);
    }
    @Override
    public <K, V> long arrLengthInCache(String path, K key) {
        return redisJSON.arrLen((String) key,path);
    }
    @Override
    public <K, V> long arrTrimInCache(String path, K key, V value, long start, long stop) {
        return redisJSON.arrTrim((String) key,path,start,stop);
    }
    @Override
    public <K, V> long arrIndexInCache(String path, K key, V value, long start, long stop) {
        return redisJSON.arrIndex((String) key,path,value,start,stop);
    }

    @Override
    public <K, V> V arrPopInCache(String path, Class type, K key, long index) {
        return (V) redisJSON.arrPop((String) key,path,type,index);
    }
    @Override
    public <K, V> long objLenInCache(String path, K key) {
        return redisJSON.objLen((String) key,path);
    }
    @Override
    public <K, V> V objKeysInCache(String path, K key) {
        return (V) redisJSON.objKeys((String) key,path);
    }
    @Override
    public <K, V> V numIncrByInCache(String path, K key, V value) {
        Integer i = (Integer) value;
        Long val = Long.valueOf(i.longValue());
        return (V) redisJSON.incrBy((String)key,path,  val);
    }
    @Override
    public <K, V> V numMultByInCache(String path, K key, V value) {
        Integer i = (Integer) value;
        Long val = Long.valueOf(i.longValue());
        return (V) redisJSON.multBy((String)key,path, (Long) value);
    }
    @Override
    public <K, V> Class typeOfObjInCache(String path, K key) {
        return redisJSON.getType((String) key,path);
    }

}
