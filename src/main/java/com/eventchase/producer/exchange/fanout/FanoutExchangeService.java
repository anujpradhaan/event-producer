package com.eventchase.producer.exchange.fanout;

import com.eventchase.producer.exchange.RabbitMQConfiguration;
import com.eventchase.producer.exchange.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class FanoutExchangeService {

	private final RabbitMQConfiguration rabbitMQConfiguration;
	private final RabbitTemplate rabbitTemplate;
	private final FanoutExchange fanoutExchange;

	public void sendMessage(Order order) {
		log.info("Pushing {} to exchange {}", order, fanoutExchange.getName());
		rabbitTemplate.convertAndSend(fanoutExchange.getName(), rabbitMQConfiguration.getRoutingKeyName(), order);
	}
}
