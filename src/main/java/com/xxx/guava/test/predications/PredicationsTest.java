package com.xxx.guava.test.predications;

import com.google.common.base.Preconditions;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author 封心
 */
public class PredicationsTest {

  @Test
  public void pintAgeNoErrorMessage(){
    int age = 10;
    Preconditions.checkArgument(age > 0);
    System.out.println("age is: " + age);
  }

  @Test
  public void pintAgeWithErrorMessage() {
    //java.lang.IllegalArgumentException: Age can't be zero or less than zero.
    int age = -10;
    String message = "Age can't be zero or less than zero.";
    Preconditions.checkArgument(age > 0, message);
    System.out.println("age is: " + age);
  }

  @Test
  public void pintAgeWithTemplateErrorMessage() {
    int age = -10;
    String message = "Age should be positive number, you supplied %s.";
    Preconditions.checkArgument(age > 0, message, age);
    System.out.println("age is: " + age);
  }

  @Test
  public void checkElementIndexTest() {
    //java.lang.IndexOutOfBoundsException: Please check the bound of an array and retry (6) must be less than size (5)
    int[] numbers = {1, 2, 3, 4, 5};
    Preconditions.checkElementIndex(6, numbers.length, "Please check the bound of an array and retry");
  }

  @Test
  public void checkPositionIndexTest() {
    int[] numbers = {1, 2, 3, 4, 5};
    Preconditions.checkPositionIndex(6, numbers.length, "Please check the bound of an array and retry");

    //测试这个请注释上一行代码
    //java.lang.IndexOutOfBoundsException: start index (6) must not be greater than size (5)
    Preconditions.checkPositionIndexes(6, 0, numbers.length);
  }

  @Test
  public void checkNotNullTest () {
    String nullObject = null;
    //java.lang.NullPointerException: Please check the Object supplied, its null!
    Preconditions.checkNotNull(nullObject, "Please check the Object supplied, its null!");
  }

  @Test
  public void checkStateTest() {
    int[] validStates = { -1, 0, 1 };
    int givenState = 10;
    String message = "You have entered an invalid state";
    Preconditions.checkState(
      Arrays.binarySearch(validStates, givenState) > 0, message);
  }
}
