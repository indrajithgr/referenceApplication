package com.learning.reference.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.reference.entity.User;
import com.learning.reference.service.SampleService;

@RestController
@RequestMapping("/v1")
public class ReferenceController {
	private static Logger logger = LoggerFactory.getLogger(ReferenceController.class);
	
	@Autowired
	private SampleService service;
	
	@GetMapping("/async")
	public ResponseEntity<String> getSamples(){
        long start = System.currentTimeMillis();
        CompletableFuture < User > page1 = service.getAllUsers("PivotalSoftware");
        CompletableFuture < User > page2 = service.getAllUsers("CloudFoundry");
        CompletableFuture < User > page3 = service.getAllUsers("Spring-Projects");
        CompletableFuture < User > page4 = service.getAllUsers("RameshMF");
        CompletableFuture.allOf(page1, page2, page3, page4).join();
        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
        try {
			logger.info("--> " + page1.get());
			logger.info("--> " + page2.get());
	        logger.info("--> " + page3.get());
	        logger.info("--> " + page4.get());
		} catch (InterruptedException | ExecutionException e) {
			logger.error(e.getMessage());
		}
		return ResponseEntity.ok("Success");
	}
}
