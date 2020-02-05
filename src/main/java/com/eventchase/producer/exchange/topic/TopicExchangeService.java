package com.eventchase.producer.exchange.topic;

import com.eventchase.producer.exchange.RabbitMQConfiguration;
import com.eventchase.producer.exchange.RequestDTO;
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

	public void sendMessage(RequestDTO requestDTO) {
		log.info("Pushing {} to exchange {}", requestDTO, topicExchange.getName());
		rabbitTemplate.convertAndSend(topicExchange.getName(), rabbitMQConfiguration.getRoutingKeyName(), requestDTO);
	}

}
