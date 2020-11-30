package com.xxx.guava.test.splitter;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;

public class SplitterTest {
  @Test
  public void splitStringToIterableWithDelimiter() {
    /*通过Char初始化拆分器，将String分隔为Iterable*/
    String str = "this, is  , , random , text,";
    ArrayList<String> list = Lists.newArrayList(Splitter.on(",").omitEmptyStrings().trimResults().split(str));
    list.forEach(System.out::println);
  }

}
