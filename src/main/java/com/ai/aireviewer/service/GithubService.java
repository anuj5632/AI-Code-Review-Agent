package com.ai.aireviewer.service;

import java.util.Map;

public interface GithubService {
    void processWebhook(Map<String, Object> payload);
}
