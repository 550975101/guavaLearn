package com.xxx.guava.test.files;

import com.google.common.base.Joiner;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author 封心
 */
public class FilesTest {

  @Test
  public void readLinesTest() {
    try {
      List<String> list = Files.readLines(new File("D:\\StudyCommitGit\\guavaLearn\\src\\main\\java\\com\\xxx\\guava\\test\\files\\a.txt"), Charset.defaultCharset());
      String join = Joiner.on(",").skipNulls().join(list);
      System.out.println(join);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void copyTest() {
    try {
      Files.copy(new File("D:\\StudyCommitGit\\guavaLearn\\src\\main\\java\\com\\xxx\\guava\\test\\files\\a.txt"), new File("D:\\StudyCommitGit\\guavaLearn\\src\\main\\java\\com\\xxx\\guava\\test\\files\\c.txt"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void copyTestTwo() {
    try {
      OutputStream outputStream = new FileOutputStream(new File("D:\\StudyCommitGit\\guavaLearn\\src\\main\\java\\com\\xxx\\guava\\test\\files\\d.txt"));
      Files.copy(new File("D:\\StudyCommitGit\\guavaLearn\\src\\main\\java\\com\\xxx\\guava\\test\\files\\a.txt"), outputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void touchTest() {
    try {
      Files.touch(new File("D:\\StudyCommitGit\\guavaLearn\\src\\main\\java\\com\\xxx\\guava\\test\\files\\touch.txt"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void createParentDirsTest() {
    try {
      Files.createParentDirs(new File("D:\\StudyCommitGit\\guavaLearn\\src\\main\\java\\com\\xxx\\guava\\test\\files\\parentdirs\\a.txt"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void moveTest() {
    try {
      Files.move(new File("D:\\StudyCommitGit\\guavaLearn\\src\\main\\java\\com\\xxx\\guava\\test\\files\\b.txt"), new File("D:\\StudyCommitGit\\guavaLearn\\src\\main\\java\\com\\xxx\\guava\\test\\files\\filesNew\\a.txt"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 失败了  没整明白
   */
  @Test
  public void mapTest() {
    try {
      ByteBuffer map = Files.map(new File("D:\\StudyCommitGit\\guavaLearn\\src\\main\\java\\com\\xxx\\guava\\test\\files\\a.txt"));
      ByteBuffer map1 = Files.map(new File("D:\\StudyCommitGit\\guavaLearn\\src\\main\\java\\com\\xxx\\guava\\test\\files\\b.txt"));
      map1.put(map);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
