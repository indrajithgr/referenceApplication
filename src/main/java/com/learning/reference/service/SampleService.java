package com.learning.reference.service;

import java.util.concurrent.CompletableFuture;

import com.learning.reference.entity.User;

public interface SampleService {
	public CompletableFuture<User> getAllUsers(String user);
}
