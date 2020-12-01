package com.xxx.guava.test.maps;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

import java.util.*;

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

    LinkedHashSet<String> strings = Sets.newLinkedHashSet(Lists.newArrayList("This", "is", "test"));

  }
}
