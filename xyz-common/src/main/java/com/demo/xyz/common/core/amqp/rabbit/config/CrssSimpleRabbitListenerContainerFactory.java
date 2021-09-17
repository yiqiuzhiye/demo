package com.demo.xyz.common.core.amqp.rabbit.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpoint;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

/**
 * 扩展SimpleRabbitListenerContainer，设置无限制重试次数，解决集群异常造成queue不存在消费者
 *
 * @author charles.ouyang
 */
public class CrssSimpleRabbitListenerContainerFactory extends SimpleRabbitListenerContainerFactory {

  @Override
  protected void initializeContainer(SimpleMessageListenerContainer instance, RabbitListenerEndpoint endpoint) {

    super.initializeContainer(instance, endpoint);
    instance.setDeclarationRetries(Integer.MAX_VALUE);


  }

}
