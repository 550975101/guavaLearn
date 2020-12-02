package com.xxx.guava.test.Ints;

import com.google.common.base.Converter;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 封心
 */
public class IntsTest {

  @Test
  public void asListTest() {
    /*基本类型数组转化为包装类List*/
    int[] intArray = {1, 2, 3, 4, 5};
    List<Integer> list = Ints.asList(intArray);
    System.out.println(list);
  }

  @Test
  public void checkedCastTest() {
    /*long转int，如果long值超出int范围抛IllegalArgumentException*/
    long input = 9998L;
    int result = Ints.checkedCast(input);
    //java.lang.IllegalArgumentException: Out of range: 2147483648
    input = 2147483648L;
    Ints.checkedCast(input);
  }

  @Test
  public void compareTest() {
    System.out.println(Ints.compare(1, 2));
    System.out.println(Ints.compare(2, 1));
    System.out.println(Ints.compare(-1, -2));
  }

  @Test
  public void concatTest() {
    /*将多个int数组拼接成一个数组*/
    int[] array1 = {1, 2, 3};
    int[] array2 = {4, 5, 6};
    int[] array3 = {7, 8};
    int[] result = Ints.concat(array1, array2, array3);
    System.out.println(Joiner.on(",").join(Arrays.stream(result).boxed().collect(Collectors.toList())));
  }

  @Test
  public void constrainToRangeTest() {
    /*如果一个数字在某个范围内则输出该数字，否则输出范围的最大值或最小值*/
    int result = Ints.constrainToRange(5, 0, 9);
    System.out.println(result);
    System.out.println(Ints.constrainToRange(10, 0, 9));
    System.out.println(Ints.constrainToRange(-1, 0, 9));
  }
  @Test
  public void containsTest() {
    /*判断一个int数是否在int数组内*/
    int[] array = {1, 2, 3, 4};
    System.out.println(Ints.contains(array, 3));
  }

  @Test
  public void ensureCapacityTest() {
    /*确保数组拥有一个最小的长度*/
    int[] array = {1, 2, 3, 4};
    //返回一个包含相同的值数组的数组，但保证是一个规定的最小长度。
    //如果minLength 大于 array长度 则新长度为minLength+padding
    int[] result = Ints.ensureCapacity(array, 6, 3);
    System.out.println(Joiner.on(",").join(Arrays.stream(result).boxed().collect(Collectors.toList())));
    //如果minLength 小于 array长度 则返回原array
    int[] result1 = Ints.ensureCapacity(array, 3, 3);
    System.out.println(Joiner.on(",").join(Arrays.stream(result1).boxed().collect(Collectors.toList())));
  }

  @Test
  public void fromByteArrayTest() {
    /*通过byte数组前四个元素转int值*/
    byte[] byteArray = {1, 1, 1, 1};
    int result = Ints.fromByteArray(byteArray);
    System.out.println(Joiner.on(",").join(Stream.of(byteArray).map(bytes -> Arrays.toString(byteArray) + "").collect(Collectors.toList())));
  }

  @Test
  public void fromBytesTest() {
    /*通过四个byte元素转int值*/
    int result = Ints.fromBytes((byte) 1, (byte) 1, (byte) 1, (byte) 1);
    System.out.println(result);
  }

  @Test
  public void hashCodeTest() {
    /*返回int值的hashCode(元素值)*/
    int hashCode = Ints.hashCode(1);
    System.out.println(hashCode);
  }

  @Test
  public void indexOfTest() {
    /*返回一个int值在数组中的第一个index,没匹配到返回-1*/
    //返回值等于3 的第一个索引
    int[] array = {1, 2, 3, 4, 3};
    System.out.println(Ints.indexOf(array, 3));
    //返回值等于5 的第一个索引  没有则返回-1
    System.out.println(Ints.indexOf(array, 5));
  }

