package com.xxx.guava.test.splitter;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class SplitterTest {

  Pattern pattern = Pattern.compile("[.|,]");

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

  @Test
  public void splitStringToListWithDelimiter() {
    /*通过Char初始化拆分器，将String直接分隔为List*/
    String str = "this, is  , , random , text,";
    List<String> list = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(str);
    list.forEach(System.out::println);
    //测试添加、移除  抛出 UnsupportedOperationException 看来分割后不支持 添加移除
    list.add("aaa");
    list.remove("aaa");
  }

  @Test
  public void splitStringToListWithCharMatcher() {
    /*通过CharMatcher初始化拆分器*/
    String str = "a,b;c.d,e.f)))),g,h.i;j.1,2.3;";
    List<String> list = Splitter.on(CharMatcher.anyOf(";.,)")).omitEmptyStrings().trimResults().splitToList(str);
    list.forEach(System.out::println);
    System.out.println(list.size());
  }

  @Test
  public void splitStringToListWithRegularExpression() {
    /*通过正则表达式初始化拆分器*/
    String str = "apple.banana,,orange,,.";
//    List<String> list = Splitter.on(Pattern.compile("[.|,]")).omitEmptyStrings().trimResults().splitToList(str);
    List<String> list = Splitter.on(pattern).omitEmptyStrings().trimResults().splitToList(str);
//    List<String> list = Splitter.onPattern("[.|,]").omitEmptyStrings().trimResults().splitToList(str);
    list.forEach(System.out::println);
    System.out.println(list.size());
  }

  @Test
  public void splitStringToListWithFixedLength() {
    /*将字符串分割为元素长度固定的List，最后一个元素长度不足可以直接返回*/
    String str = "Hello world";
    List<String> list = Splitter.fixedLength(3).splitToList(str);
    list.forEach(System.out::println);
    System.out.println(list.size());
  }

  @Test
  public void splitStringToMap() {
    /*String转Map*/
    String str = "John=first,Adam=second";
    Map<String, String> split = Splitter.on(",").withKeyValueSeparator("=").split(str);
    split.forEach((s, s2) -> System.out.println("key: "+s+" value: "+s));
  }
}
