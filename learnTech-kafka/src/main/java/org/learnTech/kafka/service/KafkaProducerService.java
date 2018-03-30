package org.learnTech.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerService {

	@Autowired
	private KafkaTemplate<?, String> kafkaTemplate;

	public void sendMessage(String msg) {
		kafkaTemplate.send("test", msg);
	}

}
