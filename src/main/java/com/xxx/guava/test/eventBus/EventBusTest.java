package com.xxx.guava.test.eventBus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executor;

public class EventBusTest {
  public static void main(String[] args) {
    // 定义一个EventBus对象，因为我这里是测试，才这样写的。实际你应该定义一个单例 或者 其他的方式
    EventBus eventBus = new EventBus("test");
    // 注册监听者 同步的  谁先监听  谁先执行
    eventBus.register(new OrderEventListener());
    eventBus.register(new OrderEventListenerTwo());
    //自己根据消息类型 判断  参数只能一个
    eventBus.post(new OrderMessage());
    //凡是string类型都能匹配到
    eventBus.post("string message");

    Executor executor = command -> System.out.println(1111);

    AsyncEventBus asyncEventBus = new AsyncEventBus("test", executor);
  }
}