  @Test
  public void arrayIndexOf() {
    /*返回int数组在另一个数组中的第一个index,没匹配到返回-1*/
    int[] array = {1, 2, 3, 4, 6, 5, 8};
    int[] target = {6, 5};
    System.out.println(Ints.indexOf(array, target));
  }
  @Test
  public void joinTest() {
    /*通过连接符连接数组转成String*/
    String str = "-";
    String join = Ints.join(str, 1, 2, 3);
    System.out.println(join);
  }

  @Test
  public void lastIndexOfTest() {
    /*返回一个int值在数组中的最后一个index,没匹配到返回-1*/
    int[] array = {1, 2, 3, 4, 3};
    System.out.println(Ints.lastIndexOf(array, 3));
    System.out.println(Ints.lastIndexOf(array, 5));
  }

  @Test
  public void lexicographicalComparatorTest() {
    /*返回一个int[]比较器，比较规则是从index0开始比较两个数组对应index上的元素大小，返回比较结果
     * 到其中一个数组结束都完全一致，则通过长度比较，长度大的那个数组大*/
    Comparator<int[]> comparator = Ints.lexicographicalComparator();
    int[] array1 = {1, 2, 3};
    int[] array2 = {1, 3, 3};
    int result = comparator.compare(array1, array2);
    System.out.println(result);
  }

  @Test
  public void maxMinTest() {
    /*返回一个数组的最大元素*/
    int[] array = {1, 16, 3, 5, 3};
    int max = Ints.max(array);
    System.out.println(max);
    int min = Ints.min(array);
    System.out.println(min);
  }

  @Test
  public void reverseTest() {
    /*将数组反转*/
    int[] array = {1, 16, 3, 5, 3};
    Ints.reverse(array);
    List<Integer> reverseList = Ints.asList(array);
    System.out.println(reverseList);

    /*将数组制定范围的元素反转(范围左闭右开)*/
    int[] array1 = {1, 16, 8, 5, 3};
    Ints.reverse(array1, 0, 2);
    List<Integer> reverseList1 = Ints.asList(array1);
    System.out.println(reverseList1);
  }

  @Test
  public void saturatedCastTest() {
    /*将long转化为int,超出int范围转化为2147483647*/
    long input = 9998L;
    int result = Ints.saturatedCast(input);
    System.out.println(result);

    long input1 = 2147483648L;
    int result1 = Ints.saturatedCast(input1);
    System.out.println(result1);
  }

  @Test
  public void sortDescendingTest() {
    /*数组按逆序排序*/
    int[] array = {1, 16, 8, 5, 3};
    Ints.sortDescending(array);
    List<Integer> sortList = Ints.asList(array);
    System.out.println(sortList);

    int[] array1 = {1, 16, 8, 5, 3};
    /*将一定范围内的数组按照逆序排序(范围左闭右开)*/
    Ints.sortDescending(array1, 0, 3);
    List<Integer> sortList1 = Ints.asList(array1);
    System.out.println(sortList1);
  }

  @Test
  public void stringConverterTest() {
    /*返回String与Integer的转换器*/
    Converter<String, Integer> converter = Ints.stringConverter();
    int num = converter.convert("123");
    System.out.println(num);
  }

  @Test
  public void toArrayTest() {
    /*List转数组*/
    List<Integer> list = Lists.newArrayList(1, 2, 3, 4);
    int[] arr = Ints.toArray(list);
    System.out.println(arr);
  }

  @Test
  public void toByteArrayTest() {
    /*int值转byte数组*/
    byte[] byteArray = Ints.toByteArray(666);
    System.out.println(Joiner.on(",").join(Stream.of(byteArray).map(bytes -> Arrays.toString(byteArray) + "").collect(Collectors.toList())));
  }

  @Test
  public void tryParseTest(){
    /*十进制String转Integer, 如果String值存在非法字符,转为null*/
    Integer result = Ints.tryParse("1234");
    System.out.println(result.intValue());

    Integer result1 = Ints.tryParse("1234ahd");
    System.out.println(result1);

    /*radix进制String转Integer, 如果String值存在非法字符,转为null*/
    Integer result2 = Ints.tryParse("0110", 2);
    System.out.println(result2.intValue());
  }
}
