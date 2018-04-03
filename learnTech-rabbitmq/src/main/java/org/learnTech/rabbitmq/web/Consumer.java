package org.learnTech.rabbitmq.web;

import org.learnTech.rabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

//	@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME_1)
	public void process(Message msg) {
		System.out.println("queue1 收到消息 : " + new String(msg.getBody()));
	}

//	@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME_2)
	public void process1(Message msg) {
		System.out.println("queue2 收到消息 : " + new String(msg.getBody()));
	}

	@RabbitListener(queues = RabbitMQConfig.DEAD_QUEUE_NAME)
	public void processDead(Message msg) {
		System.out.println("dead_queue 收到消息 : " + new String(msg.getBody()));
	}
	
}
