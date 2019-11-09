package com.learning.reference.service.impl;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learning.reference.entity.User;
import com.learning.reference.service.SampleService;

@Service
public class SampleServiceImpl implements SampleService {
	
	private static Logger logger = LoggerFactory.getLogger(SampleServiceImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Async("threadPoolTaskExecutor")
	public CompletableFuture<User> getAllUsers(String user) {
		logger.info("Looking up " + user);
    String url = String.format("https://api.github.com/users/%s", user);
    User results = restTemplate.getForObject(url, User.class);
    try {
		Thread.sleep(1000L);
	} catch (InterruptedException e) {
		logger.error(e.getMessage());
	}
    return CompletableFuture.completedFuture(results);}

}
