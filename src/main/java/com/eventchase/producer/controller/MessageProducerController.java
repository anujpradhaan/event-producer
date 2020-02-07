package com.eventchase.producer.controller;

import com.eventchase.producer.exchange.Order;
import com.eventchase.producer.exchange.direct.DirectExchangeService;
import com.eventchase.producer.exchange.fanout.FanoutExchangeService;
import com.eventchase.producer.exchange.topic.TopicExchangeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
@AllArgsConstructor
public class MessageProducerController {

	private final TopicExchangeService topicExchangeService;
	private final DirectExchangeService directExchangeService;
	private final FanoutExchangeService fanoutExchangeService;

	@PostMapping("/topic")
	public void addToTopicExchange(@RequestBody Order order) {
		topicExchangeService.sendMessage(order);
	}

	@PostMapping("/direct")
	public void addToDirectExchange(@RequestBody Order order) {
		directExchangeService.sendMessage(order);
	}

	@PostMapping("/fanout")
	public void addToFanoutExchange(@RequestBody Order order) {
		fanoutExchangeService.sendMessage(order);
	}
}
