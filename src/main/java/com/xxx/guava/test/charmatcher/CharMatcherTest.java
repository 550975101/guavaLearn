package com.xxx.guava.test.charmatcher;

import com.google.common.base.CharMatcher;
import org.junit.Test;

/**
 * @author 封心
 */
public class CharMatcherTest {

    @Test
    public void retainFromTest() {
        String input = "H*el.lo,}12";
        CharMatcher matcher = CharMatcher.forPredicate(Character::isLetterOrDigit);
        String result = matcher.retainFrom(input);
        System.out.println(result);
    }

    @Test
    public void andTes() {
        /*返回两个Matcher执行逻辑与操作的Matcher*/
        String input = "H*el.lo,}12";
        CharMatcher matcher0 = CharMatcher.forPredicate(Character::isLetter);
        CharMatcher matcher1 = CharMatcher.forPredicate(Character::isLowerCase);
        String result = matcher0.and(matcher1).retainFrom(input);
        System.out.println(result);
    }

    @Test
    public void anyTest() {
        /*匹配任意字符*/
        String input = "H*el.lo,}12";
        CharMatcher matcher = CharMatcher.any();
        //retain  保留
        String result = matcher.retainFrom(input);
        System.out.println(result);
    }

    @Test
    public void anyOfTest() {
        /*匹配在CharSequence内的任意一个字符*/
        String input = "H*el.elHHllo,}12";
        CharMatcher matcher = CharMatcher.anyOf("Hel");
        //remove  移除
        String result = matcher.removeFrom(input);
        System.out.println(result);
    }

    @Test
    public void breakingWhitespaceTest() {
        String input = " this is test ";
        //匹配所有空格
        CharMatcher matcher = CharMatcher.breakingWhitespace();
        String result = matcher.removeFrom(input);
        System.out.println(result);
    }

    @Test
    public void asciiTest() {
        String input = "あH*el.lo,}12";
        CharMatcher matcher = CharMatcher.ascii();
        String result = matcher.retainFrom(input);
        System.out.println(result);
    }

    @Test
    public void collapseTest() {
        /*将charMatcher连续被匹配到的字符用一个replacement替换*/
        String input = "       hel    lo      ";
        String result = CharMatcher.is(' ').collapseFrom(input, '-');
        //-hel-lo-
        System.out.println(result);

        /*先进性Trim操作(将charSequence头和尾匹配到的连续字符去除)，再进行collapseFrom操作*/
        result = CharMatcher.is(' ').trimAndCollapseFrom(input, '_');
        //hel_lo
        System.out.println(result);
    }

    @Test
    public void countInTest() {
        String input = "H*el.lo,}12";
        //匹配字母和数字
        CharMatcher matcher = CharMatcher.forPredicate(Character::isLetterOrDigit);
        //匹配到个数
        int count = matcher.countIn(input);
        System.out.println(count);
    }


}

/**
 *2种操作:
 *
 * 1. 找到需要的CharMatcher
 * 2. 用CharMatcher进行操作
 *
 * 1.获得CharMatcher
 *
 * --内置的的对象:
 *
 *
 * ANY: 匹配任何字符
 * ASCII: 匹配是否是ASCII字符
 * BREAKING_WHITESPACE: 匹配所有可换行的空白字符(不包括非换行空白字符,例如"\u00a0")
 * DIGIT: 匹配ASCII数字
 * INVISIBLE: 匹配所有看不见的字符
 * JAVA_DIGIT: 匹配UNICODE数字, 使用 Character.isDigit() 实现
 * JAVA_ISO_CONTROL: 匹配ISO控制字符, 使用 Charater.isISOControl() 实现
 * JAVA_LETTER: 匹配字母, 使用 Charater.isLetter() 实现
 * JAVA_LETTER_OR_DIGET: 匹配数字或字母
 * JAVA_LOWER_CASE: 匹配小写
 * JAVA_UPPER_CASE: 匹配大写
 * NONE: 不匹配所有字符
 * SINGLE_WIDTH: 匹配单字宽字符, 如中文字就是双字宽
 * WHITESPACE: 匹配所有空白字符
 *
 *
 * --自己构造对象:
 *
 *
 *
 * CharMatcher is(char match): 返回匹配指定字符的Matcher
 * CharMatcher isNot(char match): 返回不匹配指定字符的Matcher
 * CharMatcher anyOf(CharSequence sequence): 返回匹配sequence中任意字符的Matcher
 * CharMatcher noneOf(CharSequence sequence): 返回不匹配sequence中任何一个字符的Matcher
 * CharMatcher inRange(char startInclusive, char endIncludesive): 返回匹配范围内任意字符的Matcher
 * CharMatcher forPredicate(Predicate<? super Charater> predicate): 返回使用predicate的apply()判断匹配的Matcher
 * CharMatcher negate(): 返回以当前Matcher判断规则相反的Matcher
 * CharMatcher and(CharMatcher other): 返回与other匹配条件组合做与来判断的Matcher
 * CharMatcher or(CharMatcher other): 返回与other匹配条件组合做或来判断的Matcher
 *
 *
 *
 * 2.用CharMatcher进行匹配或操作
 *
 * --进行匹配
 *
 * boolean matchesAnyOf(CharSequence sequence): 只要sequence中有任意字符能匹配Matcher,返回true
 * boolean matchesAllOf(CharSequence sequence): sequence中所有字符都能匹配Matcher,返回true
 * boolean matchesNoneOf(CharSequence sequence): sequence中所有字符都不能匹配Matcher,返回true
 *
 * int indexIn(CharSequence sequence): 返回sequence中匹配到的第一个字符的坐标
 * int indexIn(CharSequence sequence, int start): 返回从start开始,在sequence中匹配到的第一个字符的坐标
 * int lastIndexIn(CharSequence sequence): 返回sequence中最后一次匹配到的字符的坐标
 * int countIn(CharSequence sequence): 返回sequence中匹配到的字符计数
 *
 *
 *
 * --对字符串进行操作
 * String removeFrom(CharSequence sequence): 删除sequence中匹配到到的字符并返回
 * String retainFrom(CharSequence sequence): 保留sequence中匹配到的字符并返回
 * String replaceFrom(CharSequence sequence, char replacement): 替换sequence中匹配到的字符并返回
 * String trimFrom(CharSequence sequence): 删除首尾匹配到的字符并返回
 * String trimLeadingFrom(CharSequence sequence): 删除首部匹配到的字符
 * String trimTrailingFrom(CharSequence sequence): 删除尾部匹配到的字符
 * String collapseFrom(CharSequence sequence, char replacement): 将匹配到的组(连续匹配的字符)替换成replacement
 * String trimAndCollapseFrom(CharSequence sequence, char replacement): 先trim在replace
 *
 * */