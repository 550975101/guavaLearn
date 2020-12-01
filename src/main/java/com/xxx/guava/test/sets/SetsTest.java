package com.xxx.guava.test.sets;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.*;

/**
 * @author 封心
 */
public class SetsTest {

  @Test
  public void cartesianProductTest() {
    ArrayList<Set<Integer>> setList = Lists.newArrayList();
    setList.add(Sets.newHashSet(1, 2));
    setList.add(Sets.newHashSet(3, 6, 7));
    //笛卡尔积
    Set<List<Integer>> result = Sets.cartesianProduct(setList);
    System.out.println(result);
  }

  @Test
  public void cartesianProductTest1() {
    Set<Integer> set1 = Sets.newHashSet(1, 2);
    Set<Integer> set2 = Sets.newHashSet(3, 6, 7);
    Set<List<Integer>> result = Sets.cartesianProduct(set1, set2);
    System.out.println(result);
  }

  @Test
  public void combinationsTest() {
    /*求组合结果 n! / r! * (n - r)!*/
    Set<Integer> set = Sets.newHashSet(1, 2, 3, 4, 5);
    Set<Set<Integer>> combinations = Sets.combinations(set, 2);
    combinations.stream().forEach(System.out::println);
  }

  @Test
  public void differenceTest() {
    /*求差集，返回结果Set是一个视图，不支持插入、删除操作*/
    Set<Character> first = Sets.newHashSet('a', 'b', 'c');
    Set<Character> second = Sets.newHashSet('b', 'c', 'd');
    Set<Character> result = Sets.difference(first, second);
    System.out.println(result);
    result = Sets.difference(second, first);
    System.out.println(result);
  }

  @Test
  public void filterTest() {
    Set<Integer> set = Sets.newHashSet(1, 2, 3, 4, 5, 6);
    Set<Integer> filter = Sets.filter(set, s -> s % 2 == 0);
    //[2, 4, 6]
    System.out.println(filter);

    Set<Integer> set1 = Sets.newLinkedHashSet(Lists.newArrayList(-3,1, 2, 3, 4, 5, 6));
    Set<Integer> filter1 = Sets.filter(set1, s -> s % 2 == 1);
    //[1, 3, 5]
    System.out.println(filter1);

    Set<Integer> set2 = Sets.newTreeSet(Lists.newArrayList(1, 2, 3, 4, 5, 6));
    //[1, 3, 5]
    Set<Integer> filter2 = Sets.filter(set2, s -> s % 2 == 1);
    System.out.println(filter2);
  }
}
