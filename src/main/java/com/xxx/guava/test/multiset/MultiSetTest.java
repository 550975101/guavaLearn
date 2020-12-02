package com.xxx.guava.test.multiset;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author 封心
 */
public class MultiSetTest {

  @Test
  public void multiSetTest() {
    HashMultiset<String> multiset = HashMultiset.<String>create();
    multiset.add("a");
    //放两个b  在multiset集合 (不是放b在索引2的位置)
    multiset.add("b", 2);
    ArrayList<String> addList = Lists.newArrayList("c", "c", "c");
    multiset.addAll(addList);
    //[a, b x 2, c x 3]
    System.out.println(multiset);
    //再放4个b  [a, b x 6, c x 3]
    multiset.add("b", 4);
    System.out.println(multiset);

    //判断集合中是否存在a
    System.out.println(multiset.contains("a"));
    //判断集合中是否存在a、c (集合做参数)  实际两个都存在
    System.out.println(multiset.containsAll(Lists.newArrayList("a", "c")));
    //试试一个存在 一个不存在  只要有一个不存在则返回false
    System.out.println(multiset.containsAll(Lists.newArrayList("a", "d")));
    //试试两个都不存在
    System.out.println(multiset.containsAll(Lists.newArrayList("d", "e")));

    Set<String> set = Sets.newLinkedHashSet(Lists.newArrayList("a", "b", "c"));
    System.out.println(set.equals(multiset));

    //获取setEntry
    Set<Multiset.Entry<String>> setEntry = multiset.entrySet();
    setEntry.forEach(e -> System.out.println(e.getElement() + ": " + e.getCount()));

    //移除全部a
    multiset.remove("a");
    System.out.println(multiset);
    //移除10个b 如果数量超过 则移除全部  不超过则移除相应的个数
    multiset.remove("b", 10);
    System.out.println(multiset);
    //removeAll 传入集合
    multiset.removeAll(Lists.newArrayList("c"));

    multiset.addAll(addList);
    System.out.println(multiset);
    multiset.removeIf(s -> s.length() == 1);
    System.out.println(multiset);

    //不存在 则添加
    multiset.setCount("x", 5);
    System.out.println(multiset);
    //存在 则设置相应的key个数
    multiset.setCount("x", 10);
    System.out.println(multiset);

    //element oldCount newCount 三个参数
    //oldCount 不一致则不添加
    multiset.setCount("y", 1, 5);
    System.out.println(multiset);
    multiset.setCount("y", 1);
    System.out.println(multiset);
    multiset.setCount("y", 1, 5);
    System.out.println(multiset);
  }
}
