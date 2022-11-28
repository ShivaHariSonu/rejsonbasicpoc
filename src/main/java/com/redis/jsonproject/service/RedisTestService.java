package com.redis.jsonproject.service;

import com.redis.jsonproject.model.APIResponse;
import com.redis.jsonproject.util.RedisJsonCache;
import com.redis.jsonproject.util.RedisJsonCacheImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisTestService {
    private final RedisJsonCache redisJsonCache;



    @Autowired
    public RedisTestService(RedisJsonCache redisJsonCache) {
        this.redisJsonCache = redisJsonCache;
    }
    public boolean setRedisData(String path, String key, Object value){
        boolean output = true;
        try {
            redisJsonCache.putInCache(path,key,value);
        }
        catch (Exception ex){
            output = false;
        }
        return output;
    }
    public Object getRedisData(String path,Object type, String key){

        try {
            return redisJsonCache.getFromCache(path, (Class) type,key);
        }
        catch (Exception ex){
            return null;
        }
    }

    public Object performOps(APIResponse request) {
        String path = request.getPath();
        String key = request.getKey();
        Object value = request.getValue();
        switch (request.getOperationType()){
            case ARRINSERT -> {
                return redisJsonCache.arrIndexInCache(path,key,value,request.getStart(), request.getStop());
            }
            case ARRTRIM -> {
                return redisJsonCache.arrTrimInCache(path,key,value,request.getStart(), request.getStop());
            }
            case ARRPOP -> {
                return redisJsonCache.arrPopInCache(path,request.getType(),key, request.getIndex());
            }
            case ARRLEN -> {
                return redisJsonCache.arrLengthInCache(path,key);
            }
            case ARRINDEX -> {
                return redisJsonCache.arrLengthInCache(path, key);
            }
            case ARRAPPEND -> {
                return redisJsonCache.arrAppendInCache(path,key,value);
            }
            case TYPE -> {
                return redisJsonCache.typeOfObjInCache(path,key);
            }
            case OBJLEN -> {
                return redisJsonCache.objLenInCache(path,key);
            }
            case NUMINCR -> {
                return redisJsonCache.numIncrByInCache(path,key,value);
            }
            case NUMMULT -> {
                return redisJsonCache.numMultByInCache(path,key,value);
            }
            case OBJKEYS -> {
                return redisJsonCache.objKeysInCache(path,key);
            }
        }
        return null;
    }

//    public <K, V> long arrayOpsInCache(String path, K key, V value, ArrayOperationType arrayOpsType, long index, long start, long stop) {
//        switch (arrayOpsType){
//            case APPEND ->  {return redisJSON.arrAppend((String) key,path,value);}
//            case INSERT -> {return redisJSON.arrInsert((String) key,path,index,value);}
//            case LENGTH -> {return redisJSON.arrLen((String) key,path);}
//            case TRIM -> {return redisJSON.arrTrim((String) key,path,start,stop);}
//            case INDEX -> {return redisJSON.arrIndex((String) key,path,value,start,stop);}
//        }
//        return -1;
//    }
}
