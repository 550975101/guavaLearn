package com.xxx.guava.test.joiner;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 封心
 */
public class JoinerTest {
  /**
   * 将集合里的元素 按-分割并且跳过空串
   */
  @Test
  public void joinTest(){
    List<String> list = Lists.newArrayList("aaa", "bbb", null, "ccc");
    String joinStr = Joiner.on('-').skipNulls().join(list);
    System.out.println(joinStr);
  }

  /***
   * 将集合里的元素 按-分割并且用null字符串替换null值
   */
  @Test
  public void useForNullTest(){
    List<String> list = Lists.newArrayList("aaa", "bbb", null, "ccc");
    String joinStr = Joiner.on('-').useForNull("null").join(list);
    System.out.println(joinStr);
  }

  /**
   * 将集合里的元素按-分割跳过空串 然后在前面追加 字符串
   */
  @Test
  public void appendToTest() throws IOException {
    List<String> list = Lists.newArrayList("aaa", "bbb", null, "ccc");
    StringBuffer stringBuffer = new StringBuffer("this is: ");
    String result = Joiner.on('-').skipNulls().appendTo(stringBuffer, list).toString();
    System.out.println(result);
  }

  /**
   *appendTo(A appendable, @Nullable Object first, @Nullable Object second, Object... rest)
   */
  @Test
  public void appendToVariableParametersTest() throws IOException {
    StringBuffer stringBuffer = new StringBuffer("this is: ");
    //后面的参数 最后都会放在一个数组里 就是个可变参数 不理解谷歌为啥 前面整两个单独的 完事后面是可变参数  并且这些还会拿on后面的字符 分割
    String s = Joiner.on('-').skipNulls().appendTo(stringBuffer, 1, 2, 3, 4, 5, 6).toString();
    System.out.println(s);
  }

  @Test
  public void withKeyValueSeparatorTest(){
    Map<Integer, String> idNameMap = Maps.newHashMap();
    idNameMap.put(1, "Michael");
    idNameMap.put(2, "Mary");
    idNameMap.put(3, "Jane");
    String join = Joiner.on("\n").withKeyValueSeparator(":").join(idNameMap);
    System.out.println(join);
  }
}
