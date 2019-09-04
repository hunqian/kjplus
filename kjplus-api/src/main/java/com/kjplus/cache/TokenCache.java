package com.kjplus.cache;

import com.kjplus.constant.Constant;
import org.apache.log4j.Logger;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//缓存的添加 删除  获取  类 具体存储删除逻辑  需在业务中自己实现
@Component("tokenCache")
public class TokenCache implements Cache {

    private Logger logger= Logger.getLogger(TokenCache.class);
    private Map<String, Object> store = new ConcurrentHashMap<String, Object>();

    private String name;

    //清空所有缓存
    public void clear() {
        store.clear();
    }

    //删除缓存
    public void evict(Object key) {
        Object thevalue = store.get(key);
        if(thevalue ==null)
            return;
        store.remove(key);
    }

    //获取缓存
    public ValueWrapper get(Object key) {
        ValueWrapper result = null;
        Object thevalue = store.get(key);
        if (thevalue != null) {
            result = new SimpleValueWrapper(thevalue);
        }
        return result;
    }

    //添加缓存
    public void put(Object key, Object value) {
        logger.info("当前缓存中的个数:"+store.size());
        boolean isNull=key ==null || value ==null ? true :false;
        if(isNull)
            return;//不处理控制存储
        //这一定义一个策略 缓存大于某个数值 直接清空 防止内存溢出
        if(store.size() > Constant.CACHE_MAX)
            clear();
        //查询缓存key是否存在 存在删除后 添加
        Object thevalue = store.get(key);
        if(thevalue !=null)
            evict(key);
        store.put((String) key, value);
    }

    //定义类模板获取
    public <T> T get(Object key, Class<T> clazz) {
        return clazz.cast(store.get(key));
    }

    public ValueWrapper putIfAbsent(Object key, Object value) {
        put(key, value);
        return new SimpleValueWrapper(value);
    }

    public Object getNativeCache() {
        return store;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
