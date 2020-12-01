package com.xxx.guava.test.sets;

import com.google.common.collect.*;
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

    Set<Integer> set1 = Sets.newLinkedHashSet(Lists.newArrayList(-3, 1, 2, 3, 4, 5, 6));
    Set<Integer> filter1 = Sets.filter(set1, s -> s % 2 == 1);
    //[1, 3, 5]
    System.out.println(filter1);

    Set<Integer> set2 = Sets.newTreeSet(Lists.newArrayList(1, 2, 3, 4, 5, 6));
    //[1, 3, 5]
    Set<Integer> filter2 = Sets.filter(set2, s -> s % 2 == 1);
    System.out.println(filter2);
  }

  @Test
  public void intersectionTest() {
    /*求交集，返回结果Set是一个视图，不支持插入、删除操作*/
    Set<Character> first = ImmutableSet.of('a', 'b', 'c');
    Set<Character> second = ImmutableSet.of('b', 'c', 'd');

    Sets.SetView<Character> intersection = Sets.intersection(first, second);
    System.out.println(intersection);
  }

  @Test
  public void newConcurrentHashSetTest() {
    Set<Integer> set = Sets.newConcurrentHashSet();
    System.out.println(set.isEmpty());

    set = Sets.newConcurrentHashSet(Lists.newArrayList(1, 2, 3));
  }

  @Test
  public void newCopyOnWriteArraySetTest() {
    Set<Integer> set = Sets.newCopyOnWriteArraySet();
    System.out.println(set.isEmpty());
    set = Sets.newCopyOnWriteArraySet(Lists.newArrayList(1, 2, 3));
  }


  @Test
  public void newHashSetTest() {
    /*HashSet构造方法*/
    Set<Object> set = Sets.newHashSet();
    System.out.println(set.isEmpty());

    Set<Integer> set1 = Sets.newHashSet(1, 2, 3, 4, 5, 6, 7);
    System.out.println(set1);

    Set<String> set2 = Sets.newHashSet(Lists.newArrayList("1", "2", "3").iterator());
    System.out.println(set2);

    Set<String> set3 = Sets.newHashSet(Lists.newArrayList("1", "2", "3"));
    System.out.println(set3);
  }

  @Test
  public void newHashSetWithExpectedSizeTest() {
    /*Set底层依赖于Map实现，newHashSetWithExpectedSize传入的数值为Map底层bucket数组大小，JDK实现为2的指数幂，所以下面代码底层Map的bucket数组实际大小为16*/
    Set<Integer> set = Sets.newHashSetWithExpectedSize(14);
    System.out.println(set.isEmpty());
  }

  @Test
  public void newLinkedHashSetTest() {
    /*HashSet构造方法*/
    Set<Integer> set = Sets.newLinkedHashSet();
    System.out.println(set.isEmpty());

    set = Sets.newLinkedHashSet(Lists.newArrayList(1, 2, 3));
    System.out.println(set);
  }

  @Test
  public void newLinkedHashSetWithExpectedSizeTest() {
    /*Set底层依赖于Map实现，newLinkedHashSetWithExpectedSize传入的数值为Map底层bucket数组大小，JDK实现为2的指数幂，所以下面代码底层Map的bucket数组实际大小为16*/
    Set<Integer> set = Sets.newLinkedHashSetWithExpectedSize(14);
    System.out.println(set.isEmpty());
  }

  @Test
  public void newTreeSetTest() {
    Set<Integer> set = Sets.newTreeSet();
    System.out.println(set.isEmpty());

    set = Sets.newTreeSet(Lists.newArrayList(1, 2, 3));
    System.out.println(set);
  }

  @Test
  public void unionTest() {
    /*求合集*/
    Set<Character> first = ImmutableSet.of('a', 'b', 'c');
    Set<Character> second = ImmutableSet.of('b', 'c', 'd');

    Set<Character> intersection = Sets.union(first, second);
    System.out.println(intersection);

    Set<Integer> set1 = Sets.newHashSet(1, 2, 4, 5, 6, 8);
    Set<Integer> set2 = Sets.newHashSet(2, 3, 4, 5, 6, 7, 9);

    //合集，并集   [1, 2, 4, 5, 6, 8, 3, 7, 9]
    Set<Integer> result1 = Sets.union(set1, set2);
    //交集          [2, 4, 5, 6]
    Set<Integer> result2 = Sets.intersection(set1, set2);
    //差集 1中有而2中没有的  [1, 8]
    Set<Integer> result3 = Sets.difference(set1, set2);
    //相对差集 1中有2中没有  2中有1中没有的 取出来做结果 [1, 8, 3, 7, 9]
    Set<Integer> result4 = Sets.symmetricDifference(set1, set2);
    System.out.println(result1);
    System.out.println(result2);
    System.out.println(result3);
    System.out.println(result4);
  }

  @Test
  public void subSetTest() {
    NavigableSet<Integer> navigableSet = Sets.newTreeSet(Lists.newArrayList(1, 2, 3,4,5,6));
    //[1, 2, 3]
    Set<Integer> set = Sets.subSet(navigableSet, Range.openClosed(0, 5));
    System.out.println(set);
  }

  @Test
  public void test() {
    NavigableSet<String> sortedTreeSet = new TreeSet<String>(); // SortedSet接收TreeSet的实例
    // 增加元素
    sortedTreeSet.add("aa");
    sortedTreeSet.add("bb");
    sortedTreeSet.add("cc");
    sortedTreeSet.add("dd");
    sortedTreeSet.add("ee");

    System.out.println(sortedTreeSet.size());//5个元素：5
    System.out.println( sortedTreeSet.ceiling("cc"));//大于等于cc的最小值，不存在返回null：cc
    System.out.println(sortedTreeSet.descendingSet());//返回Set的逆序视图：[ee, dd, cc, bb, aa]
    System.out.println(sortedTreeSet.floor("cc"));//返回小于等于cc的元素的最大值，不存在返回null：cc
    System.out.println( sortedTreeSet.headSet("cc"));//返回元素小于cc的元素：[aa,bb]
    System.out.println( sortedTreeSet.headSet("cc", true));//返回元素小于等于cc的元素视图:[aa,bb,cc]

    System.out.println(sortedTreeSet.higher("cc"));//返回大于给定元素的最小元素:dd
    System.out.println( sortedTreeSet.lower("cc"));//返回小于cc的最大元素:bb
    System.out.println(sortedTreeSet.pollFirst());//移除第一个元素:aa
    System.out.println(sortedTreeSet.pollLast());//移除最后一个元素:ee
    System.out.println( sortedTreeSet.subSet("aa",true,"dd",true));//返回部分视图，true表示包括当前元素:[bb,cc,dd]
    System.out.println( sortedTreeSet.subSet("bb","dd"));//返回部分视图包括前面的，不包括后面的:[bb,cc]
    System.out.println( sortedTreeSet.tailSet("cc"));//返回元素大于cc的元素视图,包括cc:[cc,dd]
    System.out.println( sortedTreeSet.tailSet("cc", false));//返回元素大于等于cc的元素视图:[dd]
    System.out.println( sortedTreeSet.iterator());//返回set上的升序排序的迭代器
    System.out.println( sortedTreeSet.descendingIterator());//返回set上的降序排序的迭代器

    System.out.println("=====================================");
  }
}