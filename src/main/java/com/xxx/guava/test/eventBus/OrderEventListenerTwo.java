package com.xxx.guava.test.eventBus;

import com.google.common.eventbus.Subscribe;

/**
 * @author 封心
 */
public class OrderEventListenerTwo {
  /**
   * 如果发送了OrderMessage消息，会进入到该函数的处理
   * @param event 消息
   */
  @Subscribe
  public void dealWithEvent(OrderMessage event) {
    // TODO: 收到EventTest消息之后，做相应的处理
    System.out.println("OrderEventListenerTwo收到了您的命令，命令内容为：" + event.getOrderContent());
  }

  @Subscribe
  public void dealWithStringEvent(String event) {
    // TODO: 收到EventTest消息之后，做相应的处理
    System.out.println("OrderEventListenerTwo--String收到了您的命令，命令内容为：" + event);
  }

  @Subscribe
  public void dealWithStringEventTwo(String event) {
    // TODO: 收到EventTest消息之后，做相应的处理
    System.out.println("OrderEventListenerTwo--dealWithStringEventTwo收到了您的命令，命令内容为：" + event);
  }
}
