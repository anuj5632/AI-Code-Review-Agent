package com.ai.aireviewer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class GithubServiceImpl implements GithubService {
    public void processWebhook(Map<String, Object> payload) {
        log.info("Received GitHub webhook with payload: {}", payload);

        String action = (String) payload.get("action");

        log.info("Received GitHub webhook with action: {}", action);
    }
}
