package com.eventchase.producer.exchange.topic;

import com.eventchase.producer.exchange.RabbitMQConfiguration;
import com.eventchase.producer.exchange.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TopicExchangeService {
	private final RabbitMQConfiguration rabbitMQConfiguration;
	private final RabbitTemplate rabbitTemplate;
	private final TopicExchange topicExchange;

	public void sendMessage(Order order) {
		log.info("Pushing {} to exchange {}", order, topicExchange.getName());
		rabbitTemplate.convertAndSend(topicExchange.getName(), rabbitMQConfiguration.getRoutingKeyName(), order);
	}

}
