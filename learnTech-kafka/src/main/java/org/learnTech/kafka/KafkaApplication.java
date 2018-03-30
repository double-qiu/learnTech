package org.learnTech.kafka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class KafkaApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(KafkaApplication.class).web(true).run(args);
	}

}
