package org.learnTech.kafka.web;

import org.learnTech.kafka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class WebController {

	@Autowired
	private KafkaProducerService kafkaProducerService;

	@RequestMapping("/kafka")
	@ResponseBody
	public String testkafka(String msg) {
		kafkaProducerService.sendMessage(msg);
		return "发送成功";
	}
}
