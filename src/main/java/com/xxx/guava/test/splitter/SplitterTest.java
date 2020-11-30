package com.xxx.guava.test.splitter;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SplitterTest {
  @Test
  public void splitStringToIterableWithDelimiter() {
    /*通过Char初始化拆分器，将String分隔为Iterable*/
    String str = "this, is  , , random , text,";
    //omitEmptyStrings  忽略空字符串   trimResults去除空串
    ArrayList<String> list = Lists.newArrayList(Splitter.on(",").omitEmptyStrings().trimResults().split(str));
    list.forEach(System.out::println);

    String str1 = "~?~this, is~~ , , random , text,";
    List<String> list1 = Splitter.on(',').omitEmptyStrings().trimResults(CharMatcher.anyOf("~? ")).splitToList(str1);
    list1.forEach(System.out::println);
  }

}
