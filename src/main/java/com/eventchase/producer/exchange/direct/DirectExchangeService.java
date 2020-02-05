package com.eventchase.producer.exchange.direct;

import com.eventchase.producer.exchange.RabbitMQConfiguration;
import com.eventchase.producer.exchange.RequestDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DirectExchangeService {
	private final RabbitMQConfiguration rabbitMQConfiguration;
	private final RabbitTemplate rabbitTemplate;
	private final DirectExchange directExchange;

	public void sendMessage(RequestDTO requestDTO) {
		log.info("Pushing {} to exchange {}", requestDTO, directExchange.getName());
		rabbitTemplate.convertAndSend(directExchange.getName(), rabbitMQConfiguration.getRoutingKeyName(), requestDTO);
	}
}
