package com.xxx.guava.test.eventBus;

/**
 * @author 封心
 */
public class OrderMessage {
  /**
   * 命令对应的内容
   */
  private String orderContent;


  public String getOrderContent() {
    return orderContent;
  }

  public void setOrderContent(String orderContent) {
    this.orderContent = orderContent;
  }
}
