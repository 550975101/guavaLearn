package com.xxx.guava.test.lists;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 封心
 */
public class ListsTest {

  @Test
  public void asListTest() {
    /*asList可用来将array转化为List,并在list头部插入值,不支持基本类型array*/
    int[] array = new int[]{1, 2, 3};
    List<Integer> list = Lists.asList(4, ArrayUtils.toObject(array));
    System.out.println(list);
    //不支持添加
//    list.add(22);

    /*list转array, 必须使用toArray(T[] array), 传入的是类型完全一样的数组，大小为list.size()
     * 如果使用无参toArray()方法，只能转成object[], 无法进行类型转换，强转会报ClassCastException*/
    Integer[] array2 = list.toArray(new Integer[list.size()]);
    list = Lists.asList(8, 9, array2);
    System.out.println(list);
  }

  @Test
  public void cartesianProductTest() {
    /*cartesianProduct用来计算若干个List的笛卡尔乘积*/
    List<Integer> list1 = Lists.newArrayList(1, 2, 3);
    List<Integer> list2 = Lists.newArrayList(5, 6, 7, 8);
    List<Integer> list3 = Lists.newArrayList(0, 9);
    //[[1, 5, 0], [1, 5, 9], [1, 6, 0], [1, 6, 9], [1, 7, 0], [1, 7, 9], [1, 8, 0], [1, 8, 9], [2, 5, 0], [2, 5, 9], [2, 6, 0], [2, 6, 9], [2, 7, 0], [2, 7, 9], [2, 8, 0], [2, 8, 9], [3, 5, 0], [3, 5, 9], [3, 6, 0], [3, 6, 9], [3, 7, 0], [3, 7, 9], [3, 8, 0], [3, 8, 9]]
    //你观察 你细品
    List<List<Integer>> lists = Lists.cartesianProduct(list1, list2, list3);
    System.out.println(lists);

    /*嵌套的List也可以直接作为参数计算笛卡尔乘积*/
    List<List<Integer>> list = Lists.newArrayList(Lists.newArrayList(1, 2), Lists.newArrayList(5, 6));
    List<List<Integer>> cartesianResult1 = Lists.cartesianProduct(list);
    System.out.println(cartesianResult1);
  }

  @Test
  public void charactersOfTest() {
    /*charactersOf(String string)*/
    List<Character> characters = Lists.charactersOf("wangjinrong");
    System.out.println(characters);

    /*charactersOf(CharSequence sequence)*/
    characters = Lists.charactersOf(new StringBuilder("Michael"));
    System.out.println(characters);
  }

  @Test
  public void newArrayListTest() {
    /*无参构造函数*/
    List<Integer> list = Lists.newArrayList();
    System.out.println(list.isEmpty());

    list = Lists.newArrayList(1, 2, 3);
    System.out.println(list);

    /*newArrayList(Iterable elements)*/
    list = Lists.newArrayList(Sets.newHashSet(1, 2, 4));
    System.out.println(list);
  }


  @Test
  public void newArrayListWithCapacityTest() throws NoSuchFieldException, IllegalAccessException {
    /*newArrayListWithCapacity直接指定返回的arrayList容量*/
    List<Integer> list = Lists.newArrayListWithCapacity(10);
    /*通过反射获取list内部实现elementData的length属性*/
    Field field = ArrayList.class.getDeclaredField("elementData");
    field.setAccessible(true);
    Object[] objects = (Object[]) field.get(list);
    System.out.println(objects.length);

    /*newArrayListWithExpectedSize返回的arrayList容量为 5L + arraySize + (arraySize / 10)*/
    //5+10+(10/10)
    List<Integer> list1 = Lists.newArrayListWithExpectedSize(10);
    Object[] objects1 = (Object[]) field.get(list1);
    System.out.println(objects1.length);
  }

  @Test
  public void newLinkedListTest() {
    List<Integer> list = Lists.newLinkedList();
    System.out.println(list.isEmpty());

    List<String> linkedList = Lists.newLinkedList(Sets.newHashSet("3", "4", "5"));
    System.out.println(linkedList);
  }

  @Test
  public void CopyOnWriteArrayListTest() {
    List<Integer> list0 = Lists.newCopyOnWriteArrayList();
    System.out.println(list0.isEmpty());

    List<Integer> list = Lists.newCopyOnWriteArrayList(Sets.newHashSet(3, 4, 5));
    System.out.println(list);
  }

  /**
   * 按指定容量拆分list  为多个小list的集合
   */
  @Test
  public void partitionTest() {
    List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);
    //填负数 会报错
    List<List<Integer>> partition = Lists.partition(list, 1);
    System.out.println(partition);
  }

  /**
   * 逆转集合
   */
  @Test
  public void reverseTest() {
    List<String> names = Lists.newArrayList("John", "Adam", "Jane");
    List<String> reverse = Lists.reverse(names);
    System.out.println(reverse);
  }
}
