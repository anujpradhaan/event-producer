package com.eventchase.producer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/health")
public class HealthCheckController {
	@GetMapping
	public String getHealthStatus() throws UnknownHostException {
		String ip = InetAddress.getLocalHost().getHostAddress();
		return "Working on IP : " + ip;
	}
}
