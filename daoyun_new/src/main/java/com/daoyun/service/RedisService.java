package com.daoyun.service;

public interface RedisService {
    /**
     * 存储数据
     */
    void set(String key, String value,int minute);
    /**
     * 获取数据
     */
    String get(String key);

    /**
     * 根据 key:string 判断缓存是否存在
     *
     * @param key key
     * @return boolean
     */
    public boolean containsValueKey(String key);
}
