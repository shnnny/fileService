package com.shnnny.notBlog.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzhh
 * @date 2018/10/11 20:12
 */
public class CachePool {
    //缓存池唯一实例
    private static CachePool instance;
    //缓存Map
    private static Map<String,Object> cacheItems;

    private CachePool(){
        cacheItems = new HashMap<String,Object>();
    }
    /**
     * 得到唯一实例
     * @return
     */
    public synchronized static CachePool getInstance(){
        if(instance == null){
            instance = new CachePool();
        }
        return instance;
    }
    /**
     * 清除所有Item缓存
     */
    public synchronized void clearAllItems(){
        cacheItems.clear();
    }
    /**
     * 获取缓存实体
     * @param name
     * @return
     */
    public synchronized Object getCacheItem(String name){
        if(!cacheItems.containsKey(name)){
            return null;
        }
        CacheItem cacheItem = (CacheItem) cacheItems.get(name);
        if(cacheItem.isExpired()){
            return null;
        }
        return cacheItem.getEntity();
    }
    /**
     * 存放缓存信息
     * @param name
     * @param obj
     * @param expires
     */
    public synchronized void putCacheItem(String name,Object obj,long expires){
        if(!cacheItems.containsKey(name)){
            cacheItems.put(name, new CacheItem(obj, expires));
        }
        CacheItem cacheItem = (CacheItem) cacheItems.get(name);
        cacheItem.setCreateTime(new Date());
        cacheItem.setEntity(obj);
        cacheItem.setExpireTime(expires);
    }
    public synchronized void putCacheItem(String name,Object obj){
        putCacheItem(name,obj,-1);
    }

    /**
     * 移除缓存数据
     * @param name
     */
    public synchronized void removeCacheItem(String name){
        if(!cacheItems.containsKey(name)){
            return;
        }
        cacheItems.remove(name);
    }

    /**
     * 获取缓存数据的数量
     * @return
     */
    public int getSize(){
        return cacheItems.size();
    }
}

