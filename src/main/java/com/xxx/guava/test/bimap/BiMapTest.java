package com.xxx.guava.test.bimap;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.Set;

/**
 * @author 封心
 */
public class BiMapTest {

  @Test
  public void biMapTest() {
    BiMap<Integer, String> idNameBiMap = HashBiMap.create();
    idNameBiMap.put(1, "Michael");
    idNameBiMap.put(2, "Jane");
    idNameBiMap.put(3, "Marry");

    /*put添加已存在的value会报IllegalArgumentException*/
    //alue already present: Marry
    //idNameBiMap.put(4, "Marry");
    /*put添加已存在的key，会覆盖之前key对应的value值*/
    idNameBiMap.put(3, "wangjinrong");
    System.out.println(idNameBiMap);

    /*forcePut强制插入已存在的valu，会删除之前的键值对 2 - Jane*/
    idNameBiMap.forcePut(4, "Jane");
    System.out.println(idNameBiMap);

    Set<String> biMapValuesSet = idNameBiMap.values();
    System.out.println(biMapValuesSet);

    //key 和value 逆转
    BiMap<String, Integer> inverse = idNameBiMap.inverse();
    System.out.println(inverse);

    idNameBiMap.putAll(Maps.asMap(Sets.newHashSet(Lists.newArrayList(1, 2, 4)), integer -> integer + ""));
    System.out.println(idNameBiMap);
  }
}
