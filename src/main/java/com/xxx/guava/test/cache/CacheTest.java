package com.xxx.guava.test.cache;

import com.google.common.cache.CacheBuilder;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CacheTest {

  private final LoadingCache<String, Map<String,String>> cache_code_name;
  /**
   * 缓存赋值
   * expireAfterWrite是在指定项在一定时间内没有创建/覆盖时，会移除该key，下次取的时候从loading中取
   * expireAfterAccess是指定项在一定时间内没有读写，会移除该key，下次取的时候从loading中取
   * refreshAfterWrite是在指定时间内没有被创建/覆盖，则指定时间过后，再次访问时，会去刷新该缓存，在新值没有到来之前，始终返回旧值
   * duration  持续时间
   */
  public CacheTest(){
    cache_code_name = CacheBuilder
      .newBuilder()
      .expireAfterWrite(10, TimeUnit.SECONDS)
      .removalListener(e -> System.out.println("正在移除: " + e.getKey()))
      .concurrencyLevel(5)
      .build(new CacheLoader<String, Map<String, String>>() {
        @Override
        public Map<String, String> load(String s){
          return getDataFromDb(s);
        }
      });
  }

  public Map<String, String> getDataFromDb(String s) {
    System.out.println("------------缓存更新:" + s + "-----------");
    Map<String, Map<String, String>> strMap = Maps.newHashMap();
    if ("item1".equals(s)) {
      Map<String, String> item1 = Maps.newHashMap();
      item1.put("1001", "ACODE10001");
      strMap.put("item1", item1);
    }
    if ("item2".equals(s)) {
      Map<String, String> item2 = Maps.newHashMap();
      item2.put("1001", "BCODE10001");
      strMap.put("item2", item2);
    }
    return strMap.get(s);
  }

  /**
   * 获取MAP
   * @param code
   * @return
   * @throws ExecutionException
   */
  public Map<String, String> name(String code) throws ExecutionException {
    Map<String, String> strMap = cache_code_name.get(code);
    return strMap;
  }
  // 执行
  public static void main(String[] args) throws Exception{
    CacheTest vo = new CacheTest();
    List<String> ls = Lists.newArrayList();
    ls.add("item1");
    ls.add("item2");
    for (int i = 0; i < 100; i++) {
      //10s  光get 完事11s 会删除
      if (i == 1) {
        Map<String, String> name = vo.name(ls.get(0));
        System.out.println(i+"---"+name);
      }
      if (i == 11) {
        Map<String, String> name1 = vo.name(ls.get(1));
        System.out.println(i+"---"+name1);
      }
      if (i == 12) {
        Map<String, String> name = vo.name(ls.get(0));
        System.out.println(i+"---"+name);
      }
      if (i == 22) {
        Map<String, String> name1 = vo.name(ls.get(1));
        System.out.println(i+"---"+name1);
      }
      if (i == 23) {
        Map<String, String> name1 = vo.name(ls.get(1));
        System.out.println(i+"---"+name1);
      }
      TimeUnit.SECONDS.sleep(1);
    }
  }

}
