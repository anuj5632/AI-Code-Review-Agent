package com.ai.aireviewer.service;

import com.ai.aireviewer.dto.SyncResponseDTO;

import java.util.Map;
import java.util.UUID;

public interface GithubService {
    void processWebhook(Map<String, Object> payload);
    SyncResponseDTO syncRepositories();
    SyncResponseDTO syncPullRequests(UUID repositoryId);
    SyncResponseDTO syncChangedFiles(UUID pullRequestId);
}
