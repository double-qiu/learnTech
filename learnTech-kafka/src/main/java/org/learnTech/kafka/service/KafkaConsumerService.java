package org.learnTech.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class KafkaConsumerService {

	@KafkaListener(topics = "test")
	public void processMessage(String content) {
		if (!StringUtils.isEmpty(content))
			System.out.println(content);
	}

}
