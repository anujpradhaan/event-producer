package com.eventchase.producer.controller;

import com.eventchase.producer.exchange.topic.TopicExchangeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest
@ContextConfiguration(classes = { MessageProducerController.class, TopicExchangeService.class })
@SpringBootTest
public class MessageProducerControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	private void testTopicExchangeApi() throws Exception {
		String orderJson = "{\n"
				+ "  \"orderId\": \"adfafdafafdasfas\",\n"
				+ "  \"products\": [\n"
				+ "    {\n"
				+ "      \"name\": \"abc\",\n"
				+ "      \"price\": 1010\n"
				+ "    },\n"
				+ "    {\n"
				+ "      \"name\": \"abc1\",\n"
				+ "      \"price\": 10101\n"
				+ "    }\n"
				+ "  ],\n"
				+ "  \"receipient\": {\n"
				+ "    \"basicProfile\": {\n"
				+ "      \"name\": \"\",\n"
				+ "      \"phoneNumber\": \"\",\n"
				+ "      \"email\": \"\",\n"
				+ "      \n"
				+ "    },\n"
				+ "    \"addresses\": [\n"
				+ "      {\n"
				+ "        \"addressType\": \"billing\",\n"
				+ "        \"line1\": \"\",\n"
				+ "        \"line2\": \"\",\n"
				+ "        \"city\": \"\",\n"
				+ "        \"state\": \"\",\n"
				+ "        \"country\": \"\"\n"
				+ "      },\n"
				+ "      {\n"
				+ "        \"addressType\": \"delivery\",\n"
				+ "        \"line1\": \"\",\n"
				+ "        \"line2\": \"\",\n"
				+ "        \"city\": \"\",\n"
				+ "        \"state\": \"\",\n"
				+ "        \"country\": \"\"\n"
				+ "      }\n"
				+ "    ]\n"
				+ "  }\n"
				+ "}";
		mockMvc.perform(MockMvcRequestBuilders.post("/producer/topic")
				.content(orderJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}
}
