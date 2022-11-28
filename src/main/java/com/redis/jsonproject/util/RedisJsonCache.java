package com.redis.jsonproject.util;

public interface RedisJsonCache {
    <K, V> void putInCache(final String path, final K key, final V value);
    <K, V> V getFromCache(final String path, final Class type,final K key);

    <K, V> long delInCache(String path, K key);

    //Array Operations
    <K, V> long arrAppendInCache(String path, K key, V value);
    <K, V> long arrInsertInCache(String path, K key, V value, long index);
    <K, V> long arrLengthInCache(String path, K key);
    <K, V> long arrTrimInCache(String path, K key, V value, long start, long stop);
    <K, V> long arrIndexInCache(String path, K key, V value, long start, long stop);
    <K, V> V arrPopInCache(String path, Class type, K key, long index);

    //Onject Operations
    abstract <K, V> long objLenInCache(String path, K key);
    <K, V> V objKeysInCache(String path, K key);

    //Number Operations
    <K, V> V numIncrByInCache(String path, K key, V value);
    <K, V> V numMultByInCache(String path, K key, V value);

    <K, V> Class typeOfObjInCache(String path, K key);
}
