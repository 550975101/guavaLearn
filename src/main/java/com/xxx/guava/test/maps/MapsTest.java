package com.xxx.guava.test.maps;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class MapsTest {
  @Test
  public void asMapTest() {
    /*asSet可用来将Set、SortedSet、NavigableSet转Map*/
    HashMap<String, Integer> compareMap = Maps.newHashMap();
    compareMap.put("This", 4);
    compareMap.put("is", 2);
    compareMap.put("test", 4);

    Set<String> hashSet = Sets.newHashSet(Lists.newArrayList("This", "is", "test"));
    Map<String, Integer> map0 = Maps.asMap(hashSet, s -> s.length());

    Set<String> treeSet = Sets.newTreeSet(Lists.newArrayList("This", "is", "test"));
    Map<String, Integer> map1 = Maps.asMap(treeSet, String::length);

    Set<String> sortedSet = Sets.newLinkedHashSet(Lists.newArrayList("This", "is", "test"));
    Map<String, Integer> map2 = Maps.asMap(sortedSet, String::length);

    Map<String, Integer> collect = sortedSet.stream().collect(Collectors.toMap(s -> s, String::length));
  }

  @Test
  public void differenceTest() {
    Map<String, Integer> left = Maps.newHashMap();
    Map<String, Integer> right = Maps.newHashMap();
    left.put("Michael", 18);
    left.put("Jane", 20);
    left.put("Mary", 22);
    left.put("haha", 22);

    right.put("Michael", 19);
    right.put("Jane", 18);
    right.put("Mary", 22);
    right.put("wangjinrong", 23);

    /*left与right的差*/
    MapDifference<String, Integer> difference = Maps.difference(left, right);
    /*left - right {haha=22}*/
    Map<String, Integer> entriesOnlyOnLeft = difference.entriesOnlyOnLeft();
    System.out.println(entriesOnlyOnLeft);
    /*right - left {wangjinrong=23}*/
    Map<String, Integer> entriesOnlyOnRight = difference.entriesOnlyOnRight();
    System.out.println(entriesOnlyOnRight);
    /*left与right相同的Entry {Mary=22}*/
    Map<String, Integer> entriesInCommon = difference.entriesInCommon();
    System.out.println(entriesInCommon);
  }

  @Test
  public void filterEntriesTest() {
    /*根据Entry过滤Map*/
    Map<String, Integer> compareMap = Maps.newHashMap();
    compareMap.put("Jane", 20);
    compareMap.put("Mary", 22);

    Map<String, Integer> left = Maps.newHashMap();
    left.put("Michael", 18);
    left.put("Jane", 20);
    left.put("Mary", 22);

    Map<String, Integer> resultMap = Maps.filterEntries(left, stringIntegerEntry -> stringIntegerEntry.getValue() > 18);
    System.out.println(resultMap);

    /*Java8 Stream filter也可以，比较起来好像Guava的Maps更方便一些*/
    Map<String, Integer> collect = left.entrySet().stream().filter(s -> s.getValue() > 18).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    System.out.println(collect);

    Map<String, Integer> collect1 = left.entrySet().stream().filter(s -> s.getValue() > 18).collect(Collectors.toMap(
      new Function<Map.Entry<String, Integer>, String>() {
      @Override
      public String apply(Map.@Nullable Entry<String, Integer> stringIntegerEntry) {
        return stringIntegerEntry.getKey();
      }
    }, new Function<Map.Entry<String, Integer>, Integer>() {
      @Override
      public Integer apply(Map.Entry<String, Integer> stringIntegerEntry) {
        return stringIntegerEntry.getValue();
      }
    }));
    System.out.println(collect1);
  }

  @Test
  public void filterKeysTest() {
    /*根据key过滤Map*/
    Map<String, Integer> compareMap = Maps.newHashMap();
    compareMap.put("Michael", 18);

    Map<String, Integer> left = Maps.newHashMap();
    left.put("Michael", 18);
    left.put("Jane", 20);
    left.put("Mary", 22);

    Map<String, Integer> resultMap = Maps.filterKeys(left, s -> s.length() > 4);
    System.out.println(resultMap);
  }

  @Test
  public void filterValuesTest() {
    /*根据value过滤Map*/
    Map<String, Integer> compareMap = Maps.newHashMap();
    compareMap.put("Michael", 18);

    Map<String, Integer> left = Maps.newHashMap();
    left.put("Michael", 18);
    left.put("Jane", 20);
    left.put("Mary", 22);

    Map<String, Integer> resultMap = Maps.filterValues(left, ele -> ele < 20);


    /*Java8 Stream filter也可以，比较起来好像Guava的Maps更方便一些*/
    Map<String, Integer> streamResult = left.entrySet().stream().filter(ele -> ele.getValue() < 20).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  @Test
  public void fromPropertiesTest() throws IOException {
    /*通过Properties构造ImmutableMap*/
    Map<String, String> compareMap = Maps.newHashMap();
    compareMap.put("this", "1");
    compareMap.put("is", "2");
    compareMap.put("test", "3");

    Properties properties = new Properties();
    properties.load(new FileReader(new File("D:\\StudyCommitGit\\guavaLearn\\src\\main\\java\\com\\xxx\\guava\\test\\maps\\test.properties")));
    String test = properties.getProperty("test");

    Map<String, String> map = Maps.fromProperties(properties);
    System.out.println(map);
  }

  @Test
  public void newConcurrentMapTest() {
    Map<String, Integer> map = Maps.newConcurrentMap();
    System.out.println(map.isEmpty());
  }

  @Test
  public void newHashMapTest() {
    Map<String, Integer> map = Maps.newHashMap();
    System.out.println(map.isEmpty());

    Map<String, Integer> sortedMap = Maps.newLinkedHashMap();
    sortedMap.put("wangjinrong", 11);
    /*HashMap<K, V> newHashMap(Map<? extends K, ? extends V> map)*/
    Map<String, Integer> map1 = Maps.newHashMap(sortedMap);
    System.out.println(map1);
  }

  @Test
  public void newHashMapWithExpectedSizeTest() {
    /*传入的数值为Map底层bucket数组大小，JDK实现为2的指数幂，所以下面代码底层Map的bucket数组实际大小为16*/
    Map<String, Integer> map = Maps.newHashMapWithExpectedSize(14);
    System.out.println(map.isEmpty());
  }

  @Test
  public void newLinkedHashMapTest() {
    Map<String, Integer> map = Maps.newLinkedHashMap();
    System.out.println(map.isEmpty());

    Map<String, Integer> sortedMap = Maps.newLinkedHashMap();
    sortedMap.put("wangjinrong", 11);
    /*LinkedHashMap<K, V> newLinkedHashMap(Map<? extends K, ? extends V> map)*/
    Map<String, Integer> map1 = Maps.newLinkedHashMap(sortedMap);
  }


  @Test
  public void newLinkedHashMapWithExpectedSizeTest() throws NoSuchFieldException, IllegalAccessException {
    /*传入的数值为Map底层bucket数组大小，JDK实现为2的指数幂，所以下面代码底层Map的bucket数组实际大小为16*/
    Map<String, Integer> map = Maps.newLinkedHashMapWithExpectedSize(14);
    System.out.println(map);
    Field[] fields = HashMap.class.getFields();
    Arrays.stream(fields).forEach(s->{
      s.setAccessible(true);
      System.out.println(s.toString());
    });
//    Field default_initial_capacity = HashMap.class.getField("DEFAULT_INITIAL_CAPACITY");
//    default_initial_capacity.setAccessible(true);
//    Integer integer = (Integer) default_initial_capacity.get(map);
//    System.out.println(integer);
  }

  @Test
  public void newTreeMapTest() {
    Map<String, Integer> map = Maps.newTreeMap();
    System.out.println(map.isEmpty());

    TreeMap<String, Integer> sortedMap = Maps.newTreeMap();
    sortedMap.put("wangjinrong", 11);

    Map<String, Integer> map1 = Maps.newTreeMap(sortedMap);
    System.out.println(map1.isEmpty());
  }

  @Test
  public void subMapTest() {
    Map<Integer, String> compareMap = Maps.newHashMap();
    compareMap.put(1, "chenhao");
    compareMap.put(2, "chenhong");

    TreeMap<Integer, String> idNameMap = Maps.newTreeMap();
    idNameMap.put(1, "chenhao");
    idNameMap.put(2, "chenhong");
    idNameMap.put(3, "xiaoxian");
    idNameMap.put(4, "haha");
    /*定义子Map的key的范围为(0, 3)*/
    Range<Integer> range = Range.openClosed(0, 3);
    Map<Integer, String> map = Maps.subMap(idNameMap, range);
    map.forEach((integer, s) -> System.out.println(integer + "---" + s));
  }

  @Test
  public void toMapTest() {
    Map<String, String> compareMap = Maps.newHashMap();
    compareMap.put("this", "THIS");
    compareMap.put("is", "IS");
    compareMap.put("test", "TEST");

    List<String> list = Lists.newArrayList("this", "is", "test", "is");

    /*ImmutableMap<K, V> toMap(Iterable<K> keys, Function<? super K, V> valueFunction)
     * 第二个参数为用于获取map value的函数表达式*/
    /*Guava toMap如果Iterable集合存在重复的情况，不会抛异常，会丢弃重复值*/
    Map<String, String> map = Maps.toMap(list, String::toUpperCase);
    System.out.println(map);

    /*Java 8 stream toMap操作*/
    /*使用Java8 stream toMap操作时，如果key存在重复的情况，会抛异常IllegalStateException*/
    Map<String, String> collect = list.stream().collect(Collectors.toMap(s -> s, String::toUpperCase));
    System.out.println(collect);
  }

  @Test
  public void transformEntriesTest() {
    /*根据Entry转化Map(根据key和value修改Map的value值)*/
    Map<String, String> compareMap = Maps.newHashMap();
    compareMap.put("this", "this4");
    compareMap.put("is", "is2");
    compareMap.put("test", "test4");

    Map<String, Integer> map = Maps.newHashMap();
    map.put("this", 4);
    map.put("is", 2);
    map.put("test", 4);

    Map<String, String> map1 = Maps.transformEntries(map, (k, v) -> k + v.toString());
    System.out.println(map1);

    Map<String, Integer> resultMap1 = Maps.transformEntries(map, (k, v) -> v + 1);
    System.out.println(resultMap1);

    /*Java 8 Stream Collectors.toMap转化Map, Guava使用简单一些，但是Java8适用性更强，可以同时转化key和value*/
    Map<String, String> resultMap2 = map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, ele -> ele.getKey() + ele.getValue()));
    System.out.println(resultMap2);

    Map<String, String> resultMap3 = map.entrySet().stream().collect(Collectors.toMap(ele -> ele.getKey().toUpperCase(), ele -> ele.getKey() + ele.getValue()));
    System.out.println(resultMap3);
  }

  @Test
  public void transformValuesTest(){
    /*通过Map的value转化Map(根据value修改value值)*/
    Map<String, Integer> compareMap = Maps.newHashMap();
    compareMap.put("this", 5);
    compareMap.put("is", 3);
    compareMap.put("test", 5);

    Map<String, Integer> map = Maps.newHashMap();
    map.put("this", 4);
    map.put("is", 2);
    map.put("test", 4);

    Map<String, Integer> resultMap = Maps.transformValues(map, value -> value + 1);
    System.out.println(resultMap);

    /*Java8 Stream 操作*/
    Map<String, Integer> resultMap1 = map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, ele -> ele.getValue() + 1));
    System.out.println(resultMap1);
  }

  @Test
  public void uniqueIndexTest(){
    Map<Integer, String> compareMap = Maps.newHashMap();
    compareMap.put(4, "this");
    compareMap.put(2, "is");


    /*Iterable作为Map的value，通过函数表达式生成key组成map*/
    List<String> list = Lists.newArrayList("this", "is");

    Map<Integer, String> map = Maps.uniqueIndex(list, String::length);
    System.out.println(map);

    //重复值抛异常
    List<String> list1 = Lists.newArrayList("this", "is", "test");
    Map<Integer, String> map1 = Maps.uniqueIndex(list1, String::length);
    System.out.println(map1);

    list.stream().collect(Collectors.toMap(String::length, s -> s));

    //重复值抛异常
    list1.stream().collect(Collectors.toMap(String::length, ele -> ele));
  }


}
