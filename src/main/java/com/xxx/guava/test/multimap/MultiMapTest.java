package com.xxx.guava.test.multimap;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * @author 封心
 */
public class MultiMapTest {

  /**
   * key可以重复的特殊 map
   */
  @Test
  public void multiMapTest(){
    Multimap<String, String> multimap = ArrayListMultimap.<String, String>create();
    multimap.put("key1", "this");
    multimap.put("key1", "is");
    multimap.put("key1", "key1List");
    System.out.println(multimap);
    //3
    System.out.println(multimap.size());
    //1 有几个key
    System.out.println(multimap.keySet().size());

    System.out.println("------------------------------------------------------");
    List<String> key2List = Lists.newArrayList("this", "is", "key2List");
    multimap.putAll("key2", key2List);
    //6 你说神奇不神奇
    System.out.println(multimap.size());
    System.out.println(multimap.containsKey("key1"));
    System.out.println(multimap.containsValue("key2List"));

    System.out.println("------------------------------------------------------");
    Multiset<String> keys = multimap.keys();
    //3
    System.out.println(keys.count("key1"));
    System.out.println(multimap);
    /*对keys返回的视图操作  不会影响multiMap*/
    keys.remove("key1");
    //神奇 删除keys的东西会影响原map的数据
    System.out.println("-----1----"+multimap.size());
    System.out.println("-----1----"+multimap);

    System.out.println("------------------------------------------------------");
    /*对keySet返回的视图操作  会影响multiMap*/
    Set<String> keySet = multimap.keySet();
    keySet.remove("key1");
    System.out.println("-----2----"+multimap.size());
    System.out.println("-----2----"+multimap);

    System.out.println("------------------------------------------------------");
    //移除存在的键值对
    multimap.remove("key2", "this");
    System.out.println(multimap);
    //移除不存在键值对
    multimap.remove("key2","testDeleteNonExistValue");
    System.out.println(multimap);

    System.out.println("------------------------------------------------------");
    List<String> key3List = Lists.newArrayList("key3","is", "create");
    multimap.putAll("key3", key3List);
    multimap.removeAll("key2");
    System.out.println(multimap);
    System.out.println(multimap.size());

    System.out.println("------------------------------------------------------");
    key3List = Lists.newArrayList("new", "key3","has","been","created");
    multimap.replaceValues("key3", key3List);
    System.out.println(multimap);
    System.out.println(multimap.size());
    /*key2不存在，新建key2*/
    multimap.replaceValues("key2", key3List);
    System.out.println(multimap);
    System.out.println(multimap.size());
  }
}
