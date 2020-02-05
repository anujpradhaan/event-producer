package com.eventchase.producer.exchange;

import lombok.Data;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("rabbitmq")
@Data
public class RabbitMQConfiguration {
	private String topicExchangeName;
	private String directExchangeName;
	private String fanoutExchangeName;
	private String routingKeyName;
	private String username;
	private String password;
	private int port;
	private String host;

	@Bean
	public ConnectionFactory getConnectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(getHost());
		connectionFactory.setUsername(getUsername());
		connectionFactory.setPassword(getPassword());
		connectionFactory.setPort(getPort());
		return connectionFactory;
	}

	@Bean
	public RabbitAdmin rabbitAdmin() {
		RabbitAdmin rabbitAdmin = new RabbitAdmin(getConnectionFactory());
		return rabbitAdmin;
	}

	@Bean
	public RabbitTemplate getRabbitTemplate() {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(getConnectionFactory());
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	/**
	 * This configuration is important because the exchange will be created here the when the first message will be received.
	 * . If we don't create this configuration and use the name as String directly in convertAndSend then the exchange will be created as direct
	 *
	 * @return
	 */
	@Bean
	public TopicExchange getTopicExchange() {
		return new TopicExchange(getTopicExchangeName());
	}

	@Bean
	public DirectExchange getDirectExchange() {
		return new DirectExchange(getDirectExchangeName());
	}

	@Bean
	public FanoutExchange getFanoutExchange() {
		return new FanoutExchange(getFanoutExchangeName());
	}
}
